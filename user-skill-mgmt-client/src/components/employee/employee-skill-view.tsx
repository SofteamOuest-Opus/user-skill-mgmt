import React, { SFC } from "react";
import Employee from "../../utils/interfaces/employee";
import Skill from "../../utils/interfaces/skill";


const EmployeeSkillView: SFC<{employee : Employee}> = (props) => {
    // We create card displaying the informations of each skill.
    const renderSkills = (skills : Skill[]) => {
        return skills.map((skill) => {
            return (
                <div className="card col-4" key={skill.id}>
                    <div className="card-body">
                        <h5 className="card-title">{skill.label}</h5>
                        <h6 className="card-subtitle mb-2 text-muted">{skill.level}</h6>
                        <div className="card-text">
                            <p><small>Category : </small>{skill.category.label}</p>
                        </div>
                    </div>
                </div>
            )
        });
    }
    
    return (
        <div>
            <h5>Skills</h5>
            {renderSkills(props.employee.skills)}
        </div>
    );
}



export default EmployeeSkillView;