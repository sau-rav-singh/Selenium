package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            properties = new Properties();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static boolean isHeadless() {
        String headless = getProperty("headless");
        return headless != null && headless.equalsIgnoreCase("true");
    }
}
