package com.ticketoffice.jdbc;

import com.ticketoffice.model.*;
import com.ticketoffice.repository.TicketRepository;
import com.ticketoffice.util.ConnectionPool;
import com.ticketoffice.util.mappers.PassObjectMapper;
import com.ticketoffice.util.mappers.PlaneObjectMapper;
import com.ticketoffice.util.mappers.RoutesObjectMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JavaJDBCTicketRepositoryImpl implements TicketRepository {
    private static final String TICKET_TABLE = "ticket";
    private static final String INSERT = "insert into " + TICKET_TABLE + " (date, type_seat, price, id_plane, id_passenger," +
            "id_routes) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE = "update " + TICKET_TABLE + " set date = ?, type_seat = ?, price = ?, id_plane = ?," +
            "id_passenger = ?, id_routes = ? where id_ticket = ?";
    private static final String DELETE = "delete from " + TICKET_TABLE + " where id_ticket = ?";
    private static final String SELECT_ALL = "select t.id_ticket, t.date, t.type_seat,t.price,r.departure," +
            "r.arrival, pl.name_plane, pas.name,pas.surname,pas.birthdate from ticket t inner join routes r on t.id_routes = r.id_routes" +
            "inner join plane pl on t.id_plane = pl.id_plane inner join passenger pas on t.id_passenger = pas.id_passenger";
    private static final String SELECT_BY_ID = SELECT_ALL + " where t.id_ticket = ?";
    private static final String SELECT_BY_ID_PASS = SELECT_ALL + " where id_passenger = ?";

    private Properties properties;

    public JavaJDBCTicketRepositoryImpl() {
        try {
            properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("liquibase/liquibase.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Ticket ticket) throws Exception {
        setParameterTicket(ticket, INSERT);
    }

    @Override
    public void update(Ticket ticket) throws Exception {
        updateTicket(ticket, UPDATE);
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
    public List<Ticket> getAll() throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL)) {
            List<Ticket> accounts = new ArrayList<>();
            while (resultSet.next()) {
                accounts.add(createTicketFromResult(resultSet));
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
    public Ticket getId(Integer id) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_BY_ID)) {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createTicketFromResult(resultSet);
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

    @Override
    public List<Ticket> getIdTicketPass(Integer id) throws SQLException, ClassNotFoundException, InterruptedException {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        List<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_BY_ID_PASS)) {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                     tickets.add(createTicketFromResult(resultSet));
                }
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    private void setParameterTicket(Ticket ticket, String query) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ticket.getDate());
            preparedStatement.setString(2, ticket.getTypeSeat().toString());
            preparedStatement.setInt(3, ticket.getPrice());
            preparedStatement.setInt(4, ticket.getPlane().getIdPlane());
            preparedStatement.setInt(5, ticket.getPassenger().getIdPass());
            preparedStatement.setInt(6, ticket.getRoutes().getIdRoutes());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    private void updateTicket(Ticket ticket, String query) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection(properties).getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ticket.getDate());
            preparedStatement.setString(2, ticket.getTypeSeat().toString());
            preparedStatement.setInt(3, ticket.getPrice());
            preparedStatement.setInt(4, ticket.getPlane().getIdPlane());
            preparedStatement.setInt(5, ticket.getPassenger().getIdPass());
            preparedStatement.setInt(6, ticket.getRoutes().getIdRoutes());
            preparedStatement.setInt(7, ticket.getIdTicket());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection(properties).closeConnection(connection);
        }
    }

    protected Ticket createTicketFromResult(ResultSet resultSet) throws SQLException {
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
