package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.ECommercePage;
import utils.ConfigReader;
import utils.TestBase;

import java.util.List;
import java.util.Optional;

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
    public void topDealsSortTest() {
        commonActions().goTo("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        commonActions().selectByValue(By.id("page-menu"), "20");
        commonActions().click(By.xpath("//span[text()='Veg/fruit name']"));
        List<WebElement> elementList = commonActions().findElements(By.xpath("//tbody/tr/td[1]"));
        List<String> originalList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortedList = originalList.stream().sorted().toList();
        commonActions().assertEquals(originalList, sortedList, "Verify that vegetables are sorted alphabetically in the UI");
    }

    @Test
    public void getPriceTest() {
        commonActions().goTo("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        commonActions().selectByValue(By.id("page-menu"), "5");
        String productName = "Wheat";
        Optional<WebElement> productRow;
        do {
            productRow = commonActions().findElements(By.xpath("//tbody/tr/td[1]")).stream().filter(nameElement -> nameElement.getText().equalsIgnoreCase(productName)).findFirst();
        }
        while (!productRow.isPresent()) {
            commonActions().click(By.xpath("//a[@aria-label='Next']"));
            productRow = commonActions().findElements(By.xpath("//tbody/tr/td[1]")).stream().filter(nameElement -> nameElement.getText().equalsIgnoreCase(productName)).findFirst();
        }
        commonActions().assertEquals(productRow.isPresent(), true, "Verify product '" + productName + "' is present on the page.");

        String price;
        if (productRow.isPresent()) {
            price = productRow.get().findElement(By.xpath("./following-sibling::td[1]")).getText();
            commonActions().assertEquals(!price.isEmpty(), true, "Verify price for '" + productName + "' is not empty.");
            System.out.println("Price of " + productName + ": " + price);
        }
    }
}
