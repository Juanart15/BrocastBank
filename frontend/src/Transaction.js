import React, { useState } from 'react';

function Transaction() {
  const [transactionType, setTransactionType] = useState('deposit'); 
  const [accountNumber, setAccountNumber] = useState('');
  const [amount, setAmount] = useState('');

  const handleTransaction = (event) => {
    event.preventDefault();
    // Aquí agregarías la lógica para realizar la transacción
    alert(`Realizando un ${transactionType} de $${amount} a la cuenta ${accountNumber}`);
  };

  return (
    <div className="transaction-container">
      <h2>Transacción</h2>
      <div className="transaction-options">
        <button onClick={() => setTransactionType('deposit')}>Depósito</button>
        <button onClick={() => setTransactionType('transfer')}>Transferencia</button>
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
  );
}

export default Transaction;
