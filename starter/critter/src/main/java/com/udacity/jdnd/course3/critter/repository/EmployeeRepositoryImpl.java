package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Implementing class for EmployeeRepository interface
 */


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

    @Autowired
    EntityManager entityManager;

    private static final String FIND_EMPLOYEE_BY_AVAILABILITY_AND_SKILL = "select distinct e from Employee e left " +
            "join e.skills es left join e.daysAvailable ea where es in :skills and ea in :daysAvailable";

    @Override
    public Employee saveEmployee(Employee e) {
        if(e.getId() == null || e.getId() <= 0){
            entityManager.persist(e);
        } else{
            e = entityManager.merge(e);
        }

        return e;
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findEmployeesByDaysAvailableAndSkillsIn(DayOfWeek requestedDay, Set<EmployeeSkill> skills) {

        TypedQuery<Employee> query = entityManager.createQuery(FIND_EMPLOYEE_BY_AVAILABILITY_AND_SKILL, Employee.class)
                .setParameter("skills", skills)
                .setParameter("daysAvailable", requestedDay);

        return query.getResultList();
    }
}
