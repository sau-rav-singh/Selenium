package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            // Log or handle error: properties file not found
            System.err.println("Warning: config.properties not found at src/test/resources/config.properties. Using system properties or defaults.");
        }
    }

    public static String getProperty(String key, String defaultValue) {
        String value = System.getProperty(key); // Check System properties first (CLI overrides)
        if (value == null) {
            value = properties.getProperty(key);
        }
        return (value != null) ? value : defaultValue;
    }

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout", "10"));
    }

    public static String getBaseUrl() {
        return getProperty("baseUrl", "");
    }
}
