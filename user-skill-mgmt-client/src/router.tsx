import React from 'react';
import { Route } from 'react-router-dom';
import EmployeePage from './components/employee/employee-container';

const Routes = () => {
    return (
        <div>
            <Route path="/employee" component={EmployeePage}/>
        </div>
    );
}

export default Routes;