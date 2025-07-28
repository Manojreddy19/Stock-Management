import "bootstrap/dist/css/bootstrap.min.css";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import DynamicTable from "../components/DynamicTable";
import { ip } from "../assets/utils";

const StockDownAdjustmentForm = () => {
  const adjustmentType = "DOWN";
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
  const [productIds, setProductIds] = useState([]);
  const [productIdSuggestions, setProductIdSuggestions] = useState([]);
  const [batchIds, setBatchIds] = useState([]);
  const [batchSuggestions, setBatchSuggestions] = useState([]);
  const [stock, setStock] = useState({});
  const [amount, setAmount] = useState(0);
  const [availableQty, setAvailableQty] = useState(null);
  const [quantityError, setQuantityError] = useState(false);
  const [showProductSuggestions, setShowProductSuggestions] = useState(false);
  const [showBatchSuggestions, setShowBatchSuggestions] = useState(false);

  const headers = [
    "productId",
    "batch",
    "batchId",
    "quantity",
    "expiryDate",
    "mrp",
    "amount",
  ];

const fetchData = async (value) => {
      try {
        const response = await fetch(`http://${ip}:8080/api/getProductIds?isRequired=false&productId=${value}`, {
          credentials: "include",
        });
        const result = await response.json();
        setProductIdSuggestions(result);
      } catch (error) {
        console.error(error);
        toast.error("Failed to fetch product IDs", { position: "bottom-left" });
      }
    };

  let callTime;

  const handleProductChange = (e) => {
    const value = e.target.value;
    setFormData((prev) => ({ ...prev, productId: value }));

    clearTimeout(callTime);

    callTime= setTimeout(
      ()=>{
        fetchData(value)
      },300
    )
    
  };

  const fetchBatchIds = async (productId) => {
    try {
      const response = await axios.get(
        `http://${ip}:8080/api/getBatches?productId=${productId}`
      );
      const entries = Object.entries(response.data);
      setBatchIds(entries);
      setBatchSuggestions(entries);
      return true;
    } catch (err) {
      toast.error("Failed to fetch batches", { position: "bottom-left" });
      return false;
    }
  };

  const resetForm = () => {
    setFormData({
      productId: "",
      batchDetails: "",
      quantity: "",
      expiryDate: "",
      mrp: "",
      amount: "",
    });
    setAvailableQty(null);
    setProductIdSuggestions([]);
    setBatchSuggestions([]);
    setQuantityError(false);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => {
      const updated = { ...prev, [name]: value };

      if (name === "quantity") {
        const enteredQty = parseFloat(value);
        if (
          !isNaN(enteredQty) &&
          availableQty !== null &&
          enteredQty > availableQty
        ) {
          setQuantityError(true);
        } else {
          setQuantityError(false);
        }
      }

      if (!isNaN(updated.mrp) && !isNaN(updated.quantity)) {
        updated.amount = (
          parseFloat(updated.mrp) * parseFloat(updated.quantity)
        ).toFixed(2);
      } else {
        updated.amount = "";
      }

      return updated;
    });
  };

  const handleAdd = (e) => {
    e.preventDefault();
    const { productId, batchDetails, quantity, amount } = formData;

    if (!productId || !batchDetails || !quantity || !amount) {
      return toast.error("All fields are required", {
        position: "bottom-left",
      });
    }

    const [batch, batchId] = batchDetails.split("-");

    const newEntry = {
      productId,
      batch: batch.trim(),
      batchId: batchId.trim(),
      quantity,
      expiryDate: formData.expiryDate,
      mrp: formData.mrp,
      amount,
    };

    setAdjustments((prev) => [...prev, newEntry]);
    setAmount((prev) => prev + parseFloat(amount));
    toast.success("Added successfully", { position: "bottom-left" });
    resetForm();
  };



  const handleBatchIdsChange = (e) => {
    const value = e.target.value;
    setFormData((prev) => ({ ...prev, batchDetails: value }));
    const filtered = batchIds.filter(
      ([id, name]) =>
        id.toLowerCase().includes(value.toLowerCase()) ||
        name.toLowerCase().includes(value.toLowerCase())
    );
    setBatchSuggestions(filtered);
  };

  const selectProduct = async (productId) => {
    setFormData((prev) => ({ ...prev, productId }));
    await fetchBatchIds(productId);
    setProductIdSuggestions([]);
  };

  const handleBatchSelect = async (batchId) => {
    const selected = batchIds.find(([id]) => id === batchId);
    if (!selected) return;

    const [id, batchName] = selected;

    try {
      const res = await axios.get(
        `http://${ip}:8080/api/getStockDetail?productId=${formData.productId}&batchId=${id}`
      );
      const stockData = res.data;
      setStock(stockData);

      const quantity = parseFloat(formData.quantity);
      const mrp = parseFloat(stockData.mrp);
      const computedAmount =
        !isNaN(quantity) && !isNaN(mrp) ? (mrp * quantity).toFixed(2) : "";

      setFormData((prev) => ({
        ...prev,
        batchDetails: `${batchName}-${id}`,
        expiryDate: stockData.expiryDate,
        mrp: stockData.mrp,
        amount: computedAmount,
      }));

      setAvailableQty(stockData.quantity);
      setBatchSuggestions([]);
    } catch (err) {
      toast.error("Failed to fetch stock details", { position: "bottom-left" });
    }
  };

  const submitAdjustments = async () => {
    if (adjustments.length === 0) {
      toast.error("Please add adjustments before submitting", {
        position: "bottom-left",
      });
      return;
    }

    const payload = {
      adjustmentType,
      amount,
      createdBy,
      adjustmentDetails: adjustments,
    };

    try {
      const res = await axios.post(
        `http://${ip}:8080/api/addAdjustment`,
        payload,
        {
          headers: { "Content-Type": "application/json" },
          withCredentials: true,
        }
      );

      if (res.status === 201) {
        toast.success(res.data.message, { position: "bottom-left" });
        setAdjustments([]);
        setAmount(0);
      }
    } catch (err) {
      toast.error(
        "Submission failed: " +
          (err?.response?.data?.message || "Unknown error"),
        { position: "bottom-left" }
      );
    }
  };

  return (
    <div className="container-fluid pb-5">
      <ToastContainer autoClose={1500} limit={1} />
      <h3 className="mb-3">
        Stock Adjustment: {adjustmentType === "UP" ? "Increase" : "Decrease"}
      </h3>

      <form autoComplete="off" onSubmit={handleAdd} className="row g-3">
        <div className="col-md-3 position-relative">
          <label className="form-label">Product ID</label>
          <input
            className="form-control form-control-sm"
            name="productId"
            value={formData.productId}
            onChange={handleProductChange}
            onFocus={() => setShowProductSuggestions(true)}
            onBlur={() =>
              setTimeout(() => setShowProductSuggestions(false), 150)
            }
            required
          />
          {showProductSuggestions && productIdSuggestions.length > 0 && (
            <ul className="list-group position-absolute w-100 z-index-dropdown">
              {productIdSuggestions.map((item, index) => (
                <li
                  key={index}
                  className="list-group-item list-group-item-action"
                  onClick={() => selectProduct(item)}
                >
                  {item}
                </li>
              ))}
            </ul>
          )}
        </div>

        <div className="col-md-3 position-relative">
          <label className="form-label">Batch ID</label>
          <input
            className="form-control form-control-sm"
            name="batchDetails"
            value={formData.batchDetails}
            onChange={handleBatchIdsChange}
            onFocus={() => setShowBatchSuggestions(true)}
            onBlur={() => setTimeout(() => setShowBatchSuggestions(false), 150)}
            required
          />
          {showBatchSuggestions && batchSuggestions.length > 0 && (
            <ul className="list-group position-absolute w-100 z-index-dropdown">
              {batchSuggestions.map(([batchId, batchName], index) => (
                <li
                  key={index}
                  className="list-group-item list-group-item-action"
                  onClick={() => handleBatchSelect(batchId)}
                >
                  {`${batchName} - ${batchId}`}
                </li>
              ))}
            </ul>
          )}
        </div>

        <div className="col-md-2">
          <label className="form-label">Quantity</label>
          <input
            type="number"
            name="quantity"
            className={`form-control form-control-sm ${
              quantityError ? "is-invalid" : ""
            }`}
            value={formData.quantity}
            onChange={handleInputChange}
            required
          />
          {quantityError && (
            <div className="invalid-feedback">
              Quantity exceeds available: {availableQty}
            </div>
          )}
        </div>

        <div className="col-md-2">
          <label className="form-label">Expiry</label>
          <input
            type="date"
            className="form-control form-control-sm"
            value={formData.expiryDate}
            readOnly
            required
          />
        </div>

        <div className="col-md-2">
          <label className="form-label">MRP</label>
          <input
            type="number"
            step="0.01"
            name="mrp"
            className="form-control form-control-sm"
            value={formData.mrp}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="col-md-2">
          <label className="form-label">Amount</label>
          <input
            type="number"
            className="form-control form-control-sm"
            value={formData.amount || ""}
            readOnly
          />
        </div>

        <div className="col-md-2 d-flex align-items-end">
          <button type="submit" className="btn btn-sm btn-primary w-100">
            Add
          </button>
        </div>
      </form>

      <hr className="my-3" />

      <div
        className="table-responsive"
        style={{ maxHeight: "300px", overflowY: "auto" }}
      >
        <DynamicTable headers={headers} data={adjustments} amount={amount} />
      </div>

      <div className="fixed-bottom bg-white border-top p-3 text-end shadow">
        <button className="btn btn-success" onClick={submitAdjustments}>
          Submit Adjustments
        </button>
      </div>
    </div>
  );
};

export default StockDownAdjustmentForm;
