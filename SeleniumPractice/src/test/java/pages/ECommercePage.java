package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonActions;

import java.util.List;

public class ECommercePage {
    private final CommonActions commonActions;

    private final By productNameLocator = By.xpath("//h4[@class='product-name']");
    private final By addToCartButtonLocator = By.xpath("./parent::div //button");

    public ECommercePage(CommonActions commonActions) {
        this.commonActions = commonActions;
    }

    public void addProductToCart(String productName) {
        String productToSearch = productName.toLowerCase();
        List<WebElement> allProductsName = commonActions.findElements(productNameLocator);
        for (WebElement product : allProductsName) {
            if (commonActions.getText(product).contains(productToSearch)) {
                product.findElement(addToCartButtonLocator).click();
                break;
            }
        }
    }
}
