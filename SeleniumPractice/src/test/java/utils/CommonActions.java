package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CommonActions {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private final ExtentTest test;

    public CommonActions(WebDriver driver, WebDriverWait wait, ExtentTest test) {
        this.driver = driver;
        this.wait = wait;
        this.test = test;
    }

    public void click(By locator) {
        test.log(Status.INFO, "Attempting to click on element: " + locator.toString());
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        test.log(Status.INFO, "Clicked on element: " + locator);
    }

    public void sendText(By locator, String text) {
        test.log(Status.INFO, "Attempting to send text '" + text + "' to element: " + locator.toString());
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        test.log(Status.INFO, "Sent text '" + text + "' to element: " + locator);
    }

    public String getText(By locator) {
        test.log(Status.INFO, "Attempting to get text from element: " + locator.toString());
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        test.log(Status.INFO, "Retrieved text '" + text + "' from element: " + locator);
        return text;
    }

    public void selectByVisibleText(By locator, String text) {
        test.log(Status.INFO, "Attempting to select by visible text '" + text + "' from dropdown: " + locator.toString());
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByVisibleText(text);
        test.log(Status.INFO, "Selected by visible text '" + text + "' from dropdown: " + locator);
    }

    public void selectByIndex(By locator, int index) {
        test.log(Status.INFO, "Attempting to select by index '" + index + "' from dropdown: " + locator.toString());
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByIndex(index);
        test.log(Status.INFO, "Selected by index '" + index + "' from dropdown: " + locator);
    }

    public void selectByValue(By locator, String value) {
        test.log(Status.INFO, "Attempting to select by value '" + value + "' from dropdown: " + locator.toString());
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByValue(value);
        test.log(Status.INFO, "Selected by value '" + value + "' from dropdown: " + locator);
    }

    public String getFirstSelectedOption(By locator) {
        test.log(Status.INFO, "Attempting to get first selected option from dropdown: " + locator.toString());
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        String selectedOption = select.getFirstSelectedOption().getText();
        test.log(Status.INFO, "Retrieved first selected option '" + selectedOption + "' from dropdown: " + locator);
        return selectedOption;
    }

    public void assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            test.log(Status.PASS, message + " - Expected: [" + expected + "] Actual: [" + actual + "]");
        } catch (AssertionError e) {
            test.log(Status.FAIL, message + " - Expected: [" + expected + "] Actual: [" + actual + "] - Error: " + e.getMessage());
            throw e;
        }
    }
}
