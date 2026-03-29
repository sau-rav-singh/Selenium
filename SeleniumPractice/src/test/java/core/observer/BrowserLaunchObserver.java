package core.observer;

import core.Browser;
import core.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BrowserLaunchObserver extends BaseTestBehaviorObserver {
    private final Supplier<WebDriver> driverSupplier;
    private final Consumer<WebDriver> driverConsumer;
    
    // Use ThreadLocal to ensure thread-safety for parallel test execution
    private final ThreadLocal<BrowserConfiguration> currentConfigThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<BrowserConfiguration> previousConfigThreadLocal = new ThreadLocal<>();

    public BrowserLaunchObserver(TestExecutionSubject subject, Supplier<WebDriver> driverSupplier, Consumer<WebDriver> driverConsumer) {
        subject.attach(this);
        this.driverSupplier = driverSupplier;
        this.driverConsumer = driverConsumer;
    }

    @Override
    public void preTestInit(ITestResult testResult, Method memberInfo) {
        BrowserConfiguration currentConfig = getBrowserConfiguration(memberInfo);
        currentConfigThreadLocal.set(currentConfig);
        
        BrowserConfiguration previousConfig = previousConfigThreadLocal.get();
        WebDriver driver = driverSupplier.get();
        
        boolean needsRestart = false;
        
        if (driver == null) {
            needsRestart = true;
        } else if (currentConfig.getBrowserBehavior() == BrowserBehavior.RESTART_EVERY_TIME) {
            needsRestart = true;
        } else if (previousConfig != null && currentConfig.getBrowser() != previousConfig.getBrowser()) {
            needsRestart = true;
        }
        
        if (needsRestart) {
            // Quit old and start new ONLY when we are about to run a test
            restartBrowser(currentConfig);
        } else {
            // If we are reusing the browser, we must clear cookies to maintain Hermetic pattern
            driver.manage().deleteAllCookies();
        }
        
        previousConfigThreadLocal.set(currentConfig);
    }

    @Override
    public void postTestCleanup(ITestResult testResult, Method memberInfo) {
        BrowserConfiguration currentConfig = currentConfigThreadLocal.get();
        if (currentConfig == null) return;

        // Only QUIT at the end of a test if specified. 
        // DO NOT start a new instance here (wait for preTestInit of the next test).
        if (currentConfig.getBrowserBehavior() == BrowserBehavior.RESTART_EVERY_TIME ||
            (currentConfig.getBrowserBehavior() == BrowserBehavior.RESTART_ON_FAIL && testResult.getStatus() == ITestResult.FAILURE)) {
            quitBrowser();
        } else {
            // If browser is reused, clear cookies to ensure next test starts fresh
            WebDriver driver = driverSupplier.get();
            if (driver != null) {
                driver.manage().deleteAllCookies();
            }
        }
    }

    private void quitBrowser() {
        WebDriver driver = driverSupplier.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Log failure to quit
            } finally {
                driverConsumer.accept(null);
            }
        }
    }

    private void restartBrowser(BrowserConfiguration config) {
        quitBrowser();
        driverConsumer.accept(BrowserFactory.getDriver(config.getBrowser().name()));
    }

    private BrowserConfiguration getBrowserConfiguration(Method memberInfo) {
        return Optional.ofNullable(memberInfo.getAnnotation(ExecutionBrowser.class))
                .map(a -> new BrowserConfiguration(a.browser(), a.browserBehavior()))
                .orElseGet(() -> Optional.ofNullable(memberInfo.getDeclaringClass().getAnnotation(ExecutionBrowser.class))
                        .map(a -> new BrowserConfiguration(a.browser(), a.browserBehavior()))
                        .orElseGet(this::getDefaultConfiguration));
    }

    private BrowserConfiguration getDefaultConfiguration() {
        String browserName = System.getProperty("browser", "chrome").toUpperCase();
        Browser browser = Browser.CHROME;
        try {
            browser = Browser.valueOf(browserName);
        } catch (IllegalArgumentException e) {
            // Fallback to default if property is invalid
        }
        return new BrowserConfiguration(browser, BrowserBehavior.RESTART_EVERY_TIME);
    }
}
