package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    CustomerRepository ownerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    PetRepositoryImpl petRepository;

    public Pet savePet (Pet pet){

        Pet returnedPet = petRepository.savePet(pet);
        if (pet.getCustomer() != null){
            customerService.addPetToCustomer(pet, pet.getCustomer());
        }

        return returnedPet;

    }

    public Pet findPet(Long petId){
        return petRepository.findPetById(petId);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAllPets();
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        Customer owner = customerService.findCustomer(ownerId);

        return owner.getPets();
    }



}
