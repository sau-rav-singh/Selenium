package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        Browser browser;
        try {
            browser = Browser.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            browser = Browser.CHROME; // Default
        }

        boolean isHeadless = ConfigReader.isHeadless();
        return switch (browser) {
            case CHROME -> createChromeDriver(isHeadless);
            case FIREFOX -> createFirefoxDriver(isHeadless);
            case EDGE -> createEdgeDriver(isHeadless);
        };
    }

    private static WebDriver createChromeDriver(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) options.addArguments("--headless=new");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean isHeadless) {
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless) options.addArguments("-headless");
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean isHeadless) {
        EdgeOptions options = new EdgeOptions();
        if (isHeadless) options.addArguments("--headless=new");
        return new EdgeDriver(options);
    }

    public enum Browser {
        CHROME, FIREFOX, EDGE
    }
}
