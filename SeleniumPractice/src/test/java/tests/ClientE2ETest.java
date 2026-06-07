package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ClientHomePage;
import pages.LoginPage;
import utils.CommonActions;
import utils.DriverManager;
import utils.TestBase;

public class ClientE2ETest extends TestBase {
    public static final String IPHONE_13_PRO = "IPHONE 13 PRO";
    public static final String ADIDAS_ORIGINAL = "ADIDAS ORIGINAL";
    private ClientHomePage clientHomePage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUpPage() {
        CommonActions actions = DriverManager.getCommonActions();
        actions.goTo("https://rahulshettyacademy.com/client");
        LoginPage loginPage = new LoginPage(actions);
        clientHomePage = new ClientHomePage(actions);
        checkoutPage = new CheckoutPage(actions);
        loginPage.loginWithDefaultCredentials();
    }

    @Test
    public void placeOrder() {
        // Add products to cart
        clientHomePage.addProductToCart(IPHONE_13_PRO);
        clientHomePage.addProductToCart(ADIDAS_ORIGINAL);

        // Navigate to cart and verify products
        CartPage cartPage = clientHomePage.mainMenuSection().goToCart();
        cartPage.verifyProductsInCart(IPHONE_13_PRO, ADIDAS_ORIGINAL);
        cartPage.verifyProductCount(2);

        // Proceed to checkout
        cartPage.clickCheckoutButton();
        checkoutPage.selectCountry("india");
        checkoutPage.placeOrder();

        // Verify order confirmation
        String orderConfirmation = checkoutPage.getOrderConfirmationMessage();
        commonActions().assertEquals(orderConfirmation, "thankyou for the order.", "Order confirmation message");

        // Navigate back to home
        clientHomePage.mainMenuSection().goToHomePage();
    }

}