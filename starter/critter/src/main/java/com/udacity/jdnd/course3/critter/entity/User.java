package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

/**
 * User entity parent class
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized
    @Column
    private String name;

    //No arg constructor
    public User(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
