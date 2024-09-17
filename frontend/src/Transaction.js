import React, { useState } from 'react';
import './Transaction.css';
import logoBB from './logoBB.png';

function Transaction() {
  const [transactionType, setTransactionType] = useState('deposit'); 
  const [accountNumber, setAccountNumber] = useState('');
  const [amount, setAmount] = useState('');

  const handleTransaction = (event) => {
    event.preventDefault();
    // Lógica para realizar la transacción
    alert(`Realizando un ${transactionType} de $${amount} a la cuenta ${accountNumber}`);
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
          
          <form onSubmit={handleTransaction}>
            <div className="input-group">
              <label>Número de Cuenta</label>
              <input 
                type="text" 
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
            
            <button type="submit" className="transaction-button">
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
