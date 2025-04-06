package stepDefinitions;

import TestUtils.TestContextSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.LandingPage;

public class LandingPageStepDefinitions {
    TestContextSetup testContextSetup;
    LandingPage landingPage;

    public LandingPageStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
    }

    @Given("User is on GreenCart Landing page")
    public void userIsOnGreenCartLandingPage() {
        Assert.assertTrue(landingPage.getTitleLandingPage().contains("GreenKart"));
    }

    @When("^User searches with Shortname (.*) and extracts actual name of product$")
    public void userSearchedWithShortnameNameAndExtractedActualNameOfProduct(String productName) {
        landingPage.enterSearchTerm(productName);
        testContextSetup.searchResults = landingPage.extractProductNames();
        testContextSetup.searchResults.forEach(System.out::println);
    }

    @When("Added {string} items of the selected product to cart")
    public void Added_items_product(String quantity)
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        landingPage.incrementQuantity(Integer.parseInt(quantity));
        landingPage.addToCart();

    }

}
