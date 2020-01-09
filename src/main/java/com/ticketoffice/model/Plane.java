package com.ticketoffice.model;

public class Plane {
    private int idPlane;
    private String name;

    public Plane() {
    }

    public Plane(int idPlane, String name) {
        this.idPlane = idPlane;
        this.name = name;
    }

    public Plane(String name) {
        this.name = name;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
