package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ECommercePage;
import utils.ConfigReader;
import utils.TestBase;

import java.util.List;

public class ECommerceTest extends TestBase {

    @Test
    public void placeOrder() {
        String baseUrl = ConfigReader.getBaseUrl();
        if (baseUrl != null && !baseUrl.isEmpty()) {
            getDriver().get(baseUrl);
        }
        ECommercePage eCommercePage = new ECommercePage(commonActions());
        String productPurchased = "Beans";
        String[] productArray = new String[]{"tomato", "cucumber"};
        eCommercePage.addProductToCart(productPurchased);
        eCommercePage.addProductToCart(productArray);
    }

    @Test
    public void topDealsSortTest(){
        commonActions().goTo("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        Select pageSizeSelect=new Select(commonActions().findElement(By.id("page-menu")));
        pageSizeSelect.selectByValue("10");
        commonActions().click(By.className("sort-icon"));
        List<String> sortedProductName= commonActions().findElements(By.xpath("//tbody/tr/td[1]")).stream().map(WebElement::getText).toList();
        Assert.assertEquals(sortedProductName,sortedProductName.stream().sorted().toList(),"Products should be sorted");
    }
}
