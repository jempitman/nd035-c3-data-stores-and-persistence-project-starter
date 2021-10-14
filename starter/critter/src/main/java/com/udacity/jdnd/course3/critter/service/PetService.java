package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
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
    PetRepository petRepository;

    public Pet savePet (Pet pet){

        Pet returnedPet = petRepository.save(pet);
        if (pet.getCustomer() != null){
            customerService.addPetToCustomer(pet, pet.getCustomer());
        }

        return returnedPet;

    }

    public Pet findPet(Long petId){
        return petRepository.findById(petId).orElse(null);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByCustomerId(ownerId);
    }



}
