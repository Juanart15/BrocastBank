import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './CreateAccount.css';
import axios from 'axios'; 
import logoBB from './logoBB.png';

function CreateAccount() {
  const [clientClave, setClave] = useState('');
  const [clientName, setClientName] = useState('');
  const [clientCedula, setClientCedula] = useState('');
  const [clientSaldo, setSaldo] = useState('');
  const [clientCelular, setClientCelular] = useState('');
  const navigate = useNavigate();

  const handleCedulaChange = (e) => {
    const cedula = e.target.value;
    setClientCedula(cedula);

    if (cedula.length === 10) { // Asegúrate de que la cédula tenga 10 dígitos
      axios.get(`http://localhost:8080/cliente?cedula=${cedula}`)
        .then(response => {
          const cliente = response.data;
          setClientName(cliente.nombre); // Auto-completa el nombre
          setClientCelular(cliente.telefono); // Auto-completa el teléfono
        })
        .catch(error => {
          console.error('Error al buscar cliente:', error);
          alert('Cliente no encontrado');
          setClientName('');
          setClientCelular('');
        });
    }
  };

  const handleCreateAccount = (event) => {
    event.preventDefault();
    const nuevaCuenta = {
      clienteCedula: clientCedula,  // Cambia a "clienteCedula" para coincidir con el backend
      cuentaSaldo: clientSaldo,   
      cuentaClave: clientClave     
    };

    axios.post('http://localhost:8080/cuenta', nuevaCuenta)
      .then(response => {
        alert('Tu cuenta ha sido creada exitosamente!');
        navigate('/');  
      })
      .catch(error => {
        console.error('Hubo un error al crear tu cuenta', error);
        alert('Hubo un error al crear cuenta');
      });
  };

  

  return (
    <div className="register-container">
      <div className="register-box">
      <div className="stars">
        {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
         {[...Array(50)].map((_, i) => (
          <div key={i} className="star"></div>
        ))}
        
      </div>
      
        <h2>Crear Cuenta Bancaria</h2>
        <div className="input-container">
          <input
            type="number"
            className="register-input"
            placeholder="Cédula cliente"
            value={clientCedula}
            onChange={(e) => setClientCedula(e.target.value)}
          />
        </div>
        <div className="input-container">
          <input
            type="text"
            className="register-input"
            placeholder="Nombre del Cliente"
            value={clientName}
            onChange={(e) => setClientName(e.target.value)}
          />
        </div>
        <div className="input-container">
          <input
            type="text"
            className="register-input"
            placeholder="Clave de la cuenta"
            value={clientClave}
            onChange={(e) => setClave(e.target.value)}
          />
        </div>
        <div className="input-container">
          <input
            type="number"
            className="register-input"
            placeholder="Numero de celular"
            value={clientCelular}
            onChange={(e) => setClientCelular(e.target.value)}
          />
        </div>
        
        <div className="input-container">
          <input
            type="number"
            className="register-input"
            placeholder="Saldo Inicial"
            value={clientSaldo}
            onChange={(e) => setSaldo(e.target.value)}
          />
        </div>
        <button className="register-button" onClick={handleCreateAccount}>
          Crear Cuenta
        </button>
      </div>
    </div>
  );
}

export default CreateAccount;
