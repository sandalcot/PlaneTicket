package com.ticketoffice.util.mappers;

import com.ticketoffice.model.Routes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoutesObjectMapper {
    public static Routes mapToRoutes(ResultSet resultSet) throws SQLException {
        Routes route = new Routes();
        route.setIdRoutes(resultSet.getInt("id_routes"));
        route.setDeparture(resultSet.getString("departure"));
        route.setArrival(resultSet.getString("arrival"));
        return route;
    }
}
