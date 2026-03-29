package base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import core.BrowserFactory;
import actions.CommonActions;
import core.observer.*;

public class TestBase {

    protected static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<CommonActions> actionsThreadLocal = new ThreadLocal<>();
    protected static ExtentReports extent;
    private static final TestExecutionSubject executionSubject = new ExecutionSubject();

    static {
        // Observer handles browser lifecycle based on annotations
        new BrowserLaunchObserver(executionSubject, 
            driverThreadLocal::get, 
            driverThreadLocal::set);
    }

    @BeforeSuite
    public void setupSuite() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setUp(Method method, ITestResult result) {
        ExtentTest test = extent.createTest(method.getName());
        testThreadLocal.set(test);

        // Notify observers before test initialization. 
        // BrowserLaunchObserver will handle starting/reusing the browser.
        executionSubject.preTestInit(result, method);

        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            // Fallback for cases where no observer handled the start
            String browser = System.getProperty("browser", "chrome");
            driver = BrowserFactory.getDriver(browser);
            driverThreadLocal.set(driver);
        }

        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitThreadLocal.set(wait);
        
        actionsThreadLocal.set(new CommonActions(driver, wait, test));

        // Notify observers after test initialization
        executionSubject.postTestInit(result, method);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public CommonActions actions() {
        return actionsThreadLocal.get();
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result) {
        executionSubject.preTestCleanup(result, method);
        
        // Notify observers after test cleanup. 
        // BrowserLaunchObserver will decide to quit based on strategy.
        executionSubject.postTestCleanup(result, method);
        
        // Clean up thread locals (except driver if it's being reused)
        testThreadLocal.remove();
        waitThreadLocal.remove();
        actionsThreadLocal.remove();
    }

    @AfterSuite
    public void tearDownSuite() {
        // Ensure any remaining driver instance is closed
        if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
        }
        extent.flush();
    }
}
