import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Constants from '../../utils/Constants';
import Employee from '../../utils/interfaces/employee';
import EmployeeSkillView from './employee-skill-view';

interface EmployeeSkillsState {
    isLoading: boolean,
    employee?: Employee,
    error: any
}

class EmployeeSkills extends Component<{}, EmployeeSkillsState> {
    constructor(props:  {}){
        super(props);
        this.state = {
            isLoading: true,
            error: null
        }
    }

    componentDidMount() : void {
        fetch(Constants.API + Constants.EMPLOYEE_API.replace("{employeeId}", "1"))
            .then(response => response.json())
            .then(responseJson => {
                this.setState({isLoading: false, employee: responseJson, error: null});
            })
            .catch(error => {
                console.error(error);
                this.setState({isLoading: false, error: error});
            });
    }


    render(){
        let info = <p>Error fetching the employee info</p>
        if(this.state.isLoading){
            info = <p>Loading...</p>
        } else {
            if(this.state.error){
                info = <p>An error ocurred : {this.state.error}</p>
            } else if(this.state.employee){
                let employee : Employee = this.state.employee;
                info = <EmployeeSkillView employee={this.state.employee} />;
            }
        }
        return (
            <div>{info}</div>
        );
    }
}

export default EmployeeSkills;