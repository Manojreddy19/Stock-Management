import React, { useState } from "react";
import { Nav } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Sidebar = () => {
  const [showStockUpSubmenu, setShowStockUpSubmenu] = useState(false);
  const [showStockDownSubmenu, setShowStockDownSubmenu] = useState(false);
  const navigate = useNavigate();

  return (
    <div
      className="d-flex flex-column vh-100 p-3  text-black"
      style={{ width: "250px" }}
    >
      <h4 className="mb-4">Dashboard</h4>
      <Nav className="flex-column">


        <Nav.Link
          onClick={() => setShowStockUpSubmenu((prev) => !prev)}
          className="text-black d-flex justify-content-between align-items-center"
          style={{ cursor: "pointer" }}
        >
          <span>Stock UP</span>
          <span>{showStockUpSubmenu ? "▲" : "▼"}</span>
        </Nav.Link>
        {showStockUpSubmenu && (
          <div className="ms-3">
            <Nav.Link
              onClick={() => navigate("/adjustment-form-up")}
              className="text-black"
              style={{ cursor: "pointer" }}
            >
              ➤ Create
            </Nav.Link>
            <Nav.Link
              onClick={() => navigate("/dashboard")}
              className="text-black"
              style={{ cursor: "pointer" }}
            >
              ➤ Dashboard
            </Nav.Link>
          </div>
        )}


        <Nav.Link
          onClick={() => setShowStockDownSubmenu((prev) => !prev)}
          className="text-black d-flex justify-content-between align-items-center mt-2"
          style={{ cursor: "pointer" }}
        >
          <span>Stock Down</span>
          <span>{showStockDownSubmenu ? "▲" : "▼"}</span>
        </Nav.Link>
        {showStockDownSubmenu && (
          <div className="ms-3">
            <Nav.Link
              onClick={() => navigate("/adjustment-form-down")}
              className="text-black"
              style={{ cursor: "pointer" }}
            >
              ➤ Create
            </Nav.Link>
            <Nav.Link
              onClick={() => navigate("/dashboard?type=DOWN")}
              className="text-black"
              style={{ cursor: "pointer" }}
            >
              ➤ Dashboard
            </Nav.Link>
          </div>
        )}
        
      </Nav>
    </div>
  );
};

export default Sidebar;
