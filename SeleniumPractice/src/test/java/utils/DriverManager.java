package utils;

import com.aventstack.extentreports.ExtentTest;
import listeners.DriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> unDecoratedDriverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<CommonActions> commonActionsThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(String browser, ExtentTest test) {
        WebDriver driver = BrowserFactory.getDriver(browser);
        unDecoratedDriverThreadLocal.set(driver);

        DriverListener listener = new DriverListener(driver, test);
        WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(driver);
        driverThreadLocal.set(decoratedDriver);

        WebDriverWait wait = new WebDriverWait(decoratedDriver, Duration.ofSeconds(ConfigReader.getTimeout()));
        waitThreadLocal.set(wait);

        CommonActions commonActions = new CommonActions(decoratedDriver, wait);
        commonActionsThreadLocal.set(commonActions);
    }

    public static WebDriver getUnDecoratedDriver() {
        return unDecoratedDriverThreadLocal.get();
    }
    
    public static void setTest(ExtentTest test) {
        testThreadLocal.set(test);
    }

    public static CommonActions getCommonActions() {
        return commonActionsThreadLocal.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        driverThreadLocal.remove();
        unDecoratedDriverThreadLocal.remove();
        waitThreadLocal.remove();
        testThreadLocal.remove();
        commonActionsThreadLocal.remove();
    }
}
