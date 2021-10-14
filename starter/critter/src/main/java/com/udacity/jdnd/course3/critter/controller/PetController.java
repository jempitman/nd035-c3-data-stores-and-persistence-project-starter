package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
//@Transactional
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        Pet newPet = this.petService.savePet(this.convertPetDTOtoPet(petDTO));

        petDTO.setId(newPet.getId());

        return petDTO;

    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet retrievedPet = this.petService.findPet(petId);
        return convertPetToDTO(retrievedPet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> petList = this.petService.getAllPets();
        return petList.stream()
                .map(PetController::convertPetToDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
         List<Pet> petList = petService.getPetsByOwnerId(ownerId);

//         petList.forEach();

        return petList.stream()
                .map(PetController::convertPetToDTO).collect(Collectors.toList());
    }

    private static PetDTO convertPetToDTO(Pet pet){
        Long ownerId = pet.getCustomer().getId();
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(ownerId);
        return petDTO;
    }


    private Pet convertPetDTOtoPet(PetDTO petDTO){
        Pet pet = new Pet();
        long ownerId = petDTO.getOwnerId();
        BeanUtils.copyProperties(petDTO, pet);
        pet.setCustomer(customerService.findCustomer(ownerId));
        return pet;
    }
}
