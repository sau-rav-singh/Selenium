package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonActions;

import java.util.List;
import java.util.Optional;

public class ClientHomePage {
    private final CommonActions commonActions;

    public ClientHomePage(CommonActions commonActions) {
        this.commonActions = commonActions;
    }

    public void addProductToCart(String productName) {
        List<WebElement> products = commonActions.findElements(By.xpath("//section[@id='products']/div/div[2]/div"));
        Optional<WebElement> product = products.stream()
                .filter(ele -> ele.findElement(By.xpath(".//b")).getText().equalsIgnoreCase(productName))
                .findFirst();

        if (product.isPresent()) {
            product.get().findElement(By.xpath(".//parent::div/button[2]")).click();
        } else {
            System.out.println("Product '" + productName + "' not found.");
        }
        commonActions.waitForVisibility(By.cssSelector("#toast-container"));
        commonActions.waitForInVisibility(By.cssSelector(".ng-animating"));
    }
}
