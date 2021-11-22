package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepositoryImpl customerRepository;


    public Customer saveCustomer(Customer customer){

        return customerRepository.saveCustomer(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    public Customer findCustomer(Long id){
        return customerRepository.findCustomerById(id);
    }

    public void addPetToCustomer(Pet pet, Customer customer){
        List<Pet> pets = customer.getPets();
        if (pets == null) {
            pets = new ArrayList<>();
        }
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.saveCustomer(customer);
    }


}
