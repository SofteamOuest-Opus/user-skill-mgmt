import Skill from "./skill";

export default interface Employee {
    employeeId: string,
    skills: Skill[]
}