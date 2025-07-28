import React from 'react';

const DynamicTable = ({ headers, data, amount = 0 }) => {
  return (
    <div className="mt-3">
      <h4 className="mb-3">Added Adjustments</h4>
      <div className="table-responsive">
        <table className="table table-bordered table-striped">
          <thead>
            <tr>
              {headers &&
                headers.map((header, index) => (
                  <th key={index}>
                    {header.charAt(0).toUpperCase() + header.slice(1)}
                  </th>
                ))}
            </tr>
          </thead>
          <tbody>
            {data && data.length > 0 ? (
              data.map((adjustment, index) => (
                <tr key={index}>
                  {headers.map((header, hIndex) => (
                    <td key={hIndex}>{adjustment[header]}</td>
                  ))}
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={headers.length} className="text-center">
                  No records added yet
                </td>
              </tr>
            )}
            {amount > 0 && (
              <tr>
                <td colSpan={headers.length} className="text-end fw-bold">
                  Total Amount: ₹ {amount.toFixed(2)}
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default DynamicTable;
