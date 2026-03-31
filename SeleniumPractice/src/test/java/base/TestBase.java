package base;

import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.ConfigReader;
import utils.CommonActions;
import utils.DriverManager;
import listeners.TestListener;

@Listeners(TestListener.class)
public class TestBase {

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
        DriverManager.setTest(test);
        
        String browser = ConfigReader.getBrowser();
        DriverManager.setDriver(browser, test);
        
        getDriver().manage().window().maximize();
        
        String baseUrl = ConfigReader.getBaseUrl();
        if (baseUrl != null && !baseUrl.isEmpty()) {
            getDriver().get(baseUrl);
        }
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public WebDriver getUnDecoratedDriver() {
        return DriverManager.getUnDecoratedDriver();
    }

    public CommonActions commonActions() {
        return DriverManager.getCommonActions();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @AfterSuite
    public void tearDownSuite() {
        extent.flush();
    }
}
