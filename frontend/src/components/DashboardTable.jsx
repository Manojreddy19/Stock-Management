import DynamicTable from './DynamicTable'
import './style.css'
import { useEffect, useState } from 'react'
import PopUp from './PopUp'
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { ip } from '../assets/utils';


const DashboardTable = ({headers,data}) => {
const[dashData,setDashData] = useState(data)
const [showPopUp, setShowPopUp] = useState(false)
const [details, setDetails] = useState([])

useEffect(() => {
  filterAdjustmentsByStatus('OPEN');
}, [data]);
const filterAdjustmentsByStatus=(status) => {
  const filteredData = data.filter(adjustment => adjustment.status === status);
  setDashData(filteredData);
};

  const submitRequest = async (adjustmentId, status) => {
  const payload = {
    adjustmentId,
    status
  };

  try {
    const response = await axios.post(`http://${ip}:8080/api/updateStatus`, payload, {
      withCredentials: true,
      headers: {
        'Content-Type': 'application/json',
      }
    });

    toast.success(`Adjustment ${status} successfully!`);
    console.log("Status updated successfully", response.data);
  } catch (error) {
    toast.error(`${error.response?.data?.message || 'Failed to update status'}`);
    console.error("Failed to update status", error);
  }
};

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
     <ToastContainer autoClose={1000} limit={1} />
    <button name="accept" id="accept" onClick={() => filterAdjustmentsByStatus('ACCEPT')}>Accepted</button>
    <button name="reject" id="reject" onClick={() => filterAdjustmentsByStatus('REJECT')}>Rejected</button>
    <button name="open" id="open" onClick={() => filterAdjustmentsByStatus('OPEN')}>OPEN</button>
   <div id="model">
          <PopUp showPopUp={showPopUp} closePopUp={()=>setShowPopUp(false)}>
          <DynamicTable headers={detailHeaders} data={details}/>
          </PopUp>
    </div>
    <div id="dashboard-table">
    <table>
        <thead>
        <tr>
    {headers && headers.map((header,index)=>(
        
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
        dashData && dashData.map((adjustment)=>
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
            <button onClick={()=>submitRequest(adjustment?.adjustmentId,"ACCEPT")}>Approve</button>
            <button onClick={()=>submitRequest(adjustment?.adjustmentId,"REJECT")} >Reject</button>
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