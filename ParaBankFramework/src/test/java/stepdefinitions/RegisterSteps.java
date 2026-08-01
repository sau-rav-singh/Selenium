package stepdefinitions;

import com.aventstack.extentreports.Status;
import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.ExtentTestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class RegisterSteps {

    private static final Logger logger = LoggerFactory.getLogger(RegisterSteps.class);
    private final TestContext testContext;

    public RegisterSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("User is on ParaBank HomePage")
    public void userIsOnParaBankHomePage() {
        logger.info("Executing step: User is on ParaBank HomePage");
        testContext.navigateToHomePage();
        ExtentTestManager.getTest().log(Status.INFO, "User is on ParaBank HomePage");
    }

    @When("User navigates to registration page")
    public void userNavigatesToRegistrationPage() {
        logger.info("Executing step: User navigates to registration page");
        testContext.getDriver().get("https://parabank.parasoft.com/parabank/register.htm");
        ExtentTestManager.getTest().log(Status.INFO, "User navigated to registration page");
    }

    @And("User registers with dynamically generated test data")
    public void userRegistersWithDynamicallyGeneratedTestData() {
        userRegistersWithDynamicallyGeneratedTestData(1);
    }

    @And("User registers with dynamically generated test data for test run {int}")
    public void userRegistersWithDynamicallyGeneratedTestData(int testRun) {
        logger.info("Executing step: User registers with dynamically generated test data for test run {}", testRun);

        // Generate unique test data for this thread
        utils.TestDataFactory.UserRegistrationData userData = utils.TestDataFactory.generateUserRegistrationData().withRepeatPassword();

        // Store user data in scenario context for potential cleanup
        testContext.getScenarioContext().set(context.ScenarioContextKey.USER_REGISTRATION_DATA, userData);

        logger.info("Test Run {}: Generated username: {}", testRun, userData.getUsername());

        // Perform registration
        testContext.getFlowManager().getRegisterFlow().registerUser(userData);

        ExtentTestManager.getTest().log(Status.INFO, "Test Run " + testRun + ": User registered with dynamically generated data. Username: " + userData.getUsername());
    }

    @Then("Registration should be successful")
    public void registrationShouldBeSuccessful() {
        logger.info("Executing step: Verify registration is successful");
        boolean isSuccessful = testContext.getFlowManager().getRegisterFlow().isRegistrationSuccessful();
        Assert.assertTrue(isSuccessful, "Registration should be successful");
        ExtentTestManager.getTest().log(Status.PASS, "Registration verified as successful");
    }

    @And("User should be logged in with the new account")
    public void userShouldBeLoggedInWithTheNewAccount() {
        logger.info("Executing step: Verify user is logged in with new account");
        boolean isLoggedIn = testContext.getFlowManager().getLoginFlow().isUserLoggedIn();
        Assert.assertTrue(isLoggedIn, "User should be logged in after registration");
        ExtentTestManager.getTest().log(Status.PASS, "User verified as logged in with new account");
    }
}
