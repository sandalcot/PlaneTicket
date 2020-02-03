package com.ticketoffice.model;

public class Routes {
    private Integer id;
    private String departure;
    private String arrival;

    public Routes() {
    }

    public Routes(String departure, String arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    public Routes(Integer id, String departure, String arrival) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Integer getIdRoutes() {
        return id;
    }

    public void setIdRoutes(int idRoutes) {
        this.id = id;
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
