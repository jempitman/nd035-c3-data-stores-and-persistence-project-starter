package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Jpa repository to store Employee entities
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public List<Employee> findByDaysAvailable(DayOfWeek requestedDay);
}
