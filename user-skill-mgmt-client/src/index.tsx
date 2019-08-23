import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';

import './index.css';
import App from './components/app/App';
import * as serviceWorker from './serviceWorker';

import Keycloak from 'keycloak-js';

let keycloak = Keycloak("http://localhost:3000/keycloak.json");

keycloak.init({ onLoad: 'login-required' }).success((auth: any) => {

    if (!auth) {
        console.error(auth)
        window.location.reload();
    } else {
        console.info("Authenticated");
    }

    ReactDOM.render(
        <Router>
            <App />
        </Router>, document.getElementById('root')
    );

    if(keycloak.token){
        localStorage.setItem("react-token", keycloak.token);
    }

    if(keycloak.refreshToken){
        localStorage.setItem("react-refresh-token", keycloak.refreshToken);
    }

    setTimeout(() => {
        keycloak.updateToken(70).success((refreshed: boolean) => {
            if (refreshed) {
                console.debug('Token refreshed' + refreshed);
            } else if(keycloak.tokenParsed && keycloak.tokenParsed.exp &&  keycloak.timeSkew) {
                console.warn('Token not refreshed, valid for '
                    + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
            }
        }).error(() => {
            console.error('Failed to refresh token');
        });


    }, 60000)

}).error(() => {
    console.error("Authenticated Failed");
});

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
