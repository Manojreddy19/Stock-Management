import React, { useEffect, useState } from "react";
import DashboardTable from "../components/DashboardTable";
// import fetchData from "../components/productstesting";
import { ip } from '../assets/utils';
import '../styles/dashborad.css'
const Dashboard = () => {
  // const [adjustments, setAdjustments] = useState([]);

  // const fetchdata = async () => {
  //   let data = await fetchData(`http://${ip}:8080/api/getAdjustments`, { withCredentials: true }
  //   );
  //   console.log("called")
  //   setAdjustments(data);
  // };

  // useEffect(() => {
  //   fetchdata();
  // }, []);

  const Headers = [
    "adjustmentId",
    "adjustmentType",
    "amount",
    "status",
    "remarks",
    "createdBy",
    "createdAt",
    "modifiedBy",
    "modifiedAt",
  ];

  return (
    <div id="dashboard-container">
  
  <DashboardTable headers={Headers} />

  </div>
);
};

export default Dashboard;