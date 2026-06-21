package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties file not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static boolean isIncognito() {
        return Boolean.parseBoolean(properties.getProperty("incognito", "false"));
    }

    public static String getDownloadDirectory() {
        return properties.getProperty("downloadDirectory");
    }

    public static boolean isDisableNotifications() {
        return Boolean.parseBoolean(properties.getProperty("disableNotifications", "true"));
    }

    public static String getPageLoadStrategy() {
        return properties.getProperty("pageLoadStrategy", "normal");
    }
}
