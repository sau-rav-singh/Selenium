package stepdefinitions;

import context.ScenarioContextKey;
import context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferFundsSteps {

    private static final Logger logger = LoggerFactory.getLogger(TransferFundsSteps.class);
    private final TestContext testContext;

    public TransferFundsSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("User navigates to transfer funds page")
    public void userNavigatesToTransferFundsPage() {
        logger.info("Executing step: User navigates to transfer funds page");
        testContext.getFlowManager().getTransferFundsFlow().navigateToFundTransferPage();
    }

    @When("^User enters amount as(.*) from account as(.*) and to account as(.*)")
    public void userEntersAmountAsAmountFromAccountAsFromAccountAndToAccountAsToAccount(String amount, String fromAccount, String toAccount) {
        logger.info("Executing step: User transfers amount: {} from account: {} to account: {}", amount, fromAccount, toAccount);
        testContext.getFlowManager().getTransferFundsFlow().transferToNewAccount(amount, fromAccount, toAccount);
    }

    @Then("Transfer should get completed")
    public void transferShouldGetCompleted() {
        logger.info("Executing step: Verify transfer is completed successfully");
        Assert.assertTrue(testContext.getFlowManager().getTransferFundsFlow().isTransferSuccessful());
    }

    @When("^User enters amount as(.*) from baseAccount as(.*) and to new account")
    public void userEntersAmountAsAmountFromBaseAccountAsBaseAccountAndToNewAccount(String amount, String baseAccount) {
        String toAccountNumber = testContext.getScenarioContext().get(ScenarioContextKey.ACCOUNT_NUMBER, String.class);
        logger.info("Executing step: User transfers amount: {} from base account: {} to new account: {}", amount, baseAccount, toAccountNumber);
        testContext.getFlowManager().getTransferFundsFlow().transferToNewAccount(amount, baseAccount,toAccountNumber);
    }
}
