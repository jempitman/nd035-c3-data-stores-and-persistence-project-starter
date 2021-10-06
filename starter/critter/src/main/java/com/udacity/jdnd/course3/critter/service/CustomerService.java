package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;


    public CustomerDTO save(Customer customer, List<Long> petIds){

        List<Pet> pets = new ArrayList<>();

        if (petIds != null){
            pets = petIds.stream().map(petId -> petRepository.getOne(petId)).collect(toList());
        }

        customer.setPets(pets);

        customerRepository.save(customer);

        return createCustomerDTO(customer);
    }

    public CustomerDTO createCustomerDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        List<Long> petIds = customer.getPets().stream().map(Pet::getId).collect(toList());
        customerDTO.setPetIds(petIds);

        return customerDTO;
    }

}
