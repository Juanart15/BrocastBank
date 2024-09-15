import React from 'react';

function Accountslist() {
  const accounts = ['Cuenta 1', 'Cuenta 2', 'Cuenta 3']; // Ejemplo

  return (
    <div className="accounts-container">
      <h2>Listado de Cuentas</h2>
      <ul>
        {accounts.map((account, index) => (
          <li key={index}>{account}</li>
        ))}
      </ul>
    </div>
  );
}

export default Accountslist;
