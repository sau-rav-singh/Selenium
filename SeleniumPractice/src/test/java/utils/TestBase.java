package utils;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestBase {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<CommonActions> actionsThreadLocal = new ThreadLocal<>();
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
        driverThreadLocal.set(driver);

        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitThreadLocal.set(wait);
        
        actionsThreadLocal.set(new CommonActions(driver, wait, test));
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public CommonActions actions() {
        return actionsThreadLocal.get();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        driverThreadLocal.remove();
        testThreadLocal.remove();
        waitThreadLocal.remove();
        actionsThreadLocal.remove();
    }

    @AfterSuite
    public void tearDownSuite() {
        extent.flush();
    }
}
