import { useNavigate } from 'react-router-dom';

const StockAdjustmentSelect = () => {
  const navigate = useNavigate();

  const handleSelection = (type) => {
    navigate(`/adjustment-form?type=${type}`);
  };

  return (
    <div style={{textAlign: 'center', margin: 'auto'}}>
      <h2>Select Stock Adjustment Type</h2>
      <button onClick={() => handleSelection('UP')} style={{ marginRight: '20px' }}>
        Stock Up
      </button>
      <button onClick={() => handleSelection('DOWN')}>
        Stock Down
      </button>
    </div>
  );
};

export default StockAdjustmentSelect;