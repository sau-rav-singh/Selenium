package driver;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void createDriver() {
        if (driver.get() == null) {
            driver.set(DriverFactory.createDriver(ConfigReader.getBrowser()));
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
