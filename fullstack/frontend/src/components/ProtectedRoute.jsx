import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ allowedRoles, children }) => {
  const user = JSON.parse(localStorage.getItem('user'));

  const roles = Array.isArray(user?.roles)
    ? user.roles
    : user?.roles
    ? [user.roles]
    : [];

  const isAuthorized = roles.some(role => allowedRoles.includes(role));

  if (!isAuthorized) {
    return <Navigate to="/" replace />;
  }

  return children;
};

export default ProtectedRoute;

