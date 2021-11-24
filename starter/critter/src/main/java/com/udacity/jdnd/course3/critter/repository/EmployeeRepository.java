package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Repository interface to persist Employee entities
 */

public interface EmployeeRepository {

    Employee saveEmployee (Employee employee);

    Employee findEmployeeById(Long id);

    List<Employee> findEmployeesByDaysAvailableAndSkills(DayOfWeek requestedDat, Set<EmployeeSkill> skills);
}
