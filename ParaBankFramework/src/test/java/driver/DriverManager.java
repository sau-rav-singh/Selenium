package driver;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class DriverManager {
    @Getter
    private static WebDriver driver;

    public static void createDriver() {
        if (driver == null) {
            String browser = ConfigReader.getBrowser();
            driver = DriverFactory.createDriver(browser);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
