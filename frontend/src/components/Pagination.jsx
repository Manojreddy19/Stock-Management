import React, { useState } from "react";
export const Pagination = (props) => {
    console.log(props);
    const noOfRecordsPerPage = 10;
    const noOfPages = Math.ceil(props.totalRecords / noOfRecordsPerPage);
    
    // console.log(noOfPages)
  const pagenumbers = [];
  for(let i=0;i<noOfPages;i++) {
    pagenumbers.push(i)
  }

  const setPageHandler = (page) => {
    // console.log(page)
    props.setPage(page)
  }
  // console.log(pagenumbers);
  return (
    <div>
      <center>
        <ul>
        {pagenumbers.map((page) => (
          <li style={{listStyleType: "none", color: "blue", cursor: "pointer", marginTop: "10px"}} key={page}><a onClick={()=>setPageHandler(page)}>{page+1}</a></li>
        ))}
        </ul>
      </center>
    </div>
  );
}