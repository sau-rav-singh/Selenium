package utils;

import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentTest;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import listeners.TestListener;

@Listeners({TestListener.class, ExtentReportListener.class})
public class TestBase {

    @BeforeMethod
    public void setUp(Method method) {
        ExtentTest test = ExtentReportListener.extent.createTest(method.getName());
        ExtentManager.setExtentTest(test);

        String browser = ConfigReader.getBrowser();
        DriverManager.setDriver(browser, test);

        getDriver().manage().window().maximize();
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
        ExtentManager.removeExtentTest();
    }
}
