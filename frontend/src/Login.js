import './login.css';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const handleSubmit = (event) => {
    event.preventDefault();

    const cliente = {
      nombre: username,
      clave: password,
    };

   
    axios.post('http://212.90.120.186:8080/login', cliente)
      .then(response => {
        if (response.status === 200) {
          navigate('/MainPage');
        }
      })
      .catch(error => {
        if (error.response && error.response.status === 401) {
          alert('Usuario o contraseña incorrectos');
        } else {
            console.log(error.response.data)
          alert('Hubo un error en el servidor22 '+error);
        }
      });
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
      <p>No tienes cuenta aún?  <Link to={'/register'}>Registrarme</Link></p>
    </div>
    </div>
    </div>
  );
}

export default Login;
