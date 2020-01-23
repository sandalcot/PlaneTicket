package com.ticketoffice.util.mappers;

import com.ticketoffice.model.Plane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaneObjectMapper {
    public static Plane getPlaneMapper(ResultSet resultSet) throws SQLException {
        Plane plane = new Plane();
        plane.setIdPlane(resultSet.getInt("id_plane"));
        plane.setName(resultSet.getString("name_plane"));
        return plane;
    }
}
