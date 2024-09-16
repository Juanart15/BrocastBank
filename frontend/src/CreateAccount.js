import React, { useState } from 'react';

import './Register.css';

function CreateAccount() {
  const [clientID, setClientID] = useState('');
  const [clientName, setClientName] = useState('');
  const [clientCedula, setClientCedula]=useState('');

  const handleCreateAccount = () => {


    alert('Cuenta creada para ' + clientName);
  };

  return (
    <div>
      <h2>Crear Cuenta de Ahorros</h2>
      <input
        type="num"
        placeholder="ID del Cliente"
        value={clientID}
        onChange={(e) => setClientID(e.target.value)}
      />
      <input
        type="text"
        placeholder="Nombre del Cliente"
        value={clientName}
        onChange={(e) => setClientName(e.target.value)}
      />
       <input
        type="int"
        placeholder="Cedula"
        value={clientName}
        onChange={(e) => setClientCedula(e.target.value)}
      />
      <button onClick={handleCreateAccount}>Crear Cuenta</button>
    </div>
  );
}

export default CreateAccount;
