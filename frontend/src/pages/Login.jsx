import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Login.css';
import { ip } from '../assets/utils';

const Login = () => {
  const [credentials, setCredentials] = useState({ username: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setCredentials((prev) => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`http://${ip}:8080/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credentials)
      });
      console.log(response)
      if (!response.ok) {
        throw new Error('Login failed');
      }

      const result = await response.json(); 

      localStorage.setItem('user', JSON.stringify(result)); 

      console.log(result.roles)
      if (result.roles.includes("ADMIN")) {
        navigate('/dashboard');
      } else if (result.roles.includes("USER")) {
        navigate('/stockAdjustmentSelect');
      } else {
        alert("Unauthorized role");
      }

    } catch (error) {
      alert("Login failed: " + error.message);
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Login</h2>
        <label htmlFor="username">Username</label>
        <input name="username" value={credentials.username} onChange={handleChange} required />

        <label htmlFor="password">Password</label>
        <input name="password" type="password" value={credentials.password} onChange={handleChange} required />

        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
