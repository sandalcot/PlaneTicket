package com.ticketoffice.repository;

import com.ticketoffice.model.Ticket;
import com.ticketoffice.model.TypeSeat;
import com.ticketoffice.util.ConnectionPool;

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
    private static final String SELECT_ALL = "select * from " + TICKET_TABLE;
    private static final String SELECT_BY_ID = SELECT_ALL + " where id_ticket = ?";
    private static final String SELECT_BY_ID_PASS = SELECT_ALL + " where id_passenger = ?";

    private Properties properties;
    private Connection connection;

    public JavaJDBCTicketRepositoryImpl() {
        try {
            properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("liquibase/liquibase.properties"));
            connection = ConnectionPool.getInstanceConnection(properties).getConnection();
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
            preparedStatement.setInt(4, ticket.getIdPlane());
            preparedStatement.setInt(5, ticket.getIdPass());
            preparedStatement.setInt(6, ticket.getIdRoutes());
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
            preparedStatement.setInt(4, ticket.getIdPlane());
            preparedStatement.setInt(5, ticket.getIdPass());
            preparedStatement.setInt(6, ticket.getIdRoutes());
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
        ticket.setIdTicket(resultSet.getInt("id_ticket"));
        ticket.setDate(resultSet.getString("date"));
        ticket.setTypeSeat(TypeSeat.valueOf(resultSet.getString("type_seat")));
        ticket.setPrice(resultSet.getInt("price"));
        ticket.setIdPlane(resultSet.getInt("id_plane"));
        ticket.setIdPass(resultSet.getInt("id_passenger"));
        ticket.setIdRoutes(resultSet.getInt("id_routes"));
        return ticket;
    }
}
