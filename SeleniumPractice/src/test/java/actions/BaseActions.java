package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BaseActions implements Actions {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Override
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    @Override
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Override
    public void sendText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    @Override
    public void selectByVisibleText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    @Override
    public void selectByIndex(By locator, int index) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    @Override
    public void selectByValue(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByValue(value);
    }

    @Override
    public String getFirstSelectedOption(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    @Override
    public void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Override
    public List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    @Override
    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    @Override
    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    @Override
    public String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    @Override
    public void sendTextToAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent()).sendKeys(text);
    }
}
