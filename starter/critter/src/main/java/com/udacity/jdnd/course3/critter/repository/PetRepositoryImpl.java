package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PetRepositoryImpl implements PetRepository{

    @Autowired
    EntityManager entityManager;

    private static final String FIND_ALL_PETS = "select p from Pet p";


    @Override
    public Pet savePet(Pet p) {
        if(p.getId() == null || p.getId() <= 0){
            entityManager.persist(p);
        } else{
            p = entityManager.merge(p);
        }

        return p;
    }

    @Override
    public Pet findPetById(Long petId) {
        return entityManager.find(Pet.class, petId);
    }


    @Override
    public List<Pet> findAllPets() {
        TypedQuery<Pet> query = entityManager.createQuery(FIND_ALL_PETS, Pet.class);
        return query.getResultList();
    }
}
