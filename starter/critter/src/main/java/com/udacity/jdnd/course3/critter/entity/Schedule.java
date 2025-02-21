package com.udacity.jdnd.course3.critter.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Class to define schedule entities
 */

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Multiple employees can have multiple schedules
    @ManyToMany(targetEntity = Employee.class, cascade = CascadeType.MERGE)
    @Column(name ="schedule_employees")
    private List<Employee> employees;

    //Multiple pets can have multiple schedules
    @ManyToMany(targetEntity = Pet.class, cascade = CascadeType.MERGE)
    @Column(name = "schedule_pets")
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection
    @JoinTable(name="schedule_activities")
    private Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
