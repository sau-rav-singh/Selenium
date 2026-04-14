package tests;

import org.testng.annotations.Test;
import pages.ECommercePage;
import utils.ConfigReader;
import utils.TestBase;

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
}
