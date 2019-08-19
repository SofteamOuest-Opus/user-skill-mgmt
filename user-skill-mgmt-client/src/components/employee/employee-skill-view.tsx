import React, { SFC } from "react";
import Employee from "../../utils/interfaces/employee";
import Skill from "../../utils/interfaces/skill";


const EmployeeSkillView: SFC<{employee : Employee}> = (props) => {
    return (
        <div className="container">
            <h2>Employee ID {props.employee.employeeId}</h2>
            <h3>Skills</h3>
            {renderSkills(props.employee.skills)}
        </div>
    );
}

const renderSkills = (skills : Skill[]) => {
    let skillsRender: JSX.Element[] = [];
    skills.forEach(skill => {
        skillsRender.push(
            <div className="card col-4">
                <div className="card-body">
                    <h5 className="card-title">{skill.label}</h5>
                    <h6 className="card-subtitle mb-2 text-muted">{skill.level}</h6>
                    <div className="card-text">
                        <p><small>Category :</small> {skill.category.label}</p>
                    </div>
                </div>
            </div>
        )
    });
    return skillsRender;
}

export default EmployeeSkillView;