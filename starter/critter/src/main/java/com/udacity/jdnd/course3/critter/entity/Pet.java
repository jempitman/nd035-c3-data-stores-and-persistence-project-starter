package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private PetType type;

    private String name;

    private LocalDate birthDate;

    @Column(nullable = true)
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="customerId", nullable=false)
    private Customer customer;

    public Pet() {
    }

    public Pet(PetType type, String name, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
