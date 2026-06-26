package stepdefinitions;

import com.aventstack.extentreports.Status;
import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import manager.ExtentTestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonSteps {

    private static final Logger logger = LoggerFactory.getLogger(CommonSteps.class);
    private final TestContext testContext;

    public CommonSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("User in on ParaBank HomePage")
    public void userInOnParaBankHomePage() {
        logger.info("Executing step: User is on ParaBank HomePage");
        testContext.navigateToHomePage();
        ExtentTestManager.getTest().log(Status.INFO, "User is on ParaBank HomePage");
    }

    @When("^User logs into Parabank with username (.*) and password (.*)$")
    public void userLogsIntoParabankWithUsernameUsernameAndPasswordPassword(String username, String password) {
        logger.info("Executing step: User logs into Parabank with username: {}", username);
        testContext.getFlowManager().getLoginFlow().login(username, password);
    }
}
