package com.ticketoffice.util.mappers;

import com.ticketoffice.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketObjectMapper {
    public static Ticket getTicketMapper(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(resultSet.getInt("t.id_ticket"));
        ticket.setDate(resultSet.getString("t.date"));
        ticket.setTypeSeat(TypeSeat.valueOf(resultSet.getString("t.type_seat")));
        ticket.setPrice(resultSet.getInt("t.price"));
        Plane plane = PlaneObjectMapper.getPlaneMapper(resultSet);
        ticket.setPlane(plane);
        Passenger passenger = PassObjectMapper.getPassMapper(resultSet);
        ticket.setPassenger(passenger);
        Routes routes = RoutesObjectMapper.getRoutesMapper(resultSet);
        ticket.setRoutes(routes);
        return ticket;
    }
}
