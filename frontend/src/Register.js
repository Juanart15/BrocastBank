import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Register.css';
import logoBB from './logoBB.png';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [cedula, setCedula] = useState('');
  const [celular, setCelular] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    // creacion de cuenta
    alert('Cuenta creada exitosamente!');
    navigate('/');
  };

  return (
    <div className="bropage">
      {/* Contenedor del logo en la esquina superior derecha */}
      <div className="logo-container">
        <img src={logoBB} alt="Brocast Bank Logo" />
      </div>

      <div className="left-container">
        <div className="register-box">
          <div className="stars">
            {[...Array(50)].map((_, i) => (
              <div key={i} className="star"></div>
            ))}
            {[...Array(50)].map((_, i) => (
              <div key={i} className="star"></div>
            ))}
          </div>
          <h2>Registrarme</h2>
          <form onSubmit={handleSubmit}>
            <div className="input-container">
              <input
                type="text"
                placeholder="Usuario"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div className="input-container">
              <input
                type="number"
                placeholder="Cedula"
                value={cedula}
                onChange={(e) => setCedula(e.target.value)}
              />
            </div>
            <div className="input-container">
              <input
                type="number"
                placeholder="Celular"
                value={celular}
                onChange={(e) => setCelular(e.target.value)}
              />
            </div>
            <div className="input-container">
              <input
                type="password"
                placeholder="Contraseña"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <button type="submit" className="login-button">Registrate</button>
          </form>
        </div>
      </div>
      
      <div className="right-container">
      </div>
    </div>
  );
}

export default Register;
