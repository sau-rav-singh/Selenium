package utils;

import com.aventstack.extentreports.ExtentTest;
import listeners.DriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> unDecoratedDriverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<CommonActions> commonActionsThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(String browserName, ExtentTest test) {
        Browser browser;
        try {
            browser = Browser.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Unsupported browser: {}. Falling back to CHROME.", browserName);
            browser = Browser.CHROME;
        }

        WebDriver driver = createDriver(browser);
        unDecoratedDriverThreadLocal.set(driver);

        DriverListener listener = new DriverListener(driver, test);
        WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(driver);
        driverThreadLocal.set(decoratedDriver);

        WebDriverWait wait = new WebDriverWait(decoratedDriver, Duration.ofSeconds(ConfigReader.getTimeout()));
        waitThreadLocal.set(wait);

        CommonActions commonActions = new CommonActions(decoratedDriver, wait);
        commonActionsThreadLocal.set(commonActions);

        logger.info("DriverManager: {} session initialized.", browser);
    }

    public static WebDriver getUnDecoratedDriver() {
        return unDecoratedDriverThreadLocal.get();
    }

    public static void setExtentTest(ExtentTest test) {
        testThreadLocal.set(test);
    }

    public static CommonActions getCommonActions() {
        return commonActionsThreadLocal.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            logger.info("DriverManager: Quitting driver session.");
            getDriver().quit();
        }
        driverThreadLocal.remove();
        unDecoratedDriverThreadLocal.remove();
        waitThreadLocal.remove();
        testThreadLocal.remove();
        commonActionsThreadLocal.remove();
    }

    private static WebDriver createDriver(Browser browser) {
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
