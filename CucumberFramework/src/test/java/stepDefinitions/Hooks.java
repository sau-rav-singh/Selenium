package stepDefinitions;

import TestUtils.TestContextSetup;
import io.cucumber.java.After;

public class Hooks {

    TestContextSetup testContextSetup;
    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }
    @After
    public void tearDown(){
        testContextSetup.testBase.getDriver().quit();
    }
}
