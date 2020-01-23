package com.ticketoffice.jdbc;

import com.ticketoffice.model.Plane;
import com.ticketoffice.repository.PlaneRepository;
import com.ticketoffice.util.ConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JavaJDBCPlaneRepositoryImpl implements PlaneRepository {
    private static final String PLANE_TABLE = "plane";
    private static final String INSERT = "insert into " + PLANE_TABLE + " (name_plane) VALUES(?)";
    private static final String UPDATE = "update " + PLANE_TABLE + " set name_plane = ? where id_plane = ?";
    private static final String DELETE = "delete from " + PLANE_TABLE + " where id_plane = ?";
    private static final String SELECT_ALL = "select * from " + PLANE_TABLE;
    private static final String SELECT_BY_ID = SELECT_ALL + " where id_plane = ?";

    private Properties properties;
    private Connection connection;

    public JavaJDBCPlaneRepositoryImpl() {
        try {
            properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("liquibase/liquibase.properties"));
            connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Plane plane) throws Exception {
        setParameterPlane(plane, INSERT);
    }

    @Override
    public void update(Plane plane) throws Exception {
        updatePlane(plane,UPDATE);
    }

    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException, InterruptedException {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(DELETE)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    @Override
    public List<Plane> getAll() throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL)) {
            List<Plane> accounts = new ArrayList<>();
            while (resultSet.next()) {
                accounts.add(createPlaneFromResult(resultSet));
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    @Override
    public Plane getId(Integer id) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_BY_ID)) {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createPlaneFromResult(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    private void setParameterPlane(Plane plane, String query) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, plane.getName());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    private void updatePlane(Plane plane, String query) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, plane.getName());
            preparedStatement.setInt(2,plane.getIdPlane());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    protected Plane createPlaneFromResult(ResultSet resultSet) throws SQLException {
        Plane plane = new Plane();
        plane.setIdPlane(resultSet.getInt("id_plane"));
        plane.setName(resultSet.getString("name_plane"));
        return plane;
    }
}
