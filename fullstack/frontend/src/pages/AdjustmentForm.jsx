// Refactored AdjustmentForm.js with improved UI/UX, Bootstrap styling, and better UX patterns

import 'bootstrap/dist/css/bootstrap.min.css';
import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import DynamicTable from '../components/DynamicTable';
import { ip } from '../assets/utils';

const AdjustmentForm = () => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const adjustmentType = queryParams.get("type");
  const user = JSON.parse(localStorage.getItem("user"));
  const createdBy = user?.username || "unknown";

  const [formData, setFormData] = useState({
    productId: "",
    batchDetails: "",
    quantity: "",
    expiryDate: "",
    mrp: "",
    amount: "",
  });

  const [adjustments, setAdjustments] = useState([]);
  const [amount, setAmount] = useState(0);
  const [fetchedData, setFetchedData] = useState([]);
  const [availableQty, setAvailableQty] = useState(null);
  const [quantityError, setQuantityError] = useState(false);

  const [productSuggestions, setProductSuggestions] = useState([]);
  const [batchSuggestions, setBatchSuggestions] = useState([]);
  const [activeProductIndex, setActiveProductIndex] = useState(-1);
  const [activeBatchIndex, setActiveBatchIndex] = useState(-1);

  const headers = ["productId", "batchDetails", "quantity", "expiryDate", "mrp", "amount"];

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://${ip}:8080/api/getStocks`, {
          credentials: 'include'
        });
        const result = await response.json();
        setFetchedData(result);
      } catch (error) {
        toast.error("Failed to fetch stock data");
      }
    };
    fetchData();
  }, []);

  const allProductIds = [...new Set(fetchedData.map(item => item.productId))];

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => {
      const updated = { ...prev, [name]: value };
      if (updated.mrp && updated.quantity) {
        updated.amount = (parseFloat(updated.mrp) * parseFloat(updated.quantity)).toFixed(2);
      }
      if (name === "quantity" && adjustmentType === "DOWN") {
        const enteredQty = parseInt(value, 10);
        setQuantityError(availableQty !== null && enteredQty > availableQty);
      }
      return updated;
    });
  };

  const handleAdd = (e) => {
    e.preventDefault();
    const { productId, batchDetails, quantity, amount } = formData;
    if (!productId || !batchDetails || !quantity || !amount) return toast.error("All fields are required");

    if (adjustmentType === 'DOWN' && parseInt(quantity) > availableQty) {
      return toast.error("Quantity exceeds available stock");
    }

    if (adjustments.some(item => item.productId === productId.toUpperCase() && item.batchDetails === batchDetails)) {
      return toast.error("Duplicate record");
    }

    const newEntry = { ...formData, productId: productId.toUpperCase() };
    setAdjustments(prev => [...prev, newEntry]);
    setAmount(prev => prev + parseFloat(amount));
    toast.success("Added successfully");
    resetForm();
  };

  const resetForm = () => {
    setFormData({ productId: "", batchDetails: "", quantity: "", expiryDate: "", mrp: "", amount: "" });
    setAvailableQty(null);
    setProductSuggestions([]);
    setBatchSuggestions([]);
  };

  const handleProductChange = (e) => {
    const value = e.target.value;
    setFormData(prev => ({ ...prev, productId: value }));
    const filtered = allProductIds.filter(id => id.toLowerCase().includes(value.toLowerCase()));
    setProductSuggestions(filtered);
  };

  const selectProduct = (productId) => {
    const matched = fetchedData.filter(item => item.productId === productId);
    setFormData(prev => ({ ...prev, productId }));
    setProductSuggestions([]);
    setBatchSuggestions(matched.map(item => `${item.batch}-${item.batchId}`));
  };

  const handleBatchSelect = (batchDetail) => {
    const match = fetchedData.find(item => `${item.batch}-${item.batchId}` === batchDetail);
    if (match) {
      setFormData(prev => ({
        ...prev,
        batchDetails: batchDetail,
        expiryDate: match.expiryDate,
        mrp: match.mrp,
        amount: match.mrp * (prev.quantity || 0)
      }));
      setAvailableQty(match.quantity);
    }
    setBatchSuggestions([]);
  };

  const submitAdjustments = async () => {
    const payload = {
      adjustmentType,
      amount,
      createdBy,
      adjustmentDetails: adjustments.map(item => {
        const [batch, batchId] = item.batchDetails.split("-");
        return { ...item, batch, batchId };
      }),
    };

    try {
      const res = await axios.post(`http://${ip}:8080/api/addAdjustment`, payload, {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: true
      });
      if (res.status === 201) {
        toast.success(res.data.message);
        setAdjustments([]);
        setAmount(0);
      }
    } catch (err) {
      toast.error("Submission failed: " + (err?.response?.data?.message || "Unknown error"));
    }
  };

  return (
    <div className="container mt-5">
      <ToastContainer autoClose={1500} limit={1} />
      <h3 className="mb-4">Stock Adjustment: {adjustmentType === "UP" ? "Increase" : "Decrease"}</h3>

      <form autoComplete='off' onSubmit={handleAdd} className="row g-3">
        <div className="col-md-4 position-relative">
          <label className="form-label">Product ID</label>
          <input className="form-control" name="productId" value={formData.productId} onChange={handleProductChange} required />
          {productSuggestions.length > 0 && (
            <ul className="list-group position-absolute w-100 zindex-dropdown">
              {productSuggestions.map((item, index) => (
                <li key={index} className="list-group-item list-group-item-action" onClick={() => selectProduct(item)}>{item}</li>
              ))}
            </ul>
          )}
        </div>

        <div className="col-md-4 position-relative">
          <label className="form-label">Batch ID</label>
          <input className="form-control" name="batchDetails" value={formData.batchDetails} onChange={(e) => setFormData({ ...formData, batchDetails: e.target.value })} required />
          {batchSuggestions.length > 0 && (
            <ul className="list-group position-absolute w-100 zindex-dropdown">
              {batchSuggestions.map((item, index) => (
                <li key={index} className="list-group-item list-group-item-action" onClick={() => handleBatchSelect(item)}>{item}</li>
              ))}
            </ul>
          )}
        </div>

        <div className="col-md-2">
          <label className="form-label">Quantity</label>
          <input type="number" name="quantity" className={`form-control ${quantityError ? 'is-invalid' : ''}`} value={formData.quantity} onChange={handleInputChange} required />
          {quantityError && <div className="invalid-feedback">Quantity exceeds available: {availableQty}</div>}
        </div>

        <div className="col-md-2">
          <label className="form-label">Expiry</label>
          <input type="date" className="form-control" value={formData.expiryDate} readOnly required />
        </div>

        <div className="col-md-2">
          <label className="form-label">MRP</label>
          <input type="number" step="0.01" name="mrp" className="form-control" value={formData.mrp} onChange={handleInputChange} required />
        </div>

        <div className="col-md-2">
          <label className="form-label">Amount</label>
          <input type="number" className="form-control" value={formData.amount} readOnly />
        </div>

        <div className="col-12">
          <button type="submit" className="btn btn-primary">Add</button>
        </div>
      </form>

      <hr className="my-4" />

      <DynamicTable headers={headers} data={adjustments} amount={amount} />

      <div className="text-end">
        <button className="btn btn-success mt-3" onClick={submitAdjustments}>Submit Adjustments</button>
      </div>
    </div>
  );
};

export default AdjustmentForm;
