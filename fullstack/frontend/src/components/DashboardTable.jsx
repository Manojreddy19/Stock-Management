import DynamicTable from "./DynamicTable";
import { useEffect, useState } from "react";
import PopUp from "./PopUp";
import axios from "axios";
import Table from "react-bootstrap/Table";
import { ip } from "../assets/utils.js";
import { useLocation } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Pagination } from "./Pagination.jsx";
import React from "react";
import { SearchCriteriaForm } from "./SearchCriteriaFrom.jsx";

const DashboardTable = ({ headers }) => {
  const location = useLocation();
  const [data, setData] = useState([]);
  const queryParams = new URLSearchParams(location.search);
  const adjustmentType = queryParams.get("type")?.toUpperCase() || "UP";
  const [page, setPage] = useState(0);
  const [dashData, setDashData] = useState([]);
  const [response, setResponse] = useState({
    adjustmentId: "",
    status: "",
    remark: "",
  });
  const [showPopUp, setShowPopUp] = useState(false);
  const [remarkPopUp, setRemarkPopUp] = useState(false);
  const [details, setDetails] = useState([]);
  const [status, setStatus] = useState("OPEN");
  const [showSearchCriteria, setShowSearchCriteria] = useState(false);
  const [search, setSearch] = useState(false);
  const [totalNoOfPages, setTotalNoOfPages] = useState(0);

  const user = JSON.parse(localStorage.getItem("user"));
  const modifiedBy = user?.username || "unknown";

  useEffect(() => {
    filterAdjustmentsByStatus("OPEN");
  }, [data, adjustmentType]);

  /// DONE FETCHING TOTAL RECORDS

  const params = {
    status: status,
    adjustmentType: "UP",
    noOfRows: 10,
    limitFrom: page*10,
  };
  const parameters = { status: status, adjustmentType: "UP" };

  const [totalRecords, setTotalRecords] = useState(0);

  const [formData, setFormData] = useState({
    adjustmentId: 0,
    status: null,
    createdFrom: null,
    createdTo: null,
    adjustmentType: null,
  });

  useEffect(() => {
    axios
      .post("http://localhost:8080/api/getAdjustmentCount", parameters)
      .then((res) => {
        setTotalRecords(res.data);
        setTotalNoOfPages(Math.ceil(totalRecords / 10));
      }) /*.then(console.log("totalRecords: ", totalRecords))*/;
  }, [status, formData, search]);

  useEffect(() => {
    axios
      .post("http://localhost:8080/api/getAdjustmentsByCriteria", params)
      .then((res) => {
        setDashData(res.data);
      })
      .then(() =>
        console.log("my data", dashData, params.noOfRows, params.page)
      );
  }, [status, page]);

  useEffect(() => {
    axios
      .post("http://localhost:8080/api/getAdjustmentsByCriteria", formData)
      .then((res) => {
        setDashData(res.data);
      })
      .then(() => {
        console.log("data", dashData);
      });
  }, [formData, search]);

  // console.log("page rerendered")

  const filterAdjustmentsByStatus = (status) => {
    // console.log(dashData)
    params.status = status;
    // console.log(params.status)
    setStatus(status);
    setPage(0);
  };

  const submitRequest = async (adjustmentId, status, remarks = null) => {
    const payload = {
      adjustmentId,
      status,
      modifiedBy,
      remarks,
    };
    // console.log(payload);

    try {
      const response = await axios.post(
        `http://${ip}:8080/api/updateStatus`,
        payload,
        {
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      // console.log("Status updated successfully", response?.data);
      // console.log(response)
      toast.success("Status updated successfully", { position: "bottom-left" });

      // fetchData();
      filterAdjustmentsByStatus("OPEN");
    } catch (error) {
      console.error("Failed to update status", error);
      toast.error("Failed to update status", { position: "bottom-left" });
    }
  };

  const setAdjustmentsAndStatus = async (Details) => {
    if (Details.length > 0) {
      setDetails(Details);
      setShowPopUp(true);
    }
  };

  const detailHeaders = [
    "adjustmentId",
    "productId",
    "batch",
    "batchId",
    "quantity",
    "mrp",
    "expiryDate",
    "amount",
    "generatedBatchId",
  ];
  const handleChange = (e) => {
    const value = e.target.value;
    setResponse({ ...response, remark: value });
  };

  const submitRemark = () => {
    setRemarkPopUp(false);
    submitRequest(response.adjustmentId, response.status, response.remark);
    setResponse({
      adjustmentId: "",
      status: "",
      remark: "",
    });
  };
  const addRemarkAndSend = (adjustment, stat) => {
    setRemarkPopUp(true);
    setResponse({ ...response, adjustmentId: adjustment, status: stat });
  };

  const searchCriteriaForm = () => {
    setShowSearchCriteria(true);
    // console.log(search)
  };

  return (
    <div>
      <ToastContainer autoClose={1500} limit={1} />
      <h4 className="mb-4">
        {adjustmentType === "UP" ? "Stock Up" : "Stock Down"} Dashboard
      </h4>

      <div id="buttons" className="mb-3">
        <button
          style={{ border: "none", marginRight: "10px" }}
          onClick={() => filterAdjustmentsByStatus("OPEN")}
        >
          OPEN
        </button>
        <button
          style={{ border: "none", marginRight: "10px" }}
          onClick={() => filterAdjustmentsByStatus("ACCEPT")}
        >
          Accepted
        </button>
        <button
          style={{ border: "none" }}
          onClick={() => filterAdjustmentsByStatus("REJECT")}
        >
          Rejected
        </button>
        <button onClick={searchCriteriaForm}>search</button>
        {showSearchCriteria && (
          <SearchCriteriaForm
            setSearch={setSearch}
            formData={formData}
            setFormData={setFormData}
          />
        )}
      </div>

      <div id="model">
        <PopUp showPopUp={showPopUp} closePopUp={() => setShowPopUp(false)}>
          <DynamicTable headers={detailHeaders} data={details} />
        </PopUp>
      </div>
      <div id="model1">
        <PopUp showPopUp={remarkPopUp} closePopUp={() => setRemarkPopUp(false)}>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              gap: "5px",
              border: "1px solid black",
            }}
          >
            <textarea
              id="w3review"
              name="w3review"
              rows="4"
              cols="50"
              onChange={handleChange}
              value={response.remark}
            />
            <button
              style={{ backgroundColor: "#008CBA" }}
              name="submit"
              onClick={submitRemark}
            >
              submit
            </button>
          </div>
        </PopUp>
      </div>

      <div className="table-container">
        <Table bordered responsive className="m-0">
          <thead className="table-header">
            <tr>
              {headers &&
                headers.map((header, index) =>
                  status === "REJECT" &&
                  (header === "modifiedBy" || header === "modifiedAt") ? (
                    <th key={index}>
                      {header
                        .replace("modified", "rejected")
                        .replace(/^\w/, (c) => c.toUpperCase())}
                    </th>
                  ) : status === "OPEN" &&
                    (header === "modifiedBy" ||
                      header === "modifiedAt") ? null : (
                    <th key={index}>
                      {header.charAt(0).toUpperCase() + header.slice(1)}
                    </th>
                  )
                )}
              {user.roles.includes("ADMIN") && status === "OPEN" && (
                <th>Actions</th>
              )}
            </tr>
          </thead>

          <tbody>
            {dashData &&
              dashData?.map((adjustment) => (
                <tr key={adjustment?.adjustmentId}>
                  {headers.map((header, index) =>
                    status === "OPEN" &&
                    (header === "modifiedBy" ||
                      header === "modifiedAt") ? null : (
                      <td key={index}>
                        {header === "adjustmentId" ? (
                          <button
                            style={{
                              border: "none",
                              backgroundColor: "white",
                              color: "blue",
                            }}
                            onClick={() =>
                              setAdjustmentsAndStatus(
                                adjustment?.adjustmentDetails
                              )
                            }
                          >
                            {adjustment[header]}
                          </button>
                        ) : (
                          adjustment[header]
                        )}
                      </td>
                    )
                  )}
                  {user.roles.includes("ADMIN") && status === "OPEN" && (
                    <td>
                      <button
                        className="btn btn-sm btn-success me-2"
                        onClick={() =>
                          submitRequest(adjustment?.adjustmentId, "ACCEPT")
                        }
                      >
                        Approve
                      </button>
                      <button
                        className="btn btn-sm btn-danger"
                        onClick={
                          () =>
                            addRemarkAndSend(adjustment?.adjustmentId, "REJECT")
                          // submitRequest(adjustment?.adjustmentId, "REJECT")
                        }
                      >
                        Reject
                      </button>
                    </td>
                  )}
                </tr>
              ))}
          </tbody>
        </Table>
      </div>
      <Pagination
        totalRecords={totalRecords}
        page={page}
        setPage={setPage}
        totalNoOfPages={totalNoOfPages}
      ></Pagination>
    </div>
  );
};

export default DashboardTable;
