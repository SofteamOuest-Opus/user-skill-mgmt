import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Constants from '../../utils/Constants';
import Employee from '../../utils/interfaces/employee';
import EmployeeSkillView from './employee-skill-view';

interface EmployeeContainerState {
    isLoading: boolean,
    employee?: Employee, //The state can or cannot (?) contain the employee info
    error?: any          //The state can or cannot (?) contain an error
}

const EmployeeSkills = () => {
    const initialState : EmployeeContainerState = {isLoading: true}
    const [employeeState, setEmployeeState] = useState(initialState);

    useEffect(() => {
        fetch(Constants.API + Constants.EMPLOYEE_API.replace("{employeeId}", "1"))
            .then(response => response.json())
            .then(responseJson => {
                setEmployeeState({isLoading: false, employee: responseJson, error: null});
            })
            .catch(error => {
                console.error(error);
                setEmployeeState({isLoading: false, error: error});
            });
    }, []);

    let info : JSX.Element = <p>Loading...</p>
    if(!employeeState.isLoading){
        if(employeeState.error){
            info = <p>An error ocurred : {employeeState.error}</p>
        } else if(employeeState.employee){
            info = (
                <div>
                    <h4>Hello employee #{employeeState.employee.employeeId}</h4>
                    <EmployeeSkillView employee={employeeState.employee} />
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

export default EmployeeSkills;