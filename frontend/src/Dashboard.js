import React from 'react';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const navigate = useNavigate();

  return (
    <div className="dashboard-container">
      <h2>Bienvenido </h2>
      <div className="login-container">
      <button onClick={() => navigate('/deposit')}>Hacer un Deposito</button>
      <button onClick={() => navigate('/accounts')}>ver cuentas</button>
    </div>
    </div>
  );
}

export default Dashboard;
