import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import DynamicTable from '../components/DynamicTable';
import { ToastContainer, toast } from 'react-toastify';
import '../components/style.css';
import axios from 'axios';
import { ip } from '../assets/utils';

const AdjustmentForm = () => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const adjustmentType = queryParams.get('type'); // "U" or "D"
  const user = JSON.parse(localStorage.getItem("user"));
  const createdBy = user?.username || "unknown";

  const [formData, setFormData] = useState({
    productId: '',
    batchDetails: '',
    quantity: '',
    expiryDate: '',
    mrp: '',
    amount: ''
  });

  const [amount, setamount] = useState(0);
  const [adjustments, setAdjustments] = useState([]);
  const [fetchedData, setFetchedData] = useState([]);
  const [batchArray, setBatchArray] = useState([]);
  const [stockData, setStockData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [availableQty, setAvailableQty] = useState(null);
const [quantityError, setQuantityError] = useState(false);

  const headers = [
    "productId",
    "batchDetails",
    "quantity",
    "expiryDate",
    "mrp",
    "amount"
  ];

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://${ip}:8080/api/getStocks`,{
          withCredentials: true,
        });
        if (!response.ok) {
          throw new Error(`HTTP error! : ${response.status}`);
        }
        const result = await response.json();
        setFetchedData(result);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const submitAdjustmentetails = async () => {
  const payload = {
    adjustmentType,
    amount,
    createdBy: createdBy,
    adjustmentDetails: adjustments.map(item => {
      const [batch, batchId] = item.batchDetails.split('-');
      return {
        productId: item.productId,
        batch,
        batchId,
        quantity: item.quantity,
        expiryDate: item.expiryDate,
        mrp: item.mrp,
        amount: item.amount
      };
    })
  };

  console.log("Submitting payload:", payload);
  

  

try {
  const response = await axios.post(
    `http://${ip}:8080/api/addAdjustment`,
    payload,
    {
      headers: {
        'Content-Type': 'application/json'
      },
      withCredentials: true
    }
  );

  if (response.status === 201) {
    toast.success(response.data.message);
    setAdjustments([]);
    setamount(0);
    console.log(response.data);
  } else {
    toast.error("Submission failed");
    console.log(response);
  }
} catch (error) {
  if (error.response) {
    // Server responded with a non-2xx status
    console.log("Backend error response:", error.response.data);
    toast.error("Submission failed: " + error.response.data?.message || "Unknown error");
  } else {
    // Network or other error
    console.error("Error submitting adjustment:", error);
    toast.error("Error occurred while submitting");
  }
}

};


  const handleChange = (e) => {
  const { name, value } = e.target;

  setFormData((prev) => {
    const updated = {
      ...prev,
      [name]: value,
    };

    updated.amount = updated.mrp && updated.quantity
      ? (updated.mrp * updated.quantity).toFixed(2)
      : '';

    // Validate quantity for stock down
    if (name === 'quantity' && adjustmentType === 'DOWN') {
      const enteredQty = parseInt(value, 10);
      if (availableQty !== null && enteredQty > availableQty) {
        setQuantityError(true);
      } else {
        setQuantityError(false);
      }
    }

    return updated;
  });
};





  const validateFields = (e) => {
    e.preventDefault();

    const newAdjustment = { ...formData };
    setAdjustments((prev) => [...prev, newAdjustment]);
    setamount((prev) => prev + parseFloat(newAdjustment.amount || 0));
    toast("Added");

    setFormData({
      productId: '',
      batchDetails: '',
      quantity: '',
      expiryDate: '',
      mrp: '',
      amount: ''
    });
    setBatchArray([]);
    setAvailableQty(null)
  };

  const filterByProduct = (e) => {
    const value = e.target.value.toLowerCase();
    setFormData((prev) => ({ ...prev, productId: value }));

    const filteredArray = fetchedData.filter(item =>
      item.productId.toLowerCase() === value
    );

    setStockData(filteredArray);
    setBatchArray(filteredArray);
  };

  const filterBybatchId = (e) => {
  const value = e.target.value;
  setFormData((prev) => ({ ...prev, batchDetails: value }));

  const filteredArray = stockData?.filter(item => {
    const pattern = `${item.batch.toLowerCase()}-${item.batchId}`;
    return pattern.includes(value.toLowerCase());
  }) || [];

  if (filteredArray.length === 1) {
    const batch = filteredArray[0];
    setFormData((prev) => ({
      ...prev,
      batchDetails: `${batch.batch}-${batch.batchId}`,
      expiryDate: batch.expiryDate,
      mrp: batch.mrp,
      amount: (batch.mrp * (prev.quantity || 0)).toFixed(2),
    }));
    setAvailableQty(batch.quantity);
  }

  setBatchArray(filteredArray);
};


  return (
    <>
      <ToastContainer autoClose={1000} limit={1} />

      <h2>Stock Adjustment: {adjustmentType === 'U' ? 'Stock Up' : 'Stock Down'}</h2>
      <div id="adjustment-form-container">
      <form id="adjustmentForm" onSubmit={validateFields} autoComplete="off">
        <label htmlFor="product_id">Product ID
          <input
            type="text"
            list="productChoices"
            name="productId"
            id="product_id"
            value={formData.productId}
            onChange={filterByProduct}
            required
            autoComplete="off"
          />
          <datalist id="productChoices">
            {[...new Set(fetchedData.map(item => item.productId))].map((productId, idx) => (
              <option key={idx} value={productId} />
            ))}
          </datalist>
        </label>

        <label htmlFor="batch_details">Batch-BatchId
          <input
            type="text"
            name="batchDetails"
            id="batch_details"
            list="batchChoices"
            value={formData.batchDetails}
            onChange={filterBybatchId}
            required
            autoComplete="off"
          />
          <datalist id="batchChoices">
            {batchArray && batchArray.map((item) => (
              <option key={item.batchId} value={`${item.batch}-${item.batchId}`} />
            ))}
          </datalist>
        </label>

        {adjustmentType === 'D' && availableQty !== null && (
  <div style={{ color: 'blue', fontWeight: 'bold' }}>
    Available Quantity: {availableQty}
  </div>
)}

        <label htmlFor="quantity">Quantity
  <input
    type="number"
    name="quantity"
    id="quantity"
    value={formData.quantity}
    onChange={handleChange}
    required
    style={{
      borderColor: quantityError ? 'red' : '',
      outlineColor: quantityError ? 'red' : ''
    }}
  />
  {quantityError && (
    <span className="warning-text">
  Quantity exceeds available stock. Available: {availableQty}
</span>
  )}
</label>


        <label htmlFor="expiry_date">Expiry Date
          <input
            type="date"
            name="expiryDate"
            id="expiry_date"
            value={formData.expiryDate}
            readOnly
            required
          />
        </label>

        <label htmlFor="mrp">MRP
          <input
            type="number"
            step="0.001"
            name="mrp"
            id="mrp"
            value={formData.mrp}
            onChange={handleChange}
            required
          />
        </label>

        <label htmlFor="amount">Amount
          <input
            type="number"
            step="0.001"
            name="amount"
            id="amount"
            value={formData.amount}
            readOnly
            required
          />
        </label>

        <button name="submit" type="submit">Add</button>
      </form>
      </div>

      <h3>Added Adjustments</h3>
      <DynamicTable headers={headers} data={adjustments} amount={amount} />
      <button onClick={submitAdjustmentetails} name="Submit">Submit</button>
    </>
  );
};

export default AdjustmentForm;
