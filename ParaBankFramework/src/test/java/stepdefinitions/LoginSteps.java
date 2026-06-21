package stepdefinitions;

import context.TestContext;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSteps {

    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    private final TestContext testContext;

    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Then("accounts overview page should be displayed")
    public void accountsOverviewPageShouldBeDisplayed() {
        logger.info("Executing step: Verify accounts overview page is displayed");
        Assert.assertTrue(testContext.getFlowManager().getLoginFlow().isUserLoggedIn());
    }

}