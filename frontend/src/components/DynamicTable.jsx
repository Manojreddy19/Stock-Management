import "../styles/adjustmentForm.css";

const DynamicTable = ({ headers, data, amount = 0 }) => {
  return (

    < div id="adjustment-table-wrapper">
       <h3>Added Adjustments</h3>
      <table id="adjustment-table">
        <thead>
          <tr>
            {headers &&
              headers?.map((header, index) => <th key={index}>{header}</th>)}
          </tr>
        </thead>
        <tbody>
          {data &&
            data?.map((adjustment, index) => {
              return (
                <tr key={index} style={{ borderBottom: "1px solid #ddd" }}>
                  {headers &&
                    headers?.map((header, index) => {
                      return <td key={index}>{adjustment[header]}</td>;
                    })}
                </tr>
              );
            })}
          {amount > 0 && (
            <tr>
              {" "}
              <td colSpan={headers?.length}>TotalAmount : {amount}</td>{" "}
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default DynamicTable;
