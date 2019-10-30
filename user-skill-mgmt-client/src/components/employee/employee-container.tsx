import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Constants from '../../utils/Constants';
import Employee from '../../utils/interfaces/employee';
import EmployeeSkillView from './employee-skill-view';

const EmployeePage = (props : any) => {
    const [isLoading, setIsLoadingState] = useState(true);
    const [employeeState, setEmployeeState] = useState({} as Employee);
    const [errorState, setErrorState] = useState(null);

    const user = props.user; //We have the user profile as param
 
    useEffect(() => {
        // We use the attribut idEmployee to fetch the information
        fetch(Constants.API + Constants.EMPLOYEE_API.replace("{employeeId}", user.attributes.idEmployee[0])) 
            .then(response => response.json())
            .then(responseJson => {
                // Once we have the employee information we update the state
                setIsLoadingState(false)
                setEmployeeState(responseJson);
            })
            .catch(error => {
                //If an error ocurred we update the state with such error
                console.error(error);
                setIsLoadingState(false)
                setErrorState(error);
            });
    }, []);
    
    // By default we display a loading page
    let info : JSX.Element = <p>Loading...</p>
    
    if(!isLoading && user){
        if(errorState){ // If an error exists it's displayed
            console.log("errorstate=" + errorState);
            info = <p>An error ocurred : {errorState}</p>
        } else if(Object.keys(employeeState).length > 0){ // If the employee info exists it's displayed
            info = (
                <div>
                    <h4>Hello {user.firstName} {user.lastName}</h4>
                    <EmployeeSkillView employee={employeeState} />
                </div>
            );
        }
    }
    return(
        <div className="container">
            {info}
        </div>
    );
}

export default EmployeePage;