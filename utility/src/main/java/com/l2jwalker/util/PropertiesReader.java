package com.l2jwalker.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final String PROPERTIES_FILE_NAME = "l2jwalker.properties";
    private static final Logger _log = Logger.getLogger(PropertiesReader.class);

    private static Properties properties;

    static {
        properties = new Properties();
        reload();
    }

    public static String getProperty(String key) {
        synchronized (properties) {
            return properties.getProperty(key);
        }
    }

    public static Properties getProperties() {
        synchronized (properties) {
            Properties result = new Properties();
            for (Object key : properties.keySet()) {
                result.put(key, properties.get(key));
            }
            return result;
        }
    }

    public static Integer getIntegerProperty(String key) {
        String stringValue = properties.getProperty(key);
        return stringValue == null ? null : Integer.parseInt(stringValue);
    }

    public static Boolean getBooleanProperty(String key) {
        String stringValue = properties.getProperty(key);
        return stringValue == null ? null : Boolean.parseBoolean(stringValue);
    }

    public static void reload() {
        synchronized (properties) {
            properties = readProperties(PROPERTIES_FILE_NAME);
        }
    }

    public static Properties readProperties(String path) {
        try {
            Properties result = new Properties();
            InputStream stream = PropertiesReader.class.getClassLoader().getResourceAsStream(path);
            if (stream == null) {
                File file = new File(path);
                if (file.canRead()) {
                    stream = new FileInputStream(file);
                }
            }
            if (stream != null) {
                result.load(stream);
            }
            return result;
        } catch (IOException e) {
           System.out.println("Failed to read properties from: " + path + e.getMessage());
            _log.warn("Failed to read properties from: " + path + e.getMessage());
        }
        return null;
    }

}
