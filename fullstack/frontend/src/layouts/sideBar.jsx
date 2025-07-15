import React, { useState } from "react";
import { Nav } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Sidebar = () => {
  const [showStockUpSubmenu, setShowStockUpSubmenu] = useState(false);
  const [showStockDownSubmenu, setShowStockDownSubmenu] = useState(false);
  const navigate = useNavigate();

  return (
    <div
      className="d-flex flex-column vh-100 p-3 bg-dark text-white"
      style={{ width: "250px" }}
    >
      <h4 className="mb-4">Dashboard</h4>
      <Nav className="flex-column">

        {/* Stock Up Section */}
        <Nav.Link
          onClick={() => setShowStockUpSubmenu((prev) => !prev)}
          className="text-white d-flex justify-content-between align-items-center"
          style={{ cursor: "pointer" }}
        >
          <span>Stock UP</span>
          <span>{showStockUpSubmenu ? "▲" : "▼"}</span>
        </Nav.Link>
        {showStockUpSubmenu && (
          <div className="ms-3">
            <Nav.Link
              onClick={() => navigate("/adjustment-form?type=UP")}
              className="text-white-50"
              style={{ cursor: "pointer" }}
            >
              ➤ Create
            </Nav.Link>
            <Nav.Link
              onClick={() => navigate("/dashboard")}
              className="text-white-50"
              style={{ cursor: "pointer" }}
            >
              ➤ Dashboard
            </Nav.Link>
          </div>
        )}

        {/* Stock Down Section */}
        <Nav.Link
          onClick={() => setShowStockDownSubmenu((prev) => !prev)}
          className="text-white d-flex justify-content-between align-items-center mt-2"
          style={{ cursor: "pointer" }}
        >
          <span>Stock Down</span>
          <span>{showStockDownSubmenu ? "▲" : "▼"}</span>
        </Nav.Link>
        {showStockDownSubmenu && (
          <div className="ms-3">
            <Nav.Link
              onClick={() => navigate("/adjustment-form?type=DOWN")}
              className="text-white-50"
              style={{ cursor: "pointer" }}
            >
              ➤ Create
            </Nav.Link>
            <Nav.Link
              onClick={() => navigate("/dashboard")}
              className="text-white-50"
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
