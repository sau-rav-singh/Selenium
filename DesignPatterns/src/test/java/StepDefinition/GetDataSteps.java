package StepDefinition;

import TestContext.SharedStateBetweenScenario;
import TestContext.SharedStateBetweenSteps;
import io.cucumber.java.en.Then;

import static org.testng.Assert.assertEquals;

public class GetDataSteps {
    private final SharedStateBetweenSteps sharedStateBetweenSteps;

    public GetDataSteps(SharedStateBetweenSteps sharedStateBetweenSteps) {
        this.sharedStateBetweenSteps = sharedStateBetweenSteps;
    }

    @Then("I should have access to the important data in step definition 2")
    public void iShouldHaveAccessToTheImportantDataInStepDefinition() {
        String data = sharedStateBetweenSteps.getDatabetweenSteps();
        System.out.println("The important data between steps is: " + data);
        assertEquals(data, "expectedData");
    }

    @Then("I should have access to the important data in Feature 1")
    public void iShouldHaveAccessToTheImportantDataInFeature() {
        String data = SharedStateBetweenScenario.dataBetweenScenarrio;
        System.out.println("The Shared data between Scenario is: " + data);
        assertEquals(data, "expectedData_Scenario");
    }
}
