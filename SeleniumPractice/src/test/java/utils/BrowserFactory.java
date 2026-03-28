package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Updated BrowserFactory that respects 'headless' setting from config.properties.
 */
public class BrowserFactory {

    public static WebDriver getDriver(String browserName) {
        Browser browser;
        try {
            browser = Browser.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        boolean isHeadless = ConfigReader.isHeadless();

        return switch (browser) {
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                if (isHeadless) options.addArguments("--headless=new");
                yield new ChromeDriver(options);
            }
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                if (isHeadless) options.addArguments("-headless");
                yield new FirefoxDriver(options);
            }
            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                if (isHeadless) options.addArguments("--headless=new");
                yield new EdgeDriver(options);
            }
        };
    }
}
