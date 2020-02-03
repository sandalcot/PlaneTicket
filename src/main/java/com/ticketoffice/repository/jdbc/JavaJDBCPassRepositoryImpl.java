package com.ticketoffice.repository.jdbc;

import com.ticketoffice.model.Passenger;
import com.ticketoffice.repository.PassengerRepository;
import com.ticketoffice.util.ConnectionPool;
import com.ticketoffice.util.PropertiesPool;
import com.ticketoffice.util.mappers.PassObjectMapper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JavaJDBCPassRepositoryImpl implements PassengerRepository {
    private static final String PASS_TABLE = "passenger";
    private static final String INSERT = "insert into " + PASS_TABLE + " (name, surname, birthdate) VALUES(?, ?, ?)";
    private static final String UPDATE = "update " + PASS_TABLE + " set name = ?, surname = ?, birthdate = ? where id_passenger = ?";
    private static final String DELETE = "delete from " + PASS_TABLE + " where id_passenger = ?";
    private static final String SELECT_ALL = "select * from " + PASS_TABLE;
    private static final String SELECT_BY_ID = SELECT_ALL + " where id_passenger = ?";

    @Override
    public void create(Passenger passenger) throws Exception {
       setParameterPass(passenger, INSERT);
    }

    @Override
    public void update(Passenger passenger) throws Exception {
      updatePass(passenger,UPDATE);
    }

    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException, InterruptedException, IOException {
        Connection connection = ConnectionPool.getInstanceConnection().getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(DELETE)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection().closeConnection(connection);
        }
    }

    @Override
    public List<Passenger> getAll() throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        Connection connection = ConnectionPool.getInstanceConnection().getConnection();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL)) {
            List<Passenger> accounts = new ArrayList<>();
            while (resultSet.next()) {
                accounts.add(PassObjectMapper.getPassMapper(resultSet));
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstanceConnection().closeConnection(connection);
        }
    }

    @Override
    public Passenger getId(Integer id) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection().getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_BY_ID)) {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return PassObjectMapper.getPassMapper(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionPool.getInstanceConnection().closeConnection(connection);
        }
    }

    private void setParameterPass(Passenger passenger, String query) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getSurname());
            preparedStatement.setString(3, passenger.getBirthdate());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection().closeConnection(connection);
        }
    }

    private void updatePass(Passenger passenger, String query) throws Exception {
        Connection connection = ConnectionPool.getInstanceConnection().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getSurname());
            preparedStatement.setString(3, passenger.getBirthdate());
            preparedStatement.setInt(4,passenger.getIdPass());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstanceConnection().closeConnection(connection);
        }
    }
}
