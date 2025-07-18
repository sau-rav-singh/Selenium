package stepDefinitions;

import testUtils.TestBase;
import testUtils.TestContextSetup;
import io.cucumber.java.After;

public class Hooks {

    TestContextSetup testContextSetup;

    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @After
    public void tearDown() {
        if (testContextSetup.testBase.getDriver() != null) {
            testContextSetup.testBase.getDriver().quit();
            TestBase.clearDriver();
        }
    }
}
