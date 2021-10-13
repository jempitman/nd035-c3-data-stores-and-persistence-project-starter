package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Repository to store Customer entities
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
