package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa repository to store Employee entities
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
