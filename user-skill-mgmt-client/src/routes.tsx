import React from 'react';
import { Route } from 'react-router-dom';
import EmployeePage from './components/employee/employee-container';
import UserContext from '.';

const Routes = () => {
    return (
        //This consumer will send the user profil to the component
        <UserContext.Consumer>
            { user => {
                return (<div>
                        <Route path="/employee" render={() => <EmployeePage user={user} />} />
                    </div>)
                }
            }
        </UserContext.Consumer>
    );
}

export default Routes;