import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import Routes from '../../routes';
import Navbar from '../../utils/navbar/navbar-view';

const App = () => {
    return (
      <div>
        <Navbar />
        <Routes />
      </div>

    );
}

export default App;
