import React, { useState } from 'react';
import './Banco.css'; 

function Login({ onLogin }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    if (username === 'admin' && password === '1234') {
      onLogin(true);
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
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <div className="input-container">
            <input 
              type="text" 
              placeholder="Username" 
              value={username} 
              onChange={(e) => setUsername(e.target.value)} 
              className="login-input"
            />
          </div>
          <div className="input-container">
            <input 
              type="password" 
              placeholder="Password" 
              value={password} 
              onChange={(e) => setPassword(e.target.value)} 
              className="login-input"
            />
          </div>
          <div className="options">
            <label>
              <input type="checkbox" /> Remember me
            </label>
            <a href="#" className="forgot-password">Forgot password?</a>
          </div>
          <button type="submit" className="login-button">Login</button>
        </form>
        <p>Don't have an account? <a href="#">Register</a></p>
      </div>
    </div>
    </div>
    
  );
}

function Banco() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div>
    {isLoggedIn ? (
      <div>
        {currentView === 'accountCreation' && <AccountCreation />}
        {currentView === 'accountsList' && <AccountsList />}
        {currentView === 'deposit' && <Deposit />}
        {/* Botones o navegación para cambiar de vista */}
        <button onClick={() => setCurrentView('accountCreation')}>Create Account</button>
        <button onClick={() => setCurrentView('accountsList')}>View Accounts</button>
        <button onClick={() => setCurrentView('deposit')}>Make Deposit</button>
      </div>
    ) : (
      <Login onLogin={setIsLoggedIn} />
    )}
  </div>

  );
}

export default Banco;
