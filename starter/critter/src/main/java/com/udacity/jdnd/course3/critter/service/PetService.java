package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
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
    private CustomerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    public Pet savePet (Pet pet, Long ownerId){

        Customer petOwner = ownerRepository.getOne(ownerId);

        pet.setOwner(petOwner);

        petRepository.save(pet);

        petOwner.getPets().add(pet);
        ownerRepository.save(petOwner);

        return pet;

    }

    public Pet getPetById(Long petId){
        return petRepository.getOne(petId);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        Customer owner = ownerRepository.getOne(ownerId);
        return owner.getPets();
    }



}
