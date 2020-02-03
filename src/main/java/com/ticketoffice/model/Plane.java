package com.ticketoffice.model;

public class Plane {
    private Integer id;
    private String name;

    public Plane() {
    }

    public Plane(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Plane(String name) {
        this.name = name;
    }

    public Integer getIdPlane() {
        return id;
    }

    public void setIdPlane(int idPlane) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
