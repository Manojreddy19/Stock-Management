import DynamicTable from "./DynamicTable";
import { useEffect, useState } from "react";
import PopUp from "./PopUp";
import axios from "axios";
import Table from "react-bootstrap/Table";
import { ip } from "../assets/utils.js";
import { useLocation } from "react-router-dom";
import "../styles/DashboardTable.css";

const DashboardTable = ({ headers, data, fetchData }) => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const adjustmentType = queryParams.get("type")?.toUpperCase() || "UP"; // default to "UP"

  const [dashData, setDashData] = useState([]);
  const [showPopUp, setShowPopUp] = useState(false);
  const [details, setDetails] = useState([]);
  const [status, setStatus] = useState("OPEN");

  const user = JSON.parse(localStorage.getItem("user"));
  const modifiedBy = user?.username || "unknown";

  useEffect(() => {
    filterAdjustmentsByStatus("OPEN");
  }, [data, adjustmentType]);

  const filterAdjustmentsByStatus = (status) => {
    const filteredData = data?.filter(
      (adjustment) =>
        adjustment?.status === status &&
        adjustment?.adjustmentType === adjustmentType
    );
    setStatus(status);
    setDashData(filteredData || []);
  };

  const submitRequest = async (adjustmentId, status) => {
    const payload = {
      adjustmentId,
      status,
      modifiedBy,
    };

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

      console.log("Status updated successfully", response?.data);
      fetchData();
      filterAdjustmentsByStatus("OPEN");
    } catch (error) {
      console.error("Failed to update status", error);
    }
  };

  const fixIdAndStatus = async (Details) => {
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

  return (
    <div>
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
      </div>

      <div id="model">
        <PopUp showPopUp={showPopUp} closePopUp={() => setShowPopUp(false)}>
          <DynamicTable headers={detailHeaders} data={details} />
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
            {dashData?.map((adjustment) => (
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
                            fixIdAndStatus(adjustment?.adjustmentDetails)
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
                      onClick={() =>
                        submitRequest(adjustment?.adjustmentId, "REJECT")
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
    </div>
  );
};

export default DashboardTable;
