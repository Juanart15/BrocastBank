import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './ViewAccounts.css';

function ViewAccounts() {
  const [cedula, setCedula] = useState('');
  const [accounts, setAccounts] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleCedulaChange = (event) => {
    setCedula(event.target.value);
  };

  const handleViewAccounts = () => {
    setLoading(true);
    axios.get(`http://212.90.120.186:8080/cuentas?clienteCedula=${cedula}`)
      .then((response) => {
        setAccounts(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error al obtener las cuentas del cliente:', error);
        setLoading(false);
      });
  };

  return (
    <div className="view-accounts-container">
      <h2>Buscar Cuentas de Cliente</h2>
      <div className="input-container">
        <input
          type="text"
          placeholder="Ingrese la cédula del cliente"
          value={cedula}
          onChange={handleCedulaChange}
        />
        <button onClick={handleViewAccounts}>Ver datos del cliente</button>
      </div>

      {loading ? (
        <p>Cargando cuentas...</p>
      ) : (
        accounts.length > 0 ? (
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
        ) : (
          <p>No se encontraron cuentas para esta cédula.</p>
        )
      )}
    </div>
  );
}

export default ViewAccounts;
