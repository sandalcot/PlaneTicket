package com.ticketoffice.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final int POOL_SIZE = 10;
    private static ConnectionPool instance;
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static BlockingQueue<Connection> pool;
    private Properties properties;

    private ConnectionPool() throws SQLException, ClassNotFoundException, IOException {
        PropertiesPool propertiesPool = new PropertiesPool();
        properties = propertiesPool.getProperties();
        String DRIVER = properties.getProperty("driver");
        Class.forName(DRIVER);
        URL = properties.getProperty("url");
        USER = properties.getProperty("user");
        PASSWORD = properties.getProperty("password");
        pool = new LinkedBlockingQueue<>(POOL_SIZE);
        initConnections();
    }

    public static ConnectionPool getInstanceConnection() throws SQLException, ClassNotFoundException, IOException {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public static Connection getConnection() throws InterruptedException, SQLException {
        Connection connection = pool.take();
        if (!connection.isValid(0)) {
            connection.close();
            connection = createConnection();
        }
        return connection;
    }

    private void initConnections() throws SQLException {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.offer(createConnection());
        }
    }

    private static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
