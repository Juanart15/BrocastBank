import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Transaction.css';
import logoBB from './logoBB.png';
import axios from 'axios'; 

function Transaction() {
  const [transactionType, setTransactionType] = useState('deposit'); 
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
    axios.post('http://localhost:8080/deposito', nuevoCliente)
      .then(response => {
        console.log('Deposito exitoso', response.data);
        alert(`Realizando un ${transactionType} de $${amount} a la cuenta ${accountNumber}`);
        navigate('/');  
      })
      .catch(error => {
        console.error('Hubo un error al crear tu registro', error);
        alert('Hubo un error al crear el registro');
      });
  };
  
  return (
    <div className="bropage">
      <div className="left-container">
        <div className="transaction-box">
          <div className="stars">
            {[...Array(50)].map((_, i) => (
              <div key={i} className="star"></div>
            ))}
            {[...Array(50)].map((_, i) => (
              <div key={i} className="star"></div>
            ))}
          </div>
          <h2>Transacción</h2>
          <div className="transaction-options">
            <button 
              className={`transaction-type-button ${transactionType === 'deposit' ? 'active' : ''}`} 
              onClick={() => setTransactionType('deposit')}
            >
              Depósito
            </button>
            <button 
              className={`transaction-type-button ${transactionType === 'transfer' ? 'active' : ''}`} 
              onClick={() => setTransactionType('transfer')}
            >
              Transferencia
            </button>
          </div>
          
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
                type="text" 
                value={clave} 
                onChange={(e) => setClave(e.target.value)} 
                placeholder="Ingresa la Clave" 
              />
            </div>
            
            <button type="submit" className="transaction-button" onClick={handleSubmit}>
              {transactionType === 'deposit' ? 'Realizar Depósito' : 'Realizar Transferencia'}
            </button>
          </form>
        </div>
      </div>
      <div className="right-container">
      </div>
      
      <img src={logoBB} className="imagen-bro" alt="Brocast Bank Logo" />
    </div>
  );
}

export default Transaction;
