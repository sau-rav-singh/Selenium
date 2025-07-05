package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> tlDriver = ThreadLocal.withInitial(() -> null);

    public static void initDriver(String browser) {
        if (tlDriver.get() == null) {
            WebDriver driver = switch (browser.toLowerCase()) {
                case "chrome" -> new ChromeDriver();
                case "firefox" -> new FirefoxDriver();
                case "edge" -> new EdgeDriver();
                default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
            };
            tlDriver.set(driver);
        }
    }

    public static WebDriver getDriver() {
        WebDriver driver = tlDriver.get();
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitBrowser() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}
