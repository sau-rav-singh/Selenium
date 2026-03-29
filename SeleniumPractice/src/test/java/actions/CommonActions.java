package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;

import java.util.List;

public class CommonActions implements Actions {

    private final Actions actions;

    public CommonActions(WebDriver driver, WebDriverWait wait, ExtentTest test) {
        Actions baseActions = new BaseActions(driver, wait);
        Actions highlightingActions = new HighlightDecorator(baseActions, driver);
        this.actions = new ReportingDecorator(highlightingActions, test, driver);
    }

    @Override
    public void click(By locator) {
        actions.click(locator);
    }

    @Override
    public void click(WebElement element) {
        actions.click(element);
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

    @Override
    public List<WebElement> findElements(By locator) {
        return actions.findElements(locator);
    }

    @Override
    public WebElement findElement(By locator) {
        return actions.findElement(locator);
    }

    @Override
    public void acceptAlert() {
        actions.acceptAlert();
    }

    @Override
    public void dismissAlert() {
        actions.dismissAlert();
    }

    @Override
    public String getAlertText() {
        return actions.getAlertText();
    }

    @Override
    public void sendTextToAlert(String text) {
        actions.sendTextToAlert(text);
    }
}
