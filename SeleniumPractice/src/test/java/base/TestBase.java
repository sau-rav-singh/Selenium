package base;

import java.lang.reflect.Method;
import java.time.Duration;

import utils.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.BrowserFactory;
import listeners.DriverListener;
import listeners.TestListener;
import org.openqa.selenium.support.events.EventFiringDecorator;

@Listeners(TestListener.class)
public class TestBase {

    protected static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
    protected static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    protected static final ThreadLocal<CommonActions> COMMON_ACTIONS_THREAD_LOCAL = new ThreadLocal<>();
    protected static ExtentReports extent;

    @BeforeSuite
    public void setupSuite() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setUp(Method method) {
        ExtentTest test = extent.createTest(method.getName());
        testThreadLocal.set(test);

        String browser = System.getProperty("browser", "chrome");
        WebDriver driver = BrowserFactory.getDriver(browser);

        DriverListener listener = new DriverListener(driver, test);
        driver = new EventFiringDecorator<>(listener).decorate(driver);
        driverThreadLocal.set(driver);

        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitThreadLocal.set(wait);
        
        CommonActions commonActions = new CommonActions(driver, wait);
        COMMON_ACTIONS_THREAD_LOCAL.set(commonActions);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public CommonActions actions() {
        return COMMON_ACTIONS_THREAD_LOCAL.get();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        driverThreadLocal.remove();
        testThreadLocal.remove();
        waitThreadLocal.remove();
        COMMON_ACTIONS_THREAD_LOCAL.remove();
    }

    @AfterSuite
    public void tearDownSuite() {
        extent.flush();
    }
}
