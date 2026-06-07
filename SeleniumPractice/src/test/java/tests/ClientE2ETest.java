package tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ClientHomePage;
import utils.DriverManager;
import utils.TestBase;

public class ClientE2ETest extends TestBase {
    private ClientHomePage clientHomePage;

    @BeforeMethod
    public void setUpPage() {
        clientHomePage = new ClientHomePage(DriverManager.getCommonActions());
    }

    @Test
    public void placeOrder() {
        commonActions().goTo("https://rahulshettyacademy.com/client");
        commonActions().sendText(By.id("userEmail"), "selena@gomez.com");
        commonActions().sendText(By.id("userPassword"), "Iamking@000");
        commonActions().click(By.id("login"));
        clientHomePage.addProductToCart("iphone 13 pro");
        clientHomePage.addProductToCart("ADIDAS ORIGINAL");
        commonActions().click(By.xpath("//button[@routerlink='/dashboard/cart']"));
    }


}