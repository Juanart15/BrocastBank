import React, { useState } from 'react';
import './CreateAccount.css';
import logoBB from './logoBB.png';

function CreateAccount() {
  const [clientClave, setClave] = useState('');
  const [clientName, setClientName] = useState('');
  const [clientCedula, setClientCedula] = useState('');
  const [clientSaldo, setSaldo] = useState('');
  const [clientCelular, setClientCelular] = useState('');

  const handleCreateAccount = () => {
    // Aquí podrías agregar la lógica para crear la cuenta en una base de datos o API.
    alert('Cuenta creada exitosamente para ' + clientName);
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
            type="number"
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
