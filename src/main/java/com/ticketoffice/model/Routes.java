package com.ticketoffice.model;

public class Routes {
    private int idRoutes;
    private String departure;
    private String arrival;

    public Routes() {
    }

    public Routes(String departure, String arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    public Routes(int idRoutes, String departure, String arrival) {
        this.idRoutes = idRoutes;
        this.departure = departure;
        this.arrival = arrival;
    }

    public int getIdRoutes() {
        return idRoutes;
    }

    public void setIdRoutes(int idRoutes) {
        this.idRoutes = idRoutes;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
