package hooks;

import com.aventstack.extentreports.ExtentReports;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import manager.ExtentManager;
import manager.ExtentTestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ScreenshotUtils;

import java.io.IOException;

public class DriverHook {

    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(DriverHook.class);
    private static final String LOG_SEPARATOR = "========================================";

    @Before
    public void setUp(Scenario scenario) {
        logger.info(LOG_SEPARATOR);
        ExtentTestManager.setTest(extent.createTest(scenario.getName()));
        logger.info("Starting Scenario: {}", scenario.getName());
        logger.info("Scenario ID: {}", scenario.getId());
        logger.info(LOG_SEPARATOR);
        DriverManager.createDriver();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        logger.info(LOG_SEPARATOR);
        if (scenario.isFailed()) {
            String screenshot = ScreenshotUtils.capture(DriverManager.getDriver(), scenario.getName());
            ExtentTestManager.getTest().fail("Scenario Failed").addScreenCaptureFromPath(screenshot);
        } else {
            ExtentTestManager.getTest().pass("Scenario Passed");
        }
        extent.flush();// this should be done from @AfterSuite(testng  in case of large tests
        ExtentTestManager.unload();
        logger.info("Scenario Status: {}", scenario.getStatus());
        logger.info("Finished Scenario: {}", scenario.getName());
        logger.info(LOG_SEPARATOR);
        DriverManager.quitDriver();
    }

}