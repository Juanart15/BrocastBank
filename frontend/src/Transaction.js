import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Transaction.css';


function Transaction() {
  
  const [accountNumber, setAccountNumber] = useState('');
  const [amount, setAmount] = useState('');
  const [clave, setClave] = useState('');
  
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();

    const nuevoCliente = {
      numeroCuentaDeposito: accountNumber,
      saldoDeposito: amount,
      claveCuentaDeposito: clave,
    };

    axios.post('http://212.90.120.186:8080/deposito', nuevoCliente)
      .then(response => {
        console.log('Deposito exitoso', response.data);
        alert(`Deposito de $${amount} realizado exitosamente a la cuenta ${accountNumber}`);
        navigate('/MainPage');
      })
      .catch(error => {
        console.error('Error al realizar el depósito', error);
        alert('Hubo un error al realizar el depósito');
      });
  };

  return (
    <div className="transaction-container">
      <h2>Realizar Depósito</h2>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label>Número de Cuenta</label>
          <input 
            type="number" 
            value={accountNumber} 
            onChange={(e) => setAccountNumber(e.target.value)} 
            placeholder="Ingrese número de cuenta" 
          />
        </div>
        
        <div className="input-group">
          <label>Monto</label>
          <input 
            type="number" 
            value={amount} 
            onChange={(e) => setAmount(e.target.value)} 
            placeholder="Ingrese el monto" 
          />
        </div>
        
        <div className="input-group">
          <label>Clave de la cuenta</label>
          <input 
            type="password" 
            value={clave} 
            onChange={(e) => setClave(e.target.value)} 
            placeholder="Ingrese la clave" 
          />
        </div>
        
        <button type="submit">Realizar Depósito</button>
      </form>
    </div>
  );
}

export default Transaction;
