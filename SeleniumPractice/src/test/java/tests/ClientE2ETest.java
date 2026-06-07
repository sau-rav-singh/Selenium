package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ClientHomePage;
import pages.LoginPage;
import utils.DriverManager;
import utils.TestBase;

import java.util.List;

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
        commonActions().click(By.cssSelector(".totalRow button"));
        commonActions().sendText(By.xpath("//input[@placeholder='Select Country']"), "india");
        List<WebElement> countryList = commonActions().findElements(By.xpath("//i[@class='fa fa-search']/parent::span"));
        countryList.stream().filter(i -> i.getText().equalsIgnoreCase("india")).findFirst().ifPresentOrElse(WebElement::click, () -> {
            throw new NoSuchElementException("Country 'India' not found in the list.");
        });
        commonActions().click(By.cssSelector(".action__submit"));
    }


}