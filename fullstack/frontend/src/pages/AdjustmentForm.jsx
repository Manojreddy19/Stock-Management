import {useEffect,   useState}from 'react'
import DynamicTable from '../components/DynamicTable';
import fetchdata from '../components/productstesting';
const AdjustmentForm = () => {
 //let stockData;
//  const getData= async()=>{
//   const Data= await fetchdata("http://10.129.241.140:8080/api/getStock");
//   stockData(Data);
//  };
// getData();
  const[formData,setFormData]=useState({
    productId:'',
    batchDetails:'',
    quantity:'',
    expiryDate:'',
    mrp:'',
    amount:''

});
console.log("rendering");
const[adjustmentType,setAdjustmentType]=useState("");

const[totalAmount,setTotalAmount]=useState(0);
const[adjustments,setAdjustments]=useState([]);

// useEffect(()=>{},[adjustments]);

  const handleChange = (e) => {
     e.preventDefault();
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };
  const addIntoAdjustmentList=(e)=>
  {
    e.preventDefault();
    formData.amount = parseFloat(formData.mrp || 0) * parseFloat(formData.quantity||0);
    setAdjustments([...adjustments,formData]);
    setTotalAmount(totalAmount+ parseFloat(formData.amount)||0);
    setFormData({
    productId:'',
    batchDetails:'',
    quantity:'',
    expiryDate:'',
    mrp:'',
    amount:''

});
  }
const headers=[
    "productId",
    "batchDetails",
    "quantity",
    "expiryDate",
    "mrp",
    "amount"];


const submitAdjustmentetails=()=>{
  const adjustmentsToSubmit = [adjustments,adjustmentType,totalAmount];
  console.log("Submitting adjustments:", adjustmentsToSubmit);
  setAdjustments([]);
  setTotalAmount(0);  
}
const adjustmentChange=(e)=>{
 e.preventDefault();
  setAdjustmentType(e.target.value);
}
  return (
    
    <>
    
    <form action="">

        <label >
        <input type="radio" name="adjustmentType" id="up" value="U" checked={adjustmentType==="U"}  onChange={adjustmentChange} required= {true} />Stock Up
        </label>
        <label >
        <input type="radio" name="adjustmentType" id="down" value="D"  checked={adjustmentType==="D"} onChange={adjustmentChange} required/>Stock Down
        </label>
        <label htmlFor="productId">product Id
        <input type="text" name="productId" id="productId"  value={formData.productId}  onChange={handleChange} required />
        </label>
        <label htmlFor="batchDetails">Batch-Batch Id
            <input type="text" name="batchDetails" id="batchDetails" value={formData.batchDetails}  onChange={handleChange} required />
        </label>
        <label htmlFor="quantity">
            quantity
            <input type="number" name="quantity" id="quantity" value={formData.quantity}  onChange={handleChange} required />
        </label>
        <label htmlFor="expiryDate">
            Expiry Date 
            <input type="date" name="expiryDate" id="expiryDate" value={formData.expiryDate}   onChange={handleChange} required/>
        </label>
        <label htmlFor="mrp">
            mrp
            <input type="number" step="0.001"  name="mrp" id="mrp"  value={formData.mrp}   onChange={handleChange} required />
        </label>
         <label htmlFor="amount">
            Amount
            <input type="number" step="0.001" name="amount" id="amount" value={formData.mrp * formData.quantity}   onChange={handleChange} required disabled />
        </label>
        <input type="button" onClick={(e)=>addIntoAdjustmentList(e)} value="Add" />
    </form>

    <h3>Added Adjustments </h3>

    <DynamicTable  headers={headers} data ={ adjustments} amount ={totalAmount}/>
    <button onClick={submitAdjustmentetails} name="Submit" >submit</button>
    </>
  )
}

export default AdjustmentForm




