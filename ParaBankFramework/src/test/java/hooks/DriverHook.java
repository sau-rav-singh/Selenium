package hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverHook {

    private static final Logger logger = LoggerFactory.getLogger(DriverHook.class);
    private static final String LOG_SEPARATOR = "========================================";
    @Before
    public void setUp(Scenario scenario) {
        logger.info(LOG_SEPARATOR);
        logger.info("Starting Scenario: {}", scenario.getName());
        logger.info("Scenario ID: {}", scenario.getId());
        logger.info(LOG_SEPARATOR);
        DriverManager.createDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info(LOG_SEPARATOR);
        logger.info("Scenario Status: {}", scenario.getStatus());
        logger.info("Finished Scenario: {}", scenario.getName());
        logger.info(LOG_SEPARATOR);
        DriverManager.quitDriver();
    }

}