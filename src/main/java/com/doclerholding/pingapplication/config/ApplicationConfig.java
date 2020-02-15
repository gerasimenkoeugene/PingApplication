package com.doclerholding.pingapplication.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ApplicationConfig.class.getResourceAsStream("/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String value) {
        return properties.getProperty(value);
    }
}
