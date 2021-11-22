package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;

import java.util.List;

/**
 * repository interface to store Customer entities
 */

public interface CustomerRepository {

    Customer saveCustomer (Customer customer);

    Customer findCustomerById (Long id);

    Customer findCustomerByPet(Pet pet);

    List<Customer> getAllCustomers();

}
