import DynamicTable from './DynamicTable'
import './style.css'
import { useState } from 'react'
import PopUp from './PopUp'
const DashboardTable = ({headers,data}) => {
const [showPopUp, setShowPopUp] = useState(false)
const [details, setDetails] = useState([])
  const submitRequest=(adjustmentid,status)=>{
    console.log(adjustmentid,status);
  }

  const fixIdAndStatus= async(Details)=>{
    console.log(Details)
    if(Details.length > 0)
    {
      setDetails(Details)
      setShowPopUp(true)
    }
     

  }
  const detailHeaders=["adjustmentId","productId","batch","batchId","quantity","mrp","expiryDate","amount","generatedBatchId"]

  
  return (
    <>
    <button name="accept" id="accept">Accepted</button>
    <button name="reject" id="reject">Rejected</button>
    <button name="open" id="open">OPEN</button>
   <div id="model">
          <PopUp showPopUp={showPopUp} closePopUp={()=>setShowPopUp(false)}>
          <DynamicTable headers={detailHeaders} data={details}/>
          </PopUp>
    </div>
    <div id="dashboard-table">
    <table>
        <thead>
        <tr>
    {headers.map((header,index)=>(
        
        <th key={index}>
            {header}
        </th>  
    ))}
     <th>
      View All Adjustments
    </th>
    <th>
      Actions
    </th>
    </tr>
    </thead>
    <tbody>
    {
        data.map((adjustment)=>
        {
          return  (
            <tr key={adjustment.adjustmentId} style={{ borderBottom: '1px solid #ddd' }}>
                {headers.map((header,index)=>{
                
       
         return( <td key={index}>
           
         {adjustment[header]}
        </td>
         )
        })} 

        <td>
          {
            <>
            <button  onClick={()=>fixIdAndStatus(adjustment?.adjustmentDetails)}>Details</button>
             </>
          }
        </td>
        <td>
          {
            <>
            <button onClick={()=>submitRequest(adjustment?.adjustmentId,"A")}>Approve</button>
            <button onClick={()=>submitRequest(adjustment?.adjustmentId,"D")} >Deny</button>
             </>
          }
        </td>
            </tr>
          )
        })
    }
    
    </tbody>
    </table>
     </div>
       
    </>
  )
}

export default DashboardTable