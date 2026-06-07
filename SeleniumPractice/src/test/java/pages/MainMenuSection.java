package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CommonActions;

public class MainMenuSection {
    private static final Logger logger = LoggerFactory.getLogger(MainMenuSection.class);
    protected final CommonActions commonActions;

    public MainMenuSection(CommonActions commonActions) {
        this.commonActions = commonActions;
    }

    private final By homePageLink = By.xpath("//button[@routerlink='/dashboard/']");
    private final By ordersLink = By.xpath("//button[@routerlink='/dashboard/myorders']");
    private final By cartLink = By.xpath("//button[@routerlink='/dashboard/cart']");

    public void goToHomePage() {
        logger.info("Navigating to home page");
        commonActions.click(homePageLink);
    }

    public void goToOrders() {
        logger.info("Navigating to orders");
        commonActions.click(ordersLink);
    }

    public void goToCart() {
        logger.info("Navigating to cart");
        commonActions.click(cartLink);
    }
}
