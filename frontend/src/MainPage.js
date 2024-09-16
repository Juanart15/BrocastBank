import React from 'react';
import { useNavigate } from 'react-router-dom'; // Cambiado useHistory por useNavigate

import './Mainpage.css'; // Asegúrate de tener estilos para organizar el layout

import logoBB from './logoBB.png';

function MainPage({ clientName }) {
  const navigate = useNavigate(); // Cambiado useHistory por useNavigate

  return (
    
    <div className="main-container">
       
      <div className="left-container">
      <div className="stars">
        {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
         {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
      </div>
        <h2>{clientName}</h2>
        <ul>
          <li onClick={() => navigate('/CreateAccount')}>Crear Cuenta ➡️</li>
          <li onClick={() => navigate('/ViewAccounts')}>Ver Cuenta ➡️</li>
          <li onClick={() => navigate('/transaction')}>Transacción ➡️</li>
          <li onClick={() => navigate('/products')}>Nuestros Productos ➡️</li>
          <li onClick={() => navigate('/')}>Cerrar Sesión</li>
        </ul>
      </div>
      <div className="right-container">
      <div className="stars">
        {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
         {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
      </div>
        <img src={logoBB} alt="Brocast Bank Logo" />
      </div>
    </div>
  );
}

export default MainPage;
