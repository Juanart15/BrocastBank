import './Banco.css';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    if (username === 'admin' && password === '1234') {
      navigate('/MainPage');
    } else {
      alert('Usuario o contraseña incorrectos');
    }
  };

  return (
    <div className="bropage">
    <div>
<header className="header">
 <h1>Brocast Bank</h1>
</header>
</div>
<div className="login-container">
<div className="ripple-background">
        <div className="circle xxlarge shade1"></div>
        <div className="circle xlarge shade2"></div>
        <div className="circle large shade3"></div>
        <div className="circle medium shade4"></div>
        <div className="circle small shade5"></div>
      </div>
    <div className="login-box">
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleSubmit}>
      <div className="input-container">
        <input 
          type="text" 
          placeholder="Username" 
          value={username} 
          onChange={(e) => setUsername(e.target.value)} 
          
        />
        </div>
        <div className="input-container">
        <input 
          type="password" 
          placeholder="Password" 
          value={password} 
          onChange={(e) => setPassword(e.target.value)} 
        />
         </div>
        <button type="submit" className="login-button">Continuar</button>
      </form>
      <p>No tienes cuenta aún?  <a href="/register">Registrarme</a></p>
    </div>
    </div>
    </div>
  );
}

export default Login;
