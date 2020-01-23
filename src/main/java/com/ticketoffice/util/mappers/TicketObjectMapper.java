package com.ticketoffice.util.mappers;

import com.ticketoffice.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketObjectMapper {
    public static Ticket getTicketMapper(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        Plane plane = new Plane();
        Passenger passenger = new Passenger();
        Routes routes = new Routes();
        ticket.setIdTicket(resultSet.getInt("t.id_ticket"));
        ticket.setDate(resultSet.getString("t.date"));
        ticket.setTypeSeat(TypeSeat.valueOf(resultSet.getString("t.type_seat")));
        ticket.setPrice(resultSet.getInt("t.price"));

        plane.setIdPlane(resultSet.getInt("id_plane"));
        ticket.setPlane(plane);
        passenger.setIdPass(resultSet.getInt("id_passenger"));
        ticket.setPassenger(passenger);
        routes.setIdRoutes(resultSet.getInt("id_routes"));
        ticket.setRoutes(routes);
        return ticket;
    }
}
