package com.ticketoffice.model;

public class Ticket {
    private int idTicket;
    private String date;
    private TypeSeat typeSeat;
    private int price;
    private Plane plane;
    private Passenger passenger;
    private Routes routes;

    public Ticket() {
    }

    public Ticket(int idTicket, String date, TypeSeat typeSeat, int price, Plane plane, Passenger passenger, Routes routes) {
        this.idTicket = idTicket;
        this.date = date;
        this.typeSeat = typeSeat;
        this.price = price;
        this.plane = plane;
        this.passenger = passenger;
        this.routes = routes;
    }

    public Ticket(String date, TypeSeat typeSeat, int price, Plane plane, Passenger passenger, Routes routes) {
        this.date = date;
        this.typeSeat = typeSeat;
        this.price = price;
        this.plane = plane;
        this.passenger = passenger;
        this.routes = routes;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TypeSeat getTypeSeat() {
        return typeSeat;
    }

    public void setTypeSeat(TypeSeat typeSeat) {
        this.typeSeat = typeSeat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Routes getRoutes() {
        return routes;
    }

    public void setRoutes(Routes routes) {
        this.routes = routes;
    }
}


