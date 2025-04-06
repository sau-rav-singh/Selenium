package stepDefinitions;

import TestUtils.TestContextSetup;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.CheckoutPage;

public class CheckoutPageStepDefinition {
    public CheckoutPage checkoutPage;
    TestContextSetup testContextSetup;

    public CheckoutPageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
    }

    @Then("verify user has ability to enter promo code and place the order")
    public void verify_user_has_ability_enter_promo() {
        Assert.assertTrue(checkoutPage.VerifyPromoBtn());
        Assert.assertTrue(checkoutPage.VerifyPlaceOrder());
    }

    @Then("^User proceeds to Checkout and validate the (.+) items in checkout page$")
    public void user_proceeds_to_checkout(String name) {
        checkoutPage.CheckoutItems();
    }
}
