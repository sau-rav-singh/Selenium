package utils;

import com.aventstack.extentreports.ExtentTest;
import listeners.DriverListener;
import org.openqa.selenium.WebDriver;
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
    private static final ThreadLocal<CommonActions> commonActionsThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(String browserName, ExtentTest test) {
        WebDriver driver = DriverFactory.createDriver(browserName);
        unDecoratedDriverThreadLocal.set(driver);

        DriverListener listener = new DriverListener(driver, test);
        WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(driver);
        driverThreadLocal.set(decoratedDriver);

        WebDriverWait wait = new WebDriverWait(decoratedDriver, Duration.ofSeconds(ConfigReader.getTimeout()));
        waitThreadLocal.set(wait);

        CommonActions commonActions = new CommonActions(decoratedDriver, wait);
        commonActionsThreadLocal.set(commonActions);

        logger.info("DriverManager: Driver session initialized for browser: {}", browserName);
    }

    public static WebDriver getUnDecoratedDriver() {
        return unDecoratedDriverThreadLocal.get();
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
        commonActionsThreadLocal.remove();
    }
}
