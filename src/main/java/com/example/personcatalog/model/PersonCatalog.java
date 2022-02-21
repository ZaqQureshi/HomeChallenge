package com.example.personcatalog.model;

import javax.persistence.*;

@Entity
@Table(name = "person_catalog")
public class PersonCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "worked_on")
    private String workedOn;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkedOn() {
        return this.workedOn;
    }

    public void setWorkedOn(String workedOn) {
        this.workedOn = workedOn;
    }
}
