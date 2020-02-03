package com.ticketoffice.util.mappers;

import com.ticketoffice.model.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassObjectMapper {
    public static Passenger mapToPass(ResultSet resultSet) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setIdPass(resultSet.getInt("id_passenger"));
        passenger.setName(resultSet.getString("name"));
        passenger.setSurname(resultSet.getString("surname"));
        passenger.setBirthdate(resultSet.getString("birthdate"));
        return passenger;
    }
}
