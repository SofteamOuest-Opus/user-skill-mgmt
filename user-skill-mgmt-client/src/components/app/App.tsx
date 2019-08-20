import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import Routes from '../../router';
import { Link } from 'react-router-dom';

const App = () => {
    return (
      <div>
        <ul>
          <li><Link to="/employee/">Employee</Link></li>
        </ul>
        <Routes />
      </div>

    );
}

export default App;
