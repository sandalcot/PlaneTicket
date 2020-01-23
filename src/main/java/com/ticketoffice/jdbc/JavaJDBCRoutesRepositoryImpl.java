package com.ticketoffice.jdbc;

import com.ticketoffice.model.Routes;
import com.ticketoffice.repository.RoutesRepository;
import com.ticketoffice.util.ConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JavaJDBCRoutesRepositoryImpl implements RoutesRepository {
    private static final String ROUTES_TABLE = "routes";
    private static final String INSERT = "insert into " + ROUTES_TABLE + " (departure, arrival) VALUES(?,?)";
    private static final String UPDATE = "update " + ROUTES_TABLE + " set departure = ?, arrival = ? where id_routes = ?";
    private static final String DELETE = "delete from " + ROUTES_TABLE + " where id_routes = ?";
    private static final String SELECT_ALL = "select * from " + ROUTES_TABLE;
    private static final String SELECT_BY_ID = SELECT_ALL + " where id_routes = ?";

    private Properties properties;
    private Connection connection;

    public JavaJDBCRoutesRepositoryImpl() {
        try {
            properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("liquibase/liquibase.properties"));
            connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Routes routes) throws Exception {
        setParameterRouter(routes, INSERT);
    }

    @Override
    public void update(Routes routes) throws Exception {
        updateRoutes(routes,UPDATE);
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
    public List<Routes> getAll() throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL)) {
            List<Routes> accounts = new ArrayList<>();
            while (resultSet.next()) {
                accounts.add(createRouteFromResult(resultSet));
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
    public Routes getId(Integer id) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_BY_ID)) {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createRouteFromResult(resultSet);
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

    private void setParameterRouter(Routes routes, String query) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, routes.getDeparture());
            preparedStatement.setString(2, routes.getArrival());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    private void updateRoutes(Routes routes, String query) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, routes.getDeparture());
            preparedStatement.setString(2, routes.getArrival());
            preparedStatement.setInt(3,routes.getIdRoutes());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    protected Routes createRouteFromResult(ResultSet resultSet) throws SQLException {
        Routes route = new Routes();
        route.setIdRoutes(resultSet.getInt("id_routes"));
        route.setDeparture(resultSet.getString("departure"));
        route.setArrival(resultSet.getString("arrival"));
        return route;
    }
}
