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
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

    @Autowired
    EntityManager entityManager;

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
    public List<Employee> findByDaysAvailable(DayOfWeek requestedDay) {
//        TypedQuery <Employee> query = entityManager.createQuery("select e from Employee e where");
        return null;
    }

    @Override
    public List<Employee> findEmployeesByDaysAvailableAndSkillsIn(DayOfWeek requestedDay, Set<EmployeeSkill> skills) {

        String FIND_BY_SERVICE = "select e from Employee e, e.skills es, e.availability ea " +
                "where e.id = es.id and e.id = ea.id and es.skills in (" + skills
                .stream()
                .map((skill) -> String.valueOf(skill.ordinal()))
                .collect(Collectors.joining(",")) + ") AND ea.days = " + requestedDay.ordinal() + " ";


//        TypedQuery <Employee> query = entityManager.createQuery("select distinct e from Employee e inner join e.skills es inner join e.availability ea" +
//                " where es in (" + skills
//                .stream()
//                .map((skill) -> String.valueOf(skill.ordinal()))
//                .collect(Collectors.joining(",")) + ") AND ea in " + requestedDay.ordinal() + " ", Employee.class);

        TypedQuery <Employee> query = entityManager.createQuery(FIND_BY_SERVICE, Employee.class);

        return query.getResultList();
    }
}
