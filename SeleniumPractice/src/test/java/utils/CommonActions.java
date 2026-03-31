package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

public class CommonActions {
    private static final Logger logger = LoggerFactory.getLogger(CommonActions.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CommonActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void sendText(By locator, String text) {
        logger.info("Sending text '{}' to locator {}", text, locator);
        WebElement element = waitForClickable(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        logger.info("Clicking on locator {}", locator);
        waitForClickable(locator).click();
    }

    public void click(WebElement element) {
        logger.info("Clicking on web element");
        element.click();
    }

    public String getAlertText() {
        logger.info("Getting alert text");
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        logger.info("Accepting alert");
        driver.switchTo().alert().accept();
    }

    public String getText(By locator) {
        logger.info("Getting text from locator {}", locator);
        return waitForVisibility(locator).getText();
    }

    public void selectByVisibleText(By locator, String text) {
        logger.info("Selecting visible text '{}' from locator {}", text, locator);
        Select select = new Select(waitForVisibility(locator));
        select.selectByVisibleText(text);
    }

    public void selectByIndex(By locator, int index) {
        logger.info("Selecting index '{}' from locator {}", index, locator);
        Select select = new Select(waitForVisibility(locator));
        select.selectByIndex(index);
    }

    public void selectByValue(By locator, String value) {
        logger.info("Selecting value '{}' from locator {}", value, locator);
        Select select = new Select(waitForVisibility(locator));
        select.selectByValue(value);
    }

    public String getFirstSelectedOption(By locator) {
        logger.info("Getting first selected option from locator {}", locator);
        Select select = new Select(waitForVisibility(locator));
        return select.getFirstSelectedOption().getText();
    }

    public void assertEquals(Object actual, Object expected, String message) {
        logger.info("Comparing actual '{}' with expected '{}' - {}", actual, expected, message);
        Assert.assertEquals(actual, expected, message);
    }

    public List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Helper methods for robust waiting
    private WebElement waitForClickable(By locator) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitForVisibility(By locator) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
