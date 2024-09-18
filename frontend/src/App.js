import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Login';
import Register from './Register';
import Deposit from './Deposit';
import Accountslist from './Accountslist';
import MainPage from './MainPage';
import CreateAccount from './CreateAccount';
import ViewAccounts from './ViewAccounts';
import Transaction from './Transaction';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/MainPage" element={<MainPage />} />
        <Route path="/deposit" element={<Deposit />} />
        <Route path="/CreateAccount" element={<CreateAccount />} />
        <Route path="/ViewAccounts" element={<ViewAccounts />} />
        <Route path="/transaction" element={<Transaction />} />
         <Route path="/accounts" element={<Accountslist />} />
      </Routes>
    </Router>
  );
}

export default App;
