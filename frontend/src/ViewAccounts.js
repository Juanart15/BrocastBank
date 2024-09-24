import React, { useState } from 'react';
import axios from 'axios';
import './ViewAccounts.css';

function ViewAccount() {
  const [cedula, setCedula] = useState('');
  const [clave, setClave] = useState('');
  const [account, setAccount] = useState(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleCedulaChange = (event) => setCedula(event.target.value);
  const handleClaveChange = (event) => setClave(event.target.value);

  const handleViewAccount = () => {
    setLoading(true);
    setError('');
    setAccount(null);

    axios.get(`http://212.90.120.186:8080/cuent?clienteCedula=${cedula}&cuentaClave=${clave}`)
      .then((response) => {
        setAccount(response.data);
        setLoading(false);
      })
      .catch((error) => {
        setError('Cédula o clave incorrecta');
        setLoading(false);
      });
  };

  return (
    <div className="view-account-container">
      <h2>Consultar Cuenta</h2>
      <div className="input-container">
        <input
          type="text"
          placeholder="Ingrese la cédula"
          value={cedula}
          onChange={handleCedulaChange}
        />
        <input
          type="password"
          placeholder="Ingrese la clave"
          value={clave}
          onChange={handleClaveChange}
        />
        <button className="Consultarcuenta" onClick={handleViewAccount}>Consultar Cuenta</button>
      </div>

      {loading ? (
        <p>Cargando datos...</p>
      ) : account ? (
        <div className="account-info">
          <h3>Detalles de la Cuenta</h3>
          <p><strong>Número de Cuenta:</strong> {account.cuentaNumero}</p>
          <p><strong>Nombre:</strong> {account.clienteNombre}</p>
          <p><strong>Teléfono:</strong> {account.clienteTelefono}</p>
          <p><strong>Saldo:</strong> ${account.cuentaSaldo}</p>
          <p><strong>Fecha de Creación:</strong> {account.cuentaFechaCreacion}</p>
        </div>
      ) : error && (
        <p className="error">{error}</p>
      )}
    </div>
  );
}

export default ViewAccount;
