package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ClientHomePage;
import pages.LoginPage;
import utils.DriverManager;
import utils.TestBase;

public class ClientE2ETest extends TestBase {
    public static final String IPHONE_13_PRO = "IPHONE 13 PRO";
    public static final String ADIDAS_ORIGINAL = "ADIDAS ORIGINAL";
    private ClientHomePage clientHomePage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        commonActions().goTo("https://rahulshettyacademy.com/client");
        LoginPage loginPage = new LoginPage(DriverManager.getCommonActions());
        clientHomePage = new ClientHomePage(DriverManager.getCommonActions());
        cartPage = new CartPage(DriverManager.getCommonActions());
        loginPage.loginWithDefaultCredentials();
    }

    @Test
    public void placeOrder() {
        clientHomePage.addProductToCart(IPHONE_13_PRO);
        clientHomePage.addProductToCart(ADIDAS_ORIGINAL);
        commonActions().click(By.xpath("//button[@routerlink='/dashboard/cart']"));
        cartPage.verifyProductsInCart(IPHONE_13_PRO, ADIDAS_ORIGINAL);
        cartPage.verifyProductCount(2);
    }


}