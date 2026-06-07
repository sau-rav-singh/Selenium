package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CommonActions;

import java.util.List;

public class CheckoutPage extends BaseClientPage {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutPage.class);

    private final By countryInputLocator = By.xpath("//input[@placeholder='Select Country']");
    private final By countryDropdownLocator = By.xpath("//i[@class='fa fa-search']/parent::span");
    private final By placeOrderButtonLocator = By.cssSelector(".action__submit");
    private final By orderConfirmationLocator = By.cssSelector(".hero-primary");

    public CheckoutPage(CommonActions commonActions) {
        super(commonActions);
    }

    /**
     * Select country from dropdown.
     */
    public void selectCountry(String countryName) {
        logger.info("Selecting country: {}", countryName);
        commonActions.sendText(countryInputLocator, countryName);
        List<WebElement> countryList = commonActions.findElements(countryDropdownLocator);
        countryList.stream()
                .filter(i -> i.getText().equalsIgnoreCase(countryName))
                .findFirst()
                .ifPresentOrElse(
                        i -> {
                            commonActions.click(i);
                            logger.info("Country {} selected", countryName);
                        },
                        () -> {
                            throw new NoSuchElementException("Country '" + countryName + "' not found in the list.");
                        }
                );
    }

    /**
     * Complete checkout and place order.
     */
    public void placeOrder() {
        logger.info("Placing order");
        commonActions.click(placeOrderButtonLocator);
    }

    /**
     * Get order confirmation message.
     */
    public String getOrderConfirmationMessage() {
        String message = commonActions.getText(orderConfirmationLocator);
        logger.info("Order confirmation message: {}", message);
        return message;
    }
}

