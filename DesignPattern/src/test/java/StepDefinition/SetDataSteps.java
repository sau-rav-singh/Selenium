package StepDefinition;

import TestContext.SharedStateBetweenScenario;
import TestContext.SharedStateBetweenSteps;
import io.cucumber.java.en.Given;

public class SetDataSteps {
    private final SharedStateBetweenSteps sharedStateBetweenSteps;

    public SetDataSteps(SharedStateBetweenSteps sharedStateBetweenSteps) {
        this.sharedStateBetweenSteps = sharedStateBetweenSteps;
    }

    @Given("I set the important data to {string} in step definition 1")
    public void iSetTheImportantDataToInStepDefinition(String data) {
        sharedStateBetweenSteps.setDatabetweenSteps(data);
        SharedStateBetweenScenario.dataBetweenScenarrio = data+"_Scenario";
    }
}
