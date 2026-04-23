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
        boolean hasNext;

        do {
            Optional<WebElement> productRow = commonActions().findElements(By.xpath("//tbody/tr/td[1]")).stream().filter(nameElement -> nameElement.getText().equalsIgnoreCase(productName)).findFirst();

            if (productRow.isPresent()) {
                price = productRow.get().findElement(By.xpath("./following-sibling::td[1]")).getText();
                hasNext = false;
            } else {
                WebElement nextButton = commonActions().findElement(By.xpath("//a[@aria-label='Next']"));
                hasNext = !"true".equals(nextButton.getAttribute("aria-disabled"));

                if (hasNext) {
                    commonActions().click(nextButton);
                }
            }
        } while (hasNext);

        commonActions().assertEquals(!price.isEmpty(), true, "Verify product '" + productName + "' was found and price retrieved.");
        if (!price.isEmpty()) {
            System.out.println("Price of " + productName + ": " + price);
        }
    }
}
