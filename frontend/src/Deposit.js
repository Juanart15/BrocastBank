import React, { useState } from 'react';

function Deposit() {
  const [amount, setAmount] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    // Aquí añadirías la lógica para procesar el depósito.
    alert(`Depósito de ${amount} realizado exitosamente!`);
    setAmount('');
  };

  return (
    <div className="deposit-container">
      <h2>Hacer un deposito</h2>
      <form onSubmit={handleSubmit}>
        <input 
          type="number" 
          placeholder="Cantidad" 
          value={amount} 
          onChange={(e) => setAmount(e.target.value)} 
        />
        <button type="submit">Hacer Deposito</button>
      </form>
    </div>
  );
}

export default Deposit;
