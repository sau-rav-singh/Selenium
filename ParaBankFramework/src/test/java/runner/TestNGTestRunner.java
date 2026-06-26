package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features", glue = {"stepdefinitions", "hooks"}, monochrome = true,
        tags = "@Login",
        plugin = {"html:target/cucumber.html", "json:target/cucumber.json"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider()
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
