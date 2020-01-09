package com.ticketoffice.model;

import java.sql.Date;

public class Passenger {
    private int idPass;
    private String name;
    private String surname;
    private String birthdate;

    public Passenger() {
    }

    public Passenger(int idPass, String name, String surname, String birthdate) {
        this.idPass = idPass;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public Passenger(String name, String surname, String birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public int getIdPass() {
        return idPass;
    }

    public void setIdPass(int idPass) {
        this.idPass = idPass;
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
