package stepDefinitions;

import TestUtils.TestContextSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.OffersPage;

public class OfferPageStepDefinitions {

    TestContextSetup testContextSetup;
    OffersPage offersPage;

    public OfferPageStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        offersPage = testContextSetup.pageObjectManager.getOffersPage();
    }

    @Then("^User searches for (.*) shortname in offers page$")
    public void userSearchedForNameShortnameInOffersPage(String productName) {

        offersPage.clickTopDeals();
        testContextSetup.genericUtils.switchToOffersPage();
        testContextSetup.productsOnDeal = offersPage.searchProduct(productName);
    }

    @And("Validate that product name in offers page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {
        Assert.assertEquals(testContextSetup.productsOnDeal, testContextSetup.searchResults, "Name Assert");
        testContextSetup.genericUtils.switchToMainPage();
    }

}
