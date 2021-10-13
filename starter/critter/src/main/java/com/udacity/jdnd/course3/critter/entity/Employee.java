package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.Set;

/**
 * Child class of User to map Employee data to EmployeeRepository
 */

@Entity
public class Employee extends User {

    @ElementCollection
    //@JoinTable(name="employee_skills")
    private Set<EmployeeSkill> skills;

    @ElementCollection
    //@JoinTable(name="employee_availability")
    private Set<DayOfWeek> daysAvailable;

    public Employee(){
    }

    public Employee(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
