import axios from "axios";
import { useState } from "react";
import React from "react";

export const SearchCriteriaForm = ({setSearch, formData, setFormData}) => {

    const adjustmentType = "UP";

    // const payload = {
    //     "adjustmentID": formData.adjustmentID,
    //     "adjustmentType": 'UP',
    //     "status": formData.status,
    //     "createdDateFrom": formData.createdDateFrom,
    //     "createdDateTo": formData.createdDateTo
    // }
    const formatDateToYYYYMMDD = (date) => {
        if (!date) return null;
        const d = new Date(date);
        if (isNaN(d.getTime())) return null;
        return d.toISOString().split("T")[0];
    };



    const submitFormData = (e) => {
        e.preventDefault();
        formData.adjustmentId = parseInt(e.target[0].value);
        formData.status = e.target[1].value == '' ? null : e.target[1].value;
        formData.createdFrom = formatDateToYYYYMMDD(e.target[2].value);
        formData.createdTo = formatDateToYYYYMMDD(e.target[3].value);
        // formData.createdFrom = e.target[2].value;
        // formData.createdTo = e.target[3].value;
        setFormData({...formData, adjustmentType: "UP", "noOfRows": 10, "limitFrom": 0});
        // console.log("form Data changed");
        console.log(formData);
        setSearch(true)
    }


    return (
        <form onSubmit={submitFormData}>
            <label htmlFor="adjustmentID">adjustmentId</label>
            <input type="number" id="adjustmentID" autoComplete="off"></input>
            <label htmlFor="status">status</label>
            <select id="status" defaultValue="click">
                <option value="">select status</option>
                <option name="open" value="OPEN">OPEN</option>
                <option name="accepted" value="ACCEPT">ACCEPTED</option>
                <option name="reject" value="REJECT">REJECT</option>
            </select>
            <label htmlFor="createdDateFrom">dateFrom</label>
            <input type="date" id="createdDateFrom" autoComplete="off"></input>
            <label htmlFor="createdDateTo">dateTo</label>
            <input type="date" id="createdDateTo" autoComplete="off"></input>

            <input type="submit" value="search"/>
        </form>
    )
}