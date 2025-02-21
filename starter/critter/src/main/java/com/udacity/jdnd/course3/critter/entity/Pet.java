package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class to define Pet Entities
 */

@Entity
@Table(name="pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PetType type;

    @Nationalized
    private String name;

    private LocalDate birthDate;

    @Column(nullable = true)
    private String notes;

//    @JsonBackReference
    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;

    public Pet() {
    }

//    public Pet(PetType type, String name, LocalDate birthDate, String notes) {
//        this.type = type;
//        this.name = name;
//        this.birthDate = birthDate;
//        this.notes = notes;
//    }

    public Long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
