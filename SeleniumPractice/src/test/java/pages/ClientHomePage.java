package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CommonActions;

import java.util.List;
import java.util.Optional;

public class ClientHomePage extends BaseClientPage {
    private static final Logger logger = LoggerFactory.getLogger(ClientHomePage.class);
    public static final String SECTION_ID_PRODUCTS_DIV_DIV_2_DIV = "//section[@id='products']/div/div[2]/div";
    public static final String ADD_TO_CART = ".//parent::div/button[2]";

    public ClientHomePage(CommonActions commonActions) {
        super(commonActions);
    }

    public void addProductToCart(String productName) {
        List<WebElement> products = commonActions.findElements(By.xpath(SECTION_ID_PRODUCTS_DIV_DIV_2_DIV));
        Optional<WebElement> product = products.stream()
                .filter(ele -> ele.findElement(By.xpath(".//b")).getText().equalsIgnoreCase(productName))
                .findFirst();

        if (product.isPresent()) {
            product.get().findElement(By.xpath(ADD_TO_CART)).click();
            logger.info("Added product to cart: {}", productName);
        } else {
            System.out.println("Product '" + productName + "' not found.");
            logger.warn("Product '{}' not found on the page", productName);
        }
        commonActions.waitForVisibility(By.cssSelector("#toast-container"));
        commonActions.waitForInVisibility(By.cssSelector(".ng-animating"));
    }
}
