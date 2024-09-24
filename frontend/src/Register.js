import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'; 
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
    const nuevoCliente = {
      nombre: username,
      cedula: cedula,
      telefono: celular,
      clave: password,
    };
    axios.post('http://212.90.120.186:8080/cliente', nuevoCliente)
      .then(response => {
        console.log('Cliente guardado:', response.data);
        alert('Te registraste exitosamente!');
        navigate('/');  
      })
      .catch(error => {
        console.error('Hubo un error al crear tu registro', error);
        alert('Hubo un error al crear el registro');
      });
  };

  return (
    <div className="bropage">
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
            <button type="submit" className="login-button" onClick={handleSubmit}>Registrate</button>
          </form>
        </div>
      </div>

      <div className="right-container"></div>
    </div>
  );
}

export default Register;
