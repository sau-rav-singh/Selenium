package tests;

import org.testng.annotations.Test;
import pages.ECommercePage;
import utils.TestBase;

public class ECommerceTest extends TestBase {

    @Test
    public void placeOrder() {
        ECommercePage eCommercePage = new ECommercePage(commonActions());
        String productPurchased = "Beans";
        String[] productArray = new String[]{"tomato", "cucumber"};
        eCommercePage.addProductToCart(productPurchased);
        eCommercePage.addProductToCart(productArray);
    }
}
