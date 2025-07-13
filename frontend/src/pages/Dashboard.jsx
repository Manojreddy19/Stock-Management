import React, { useEffect, useState } from "react";
import DashboardTable from "../components/DashboardTable";
import fetchData from "../components/productstesting";

const Dashboard = () => {
  const [adjustments, setAdjustments] = useState([]);

  const fetchdata = async () => {
    let data = await fetchData("http://192.168.0.219:8080/api/getAdjustments", { withCredentials: true }
    );
    setAdjustments(data);
  };

  useEffect(() => {
    fetchdata();
  }, []);

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

  return <DashboardTable headers={Headers} data={adjustments} />;
};

export default Dashboard;


// console.log(data);
//     const filtered = [];
//     for (let i = 0; i < data.length; i++) {
//       if (data[i].status === 'OPEN') {
//         filtered.push(data[i]);
//       }
//     }
//     console.log(filtered)