package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{

    @Autowired
    EntityManager entityManager;

    private static final String FIND_ALL_CUSTOMERS = "select c from Customer c";
    private static final String FIND_CUSTOMER_BY_PET = "select c from Customer c where :pet member of c.pets";

    @Override
    public Customer saveCustomer(Customer c) {

        if(c.getId() == null || c.getId() <= 0){
            entityManager.persist(c);
        } else{
            c = entityManager.merge(c);
        }

        return c;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer findCustomerByPet(Pet pet) {
        return entityManager
                .createQuery(FIND_CUSTOMER_BY_PET, Customer.class)
                .setParameter("pet", pet)
                .getSingleResult();
    }

    @Override
    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> query = entityManager.createQuery(FIND_ALL_CUSTOMERS, Customer.class);
        return query.getResultList();
    }


}
