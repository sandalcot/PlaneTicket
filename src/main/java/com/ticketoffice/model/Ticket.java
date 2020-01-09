package com.ticketoffice.model;

public class Ticket {
    private int idTicket;
    private String date;
    private TypeSeat typeSeat;
    private int price;
    private int idPlane;
    private int idPass;
    private int idRoutes;

    public Ticket() {
    }

    public Ticket(int idTicket, String date, TypeSeat typeSeat, int price, int idPlane, int idPass, int idRoutes) {
        this.idTicket = idTicket;
        this.date = date;
        this.typeSeat = typeSeat;
        this.price = price;
        this.idPlane = idPlane;
        this.idPass = idPass;
        this.idRoutes = idRoutes;
    }

    public Ticket(String date, TypeSeat typeSeat, int price, int idPlane, int idPass, int idRoutes) {
        this.date = date;
        this.typeSeat = typeSeat;
        this.price = price;
        this.idPlane = idPlane;
        this.idPass = idPass;
        this.idRoutes = idRoutes;
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

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public int getIdPass() {
        return idPass;
    }

    public void setIdPass(int idPass) {
        this.idPass = idPass;
    }

    public int getIdRoutes() {
        return idRoutes;
    }

    public void setIdRoutes(int idRoutes) {
        this.idRoutes = idRoutes;
    }
}
