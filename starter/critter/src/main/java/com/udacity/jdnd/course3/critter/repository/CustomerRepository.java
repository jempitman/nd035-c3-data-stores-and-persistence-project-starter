package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa Repository to store Customer entities
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
