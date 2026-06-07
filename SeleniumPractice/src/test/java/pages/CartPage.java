package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.CommonActions;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);
    private final CommonActions commonActions;

    private final By productNamesLocator = By.cssSelector("div.cartSection h3");

    public CartPage(CommonActions commonActions) {
        this.commonActions = commonActions;
    }

    public List<String> getProductsInCart() {
        List<WebElement> productElements = commonActions.findElements(productNamesLocator);
        return productElements.stream()
                .map(element -> commonActions.getText(element).trim())
                .collect(Collectors.toList());
    }

    public void verifyProductsInCart(String... expectedProducts) {
        List<String> actualProducts = getProductsInCart();

        logger.info("Expected products in cart: {}", (Object[]) expectedProducts);
        logger.info("Actual products in cart: {}", actualProducts);

        if (actualProducts.isEmpty()) {
            Assert.fail("Cart is empty. No products found.");
        }

        for (String expectedProduct : expectedProducts) {
            String expectedLower = expectedProduct.toLowerCase().trim();
            boolean productFound = actualProducts.stream()
                    .anyMatch(actual -> actual.toLowerCase().contains(expectedLower));

            commonActions.assertEquals(productFound, true, "Product '" + expectedProduct + "' not found in cart. Actual products: " + actualProducts);
            logger.info("✓ Product '{}' found in cart", expectedProduct);
        }
    }

    public void verifyProductCount(int expectedCount) {
        List<String> products = getProductsInCart();
        Assert.assertEquals(products.size(), expectedCount,
                "Product count mismatch. Expected: " + expectedCount + ", Actual: " + products.size());
        logger.info("✓ Cart contains {} product(s) as expected", expectedCount);
    }
}

