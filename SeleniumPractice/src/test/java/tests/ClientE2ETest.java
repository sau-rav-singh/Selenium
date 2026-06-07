package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.CommonUtils;
import utils.TestBase;

public class ClientE2ETest extends TestBase {
    @Test
    public void placeOrder() {
        commonActions().goTo("https://rahulshettyacademy.com/client");
        commonActions().sendText(By.id("userEmail"), "selena@gomez.com");
        commonActions().sendText(By.id("userPassword"), "Iamking@000");
        commonActions().click(By.id("login"));
        CommonUtils.addProductToCart("iphone 13 pro");
        CommonUtils.addProductToCart("ADIDAS ORIGINAL");
        commonActions().click(By.xpath("//button[@routerlink='/dashboard/cart']"));
    }


}