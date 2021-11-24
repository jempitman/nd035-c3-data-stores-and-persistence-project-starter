package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;

import java.util.List;

/**
 * repository interface to persist Pet entities
 */


public interface PetRepository {

    Pet savePet(Pet pet);

    Pet findPetById(Long id);



    List<Pet> findAllPets();

}