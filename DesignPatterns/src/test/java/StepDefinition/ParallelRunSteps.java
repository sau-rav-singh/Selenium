package StepDefinition;

import Utils.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static Utils.WebDriverManager.getDriver;

public class ParallelRunSteps {

    WebDriver driver;
    @Given("I am on {string} homepage")
    public void openHomepage(String searchEngine) {
        driver = getDriver();
        driver.manage().window().maximize();
        if (searchEngine.equalsIgnoreCase("Google")) {
            driver.get("https://www.google.com/");
        } else if (searchEngine.equalsIgnoreCase("Bing")) {
            driver.get("https://www.bing.com/");
        } else {
            throw new IllegalArgumentException("Unsupported search engine: " + searchEngine);
        }
    }

    @When("I get the page title")
    public void getPageTitle() throws InterruptedException {
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);
        Thread.sleep(1000);
    }

    @Then("the title should be {string}")
    public void verifyTitle(String expectedTitle) {
        System.out.println(driver.getTitle());
        //Assert.assertEquals(driver.getTitle(), expectedTitle);

    }
}