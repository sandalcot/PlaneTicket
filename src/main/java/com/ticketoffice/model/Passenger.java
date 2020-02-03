package com.ticketoffice.model;

import java.sql.Date;

public class Passenger {
    private Integer id;
    private String name;
    private String surname;
    private String birthdate;

    public Passenger() {
    }

    public Passenger(Integer id, String name, String surname, String birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public Passenger(String name, String surname, String birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public Integer getIdPass() {
        return id;
    }

    public void setIdPass(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
