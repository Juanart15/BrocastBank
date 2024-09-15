import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Register.css'; 

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [verifypassword, setVerifypassword] = useState('');
  const [celular, setCelular] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
// creacion de cuenta

    


    alert('Cuenta creada exitosamente!');
    navigate('/');
  };

  return (
    <div className="register-container">
      <h2>Registrarme</h2>
      <form onSubmit={handleSubmit}>
        <input 
          type="text" 
          placeholder="Usuario" 
          value={username} 
          onChange={(e) => setUsername(e.target.value)} 
        />
        <input 
          type="password" 
          placeholder="contraseña" 
          value={password} 
          onChange={(e) => setPassword(e.target.value)} 
        />
         <input 
          type="verificar Contraseña" 
          placeholder="contraseña" 
          value={verifypassword} 
          onChange={(e) => setVerifypassword(e.target.value)} 
        />
        <input 
          type="Numero celular" 
          placeholder="Celular" 
          value={celular} 
          onChange={(e) => setCelular(e.target.value)} 
        />
        <button type="submit">Registro</button>
      </form>
    </div>
  );
}

export default Register;
