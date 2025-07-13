import './App.css'
import AdjustmentForm from './pages/AdjustmentForm';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import StockAdjustmentSelect from './pages/StockAdjustmentSelect';
import Dashboard from './pages/Dashboard';
import Login from './pages/Login';
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        {/* <Route
          path="/dashboard"
          element={
            <ProtectedRoute allowedRoles={'ROLE_ADMIN'}>
              <Dashboard />
            </ProtectedRoute>
          }
        />
        <Route
          path="/stockAdjustmentSelect"
          element={
            <ProtectedRoute allowedRoles={'ROLE_USER'}>
              <StockAdjustmentSelect />
            </ProtectedRoute>
          }
        />
        <Route
          path="/adjustmentForm"
          element={
            <ProtectedRoute allowedRoles={'ROLE_USER'}>
              <AdjustmentForm />
            </ProtectedRoute>
          }
        />
      </Routes> */}
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/stockAdjustmentSelect" element={<StockAdjustmentSelect />} />
        <Route path="/adjustment-form" element={<AdjustmentForm />} />
        </Routes>
    </Router>
  );
}

export default App;
