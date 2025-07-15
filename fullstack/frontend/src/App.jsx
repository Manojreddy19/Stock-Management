import "./App.css";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  useLocation,
} from "react-router-dom";
import StockUpAdjustmentForm from "./pages/StockUpAdjustmentForm";
import StockDownAdjustmentForm from "./pages/StockDownAdjustmentForm";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
import "bootstrap/dist/css/bootstrap.min.css";
import MainLayout from "./layouts/mainLayout";

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
            path="/adjustment-form-up"
            element={
              <MainLayout>
                <StockUpAdjustmentForm />
              </MainLayout>
            }
          />
          <Route
            path="/adjustment-form-down"
            element={
              <MainLayout>
                <StockDownAdjustmentForm />
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
