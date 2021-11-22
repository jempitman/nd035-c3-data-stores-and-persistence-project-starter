package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId){
        employeeRepository.getOne(employeeId).setDaysAvailable(daysAvailable);
//        employee.setDaysAvailable(daysAvailable);
//        employeeRepository.save(employee);
//        System.out.println("Employee availability saved");
    }

    public List<Employee> getEmployeesForService(DayOfWeek requestedDay, Set<EmployeeSkill> requestedSkill){

        List<Employee> availableEmployeesWithOneOfTheSkills = employeeRepository.findEmployeesByDaysAvailableAndSkillsIn(requestedDay, requestedSkill);
        List<Employee> availableEmployeesWithAllOfTheSkills = new ArrayList<>();

        //Remove duplicates caused by employee having more than one of the request skills
        Set<Employee> set = new HashSet<>(availableEmployeesWithOneOfTheSkills);
        availableEmployeesWithOneOfTheSkills.clear();
        availableEmployeesWithOneOfTheSkills.addAll(set);

        for (Employee e : availableEmployeesWithOneOfTheSkills){
            if(e.getSkills().containsAll(requestedSkill)){
                availableEmployeesWithAllOfTheSkills.add(e);
            }
        }
        return availableEmployeesWithAllOfTheSkills;
    }





}
