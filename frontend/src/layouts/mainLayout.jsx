import Sidebar from "./sideBar";
import React from 'react';

const MainLayout = ({ children }) => {
  return (
    <div className="d-flex" style={{ height: "100vh", overflow: "hidden" }}>
      <Sidebar />
      <div
        className="p-4 flex-grow-1 w-100"
        style={{
          overflowY: "auto",
        }}
      >
        {children}
      </div>
    </div>
  );
};

export default MainLayout;
