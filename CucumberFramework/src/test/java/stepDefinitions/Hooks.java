package stepDefinitions;

import TestUtils.TestBase;
import TestUtils.TestContextSetup;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    TestContextSetup testContextSetup;
    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (testContextSetup.testBase.getDriver() != null) {
            testContextSetup.testBase.getDriver().quit();
            TestBase.clearDriver();
        }
    }
}
