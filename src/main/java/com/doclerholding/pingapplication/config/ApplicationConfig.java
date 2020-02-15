package com.doclerholding.pingapplication.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ApplicationConfig.class.getResourceAsStream("/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            LOG.error("Error while reading properties ", e);
            System.exit(0);
        }
    }

    public static String getValue(String value) {
        return properties.getProperty(value);
    }
}
