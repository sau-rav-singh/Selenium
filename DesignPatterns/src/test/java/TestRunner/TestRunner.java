package TestRunner;

import Utils.WebDriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "StepDefinition",
        tags = "@Parallel"
        //tags = "@DI"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Parameters({"browser"})
    @BeforeMethod
    public static void setUpScenario(String browser) {
        WebDriverManager.initDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitBrowser();
    }
}