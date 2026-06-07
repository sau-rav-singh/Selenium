package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonActions;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ECommercePage {
    private static final Logger logger = LoggerFactory.getLogger(ECommercePage.class);
    private final CommonActions commonActions;

    private final By productNameLocator = By.xpath("//h4[@class='product-name']");
    private final By addToCartButtonLocator = By.xpath("./parent::div//button");

    public ECommercePage(CommonActions commonActions) {
        this.commonActions = commonActions;
    }

    public void addProductToCart(String productName) {
        String productToSearch = productName.toLowerCase();
        List<WebElement> allProductsName = commonActions.findElements(productNameLocator);
        for (WebElement product : allProductsName) {
            if (commonActions.getText(product).contains(productToSearch)) {
                product.findElement(addToCartButtonLocator).click();
                commonActions.waitForVisibility(By.cssSelector("#toast-container"));
                commonActions.waitForInVisibility(By.cssSelector(".ng-animating"));
                logger.info("Added product to cart: {}", productName);
                break;
            }
        }
    }

    public void addProductToCart(String[] productArray) {
        List<WebElement> allProductsName = commonActions.findElements(productNameLocator);
        logger.debug("productArray is {}", Arrays.toString(productArray));
        for (WebElement product : allProductsName) {
            String productName = commonActions.getText(product).split(" ")[0];
            logger.debug("productName is {}", productName);
            if (Arrays.asList(productArray).contains(productName)) {
                logger.debug("In if loop for {}", productName);
                product.findElement(addToCartButtonLocator).click();
                commonActions.waitForVisibility(By.cssSelector("#toast-container"));
                commonActions.waitForInVisibility(By.cssSelector(".ng-animating"));
            }else{
                logger.trace("{} not in the expected product list", productName);
            }
        }
    }

}
