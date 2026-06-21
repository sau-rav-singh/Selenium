package stepdefinitions;

import context.ScenarioContextKey;
import context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenAccountSteps {

    private static final Logger logger = LoggerFactory.getLogger(OpenAccountSteps.class);
    private final TestContext testContext;

    public OpenAccountSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("User navigates to Open New Account link")
    public void userNavigatesToOpenNewAccountLink() {
        logger.info("Executing step: User navigates to Open New Account link");
        testContext.getFlowManager().getOpenAccountFlow().navigateToOpenAccountPage();
    }

    @When("^User selects accountType as (.*) and base account as (.*)")
    public void userSelectsAccountTypeAndBaseAccountAndOpensANewAccount(String accountType, String baseAccount) {
        logger.info("Executing step: User selects account type: {} and base account: {}", accountType, baseAccount);
        String accountNumber = testContext.getFlowManager().getOpenAccountFlow().createNewAccount(accountType, baseAccount);
        testContext.getScenarioContext().set(ScenarioContextKey.ACCOUNT_NUMBER, accountNumber);
        logger.info("New account created with number: {}", accountNumber);

    }

    @Then("The new account number should be displayed")
    public void theNewAccountNumberShouldBeDisplayed() {
        logger.info("Executing step: Verify new account number is displayed");
        String accountNumber = testContext.getScenarioContext().get(ScenarioContextKey.ACCOUNT_NUMBER, String.class);
        Assert.assertNotNull(accountNumber, "New account number should not be null");
        Assert.assertFalse(accountNumber.isEmpty(), "New account number should not be empty");
    }
}
