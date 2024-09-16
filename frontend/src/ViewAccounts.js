import React, { useState, useEffect } from 'react';

function ViewAccounts() {
    //ejemplo despues se debe carar la bd aqui

  const [accounts, setAccounts] = useState([
    { accountNumber: '123456', clientId: '12345678', clientName: 'John Doe', balance: 5000, creationDate: '2023-09-01' },
    { accountNumber: '789101', clientId: '87654321', clientName: 'Jane Doe', balance: 3000, creationDate: '2023-09-10' },
  ]);

  return (
    <div className="view-accounts-container">
      <h2>Lista de Cuentas</h2>
      <table className="accounts-table">
        <thead>
          <tr>
            <th>Número de Cuenta</th>
            <th>Doc Cliente</th>
            <th>Nombre Cliente</th>
            <th>Saldo</th>
            <th>Fecha de Creación</th>
          </tr>
        </thead>
        <tbody>
          {accounts.map((account) => (
            <tr key={account.accountNumber}>
              <td>{account.accountNumber}</td>
              <td>{account.clientId}</td>
              <td>{account.clientName}</td>
              <td>${account.balance}</td>
              <td>{account.creationDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ViewAccounts;
