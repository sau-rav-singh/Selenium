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
        String productName = "Cherry";
        String price = "";
        boolean productFound = false;
        List<WebElement> products;

        while (!productFound) {
            products = commonActions().findElements(By.xpath("//tbody/tr/td[1]"));
            Optional<String> extractedPrice = products.stream().filter(product -> product.getText().equalsIgnoreCase(productName)).findFirst().map(product -> product.findElement(By.xpath("./following-sibling::td[1]")).getText());
            if (extractedPrice.isEmpty()) {
                commonActions().click(By.xpath("//a[@aria-label='Next']"));
            } else {
                price = extractedPrice.get();
                productFound = true;
            }
        }
        System.out.println("Price of " + productName + ": " + price);
        commonActions().assertEquals(price, "93", "Price if Cherry should be 93");
    }

    @Test
    public void filterTest(){
        commonActions().goTo("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        String searchText = "ch";
        commonActions().sendText(By.id("search-field"), searchText);
        List<WebElement> products=commonActions().findElements(By.xpath("//tbody/tr/td[1]"));
        List<WebElement> productsNameList=products.stream().filter(product->product.getText().toLowerCase().startsWith(searchText)).toList();
        commonActions().assertEquals(products.size(),productsNameList.size(),"products and productsNameList should have same elements");
    }
}
