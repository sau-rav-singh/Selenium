package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;

/**
 * CommonActions now acts as a facade that composes the decorated actions.
 * This maintains backward compatibility with existing tests.
 */
public class CommonActions implements Actions {

    private final Actions actions;

    public CommonActions(WebDriver driver, WebDriverWait wait, ExtentTest test) {
        // Compose the behavior using Decorator pattern
        Actions baseActions = new BaseActions(driver, wait);
        this.actions = new ReportingDecorator(baseActions, test, driver);
    }

    @Override
    public void click(By locator) {
        actions.click(locator);
    }

    @Override
    public void sendText(By locator, String text) {
        actions.sendText(locator, text);
    }

    @Override
    public String getText(By locator) {
        return actions.getText(locator);
    }

    @Override
    public void selectByVisibleText(By locator, String text) {
        actions.selectByVisibleText(locator, text);
    }

    @Override
    public void selectByIndex(By locator, int index) {
        actions.selectByIndex(locator, index);
    }

    @Override
    public void selectByValue(By locator, String value) {
        actions.selectByValue(locator, value);
    }

    @Override
    public String getFirstSelectedOption(By locator) {
        return actions.getFirstSelectedOption(locator);
    }

    @Override
    public void assertEquals(Object actual, Object expected, String message) {
        actions.assertEquals(actual, expected, message);
    }
}
