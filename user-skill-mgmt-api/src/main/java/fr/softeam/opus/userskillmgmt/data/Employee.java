package fr.softeam.opus.userskillmgmt.data;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true, publicConverter = false)
public class Employee {

    private String employeeId;

    private List<Skill> skills;

    public Employee() {
        this.skills = new ArrayList<>();
    }

    public Employee(String employeeId, List<Skill> skills) {
        this.employeeId = employeeId;
        this.skills = skills;
    }

    public Employee(JsonObject json){
        EmployeeConverter.fromJson(json, this);
    }

    public static void toJson(Employee employee, JsonObject json){
        EmployeeConverter.toJson(employee, json);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Fluent
    public Employee setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    @Fluent
    public Employee setSkills(List<Skill> skills) {
        this.skills = skills;
        return this;
    }

    @Fluent
    public Employee addSkill(Skill skill){
        this.skills.add(skill);
        return this;
    }
}
