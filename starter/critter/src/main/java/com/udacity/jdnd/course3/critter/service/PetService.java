package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PetService {

    @Autowired
    private CustomerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    public PetDTO savePet (Pet pet, Long ownerId){

        Customer petOwner = ownerRepository.getOne(ownerId);

        pet.setOwner(petOwner);

        petRepository.save(pet);

        petOwner.getPets().add(pet);
        ownerRepository.save(petOwner);

        return createPetDTO(pet);

    }

    public PetDTO createPetDTO (Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);

        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;

    }


}
