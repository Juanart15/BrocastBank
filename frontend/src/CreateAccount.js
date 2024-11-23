import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './CreateAccount.css';
import axios from 'axios';
import logoBB from './logoBB.png';

function CreateAccount() {
  const [clientClave, setClave] = useState('');
  const [clientName, setClientName] = useState('');
  const [clientCedula, setClientCedula] = useState('');
  const [clientSaldo, setSaldo] = useState('');
  const [clientCelular, setClientCelular] = useState('');
  const navigate = useNavigate();

  const handleCedulaChange = (e) => {
    const cedula = e.target.value;
    setClientCedula(cedula);

    if (cedula.length === 10) { // Asegúrate de que la cédula tenga 10 dígitos
      axios.get(`http://212.90.120.186:8080/clientes?cedula=${cedula}`)
          .then(response => {
            const cliente = response.data;
            setClientName(cliente.nombre); // Auto-completa el nombre
            setClientCelular(cliente.telefono); // Auto-completa el teléfono
          })
          .catch(error => {
            console.error('Error al buscar cliente:', error);
            alert('Cliente no encontrado');
            setClientName('');
            setClientCelular('');
          });
    }
  };

  const handleCreateAccount = (event) => {
    event.preventDefault();
    const nuevaCuenta = {
      clienteCedula: clientCedula,
      cuentaSaldo: clientSaldo,
      cuentaClave: clientClave
    };

    axios.post('http://212.90.120.186:8080/cuenta', nuevaCuenta)
        .then(response => {
          alert('Tu cuenta ha sido creada exitosamente!');
          navigate('/');
        })
        .catch(error => {
          console.error('Esta cuenta ya existe', error);
          alert('Esta cuenta ya existe');
        });
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

          <div className="input-containere">
            <input
                type="number"
                className="register-inputt"
                placeholder="Cédula cliente"
                value={clientCedula}
                onChange={(e) => setClientCedula(e.target.value)}
            />
          </div>

          <div className="input-containere">
            <input
                type="text"
                className="register-inputt"
                placeholder="Nombre del cliente"
                value={clientName}
                disabled // Campo de solo lectura
            />
          </div>

          <div className="input-containere">
            <input
                type="text"
                className="register-inputt"
                placeholder="Teléfono del cliente"
                value={clientCelular}
                disabled // Campo de solo lectura
            />
          </div>

          <div className="input-containere">
            <input
                type="text"
                className="register-inputt"
                placeholder="Clave de la cuenta"
                value={clientClave}
                onChange={(e) => setClave(e.target.value)}
            />
          </div>

          <div className="input-containere">
            <input
                type="number"
                className="register-inputt"
                placeholder="Saldo Inicial"
                value={clientSaldo}
                onChange={(e) => setSaldo(e.target.value)}
            />
          </div>

          <button className="register-buttono" onClick={handleCreateAccount}>
            Crear Cuenta
          </button>
        </div>
      </div>
  );
}

export default CreateAccount;
