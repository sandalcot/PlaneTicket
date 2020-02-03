package com.ticketoffice.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesPool {
    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        return properties;
    }
}
