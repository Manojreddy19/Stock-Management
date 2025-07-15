import "./App.css";
import { BrowserRouter as Router, Routes, Route, useLocation } from "react-router-dom";
import AdjustmentForm from "./pages/AdjustmentForm";
import StockAdjustmentSelect from "./pages/StockAdjustmentSelect";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
import "bootstrap/dist/css/bootstrap.min.css";
import MainLayout from './layouts/mainLayout'

function AppRoutes() {
  const location = useLocation();
  const isLoginPage = location.pathname === "/";

  return (
    <Routes>
      <Route path="/" element={<Login />} />
      {!isLoginPage && (
        <>
          <Route
            path="/dashboard"
            element={
              <MainLayout>
                <Dashboard />
              </MainLayout>
            }
          />
          <Route
            path="/stockAdjustmentSelect"
            element={
              <MainLayout>
                <StockAdjustmentSelect />
              </MainLayout>
            }
          />
          <Route
            path="/adjustment-form"
            element={
              <MainLayout>
                <AdjustmentForm />
              </MainLayout>
            }
          />
        </>
      )}
    </Routes>
  );
}

function App() {
  return (
    <Router>
      <AppRoutes />
    </Router>
  );
}

export default App;
