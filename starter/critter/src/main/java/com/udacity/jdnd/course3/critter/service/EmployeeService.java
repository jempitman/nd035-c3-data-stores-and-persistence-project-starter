package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepositoryImpl;
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
    EmployeeRepositoryImpl employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.saveEmployee(employee);
    }

    public Employee findEmployeeById(Long employeeId){
        return employeeRepository.findEmployeeById(employeeId);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId){
        employeeRepository.findEmployeeById(employeeId).setDaysAvailable(daysAvailable);

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
