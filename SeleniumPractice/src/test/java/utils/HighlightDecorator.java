package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightDecorator implements Actions {
    private final Actions decorated;
    private final WebDriver driver;

    public HighlightDecorator(Actions decorated, WebDriver driver) {
        this.decorated = decorated;
        this.driver = driver;
    }

    private void highlight(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Apply a yellow background and red border
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
            Thread.sleep(200); // Short pause to make the highlight visible
            // Reset the style (optional, or leave it to see what was touched)
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid white;');", element);
        } catch (Exception e) {
            // Ignore highlighting errors to not break the actual test
        }
    }

    @Override
    public void click(By locator) {
        highlight(locator);
        decorated.click(locator);
    }

    @Override
    public void sendText(By locator, String text) {
        highlight(locator);
        decorated.sendText(locator, text);
    }

    @Override
    public String getText(By locator) {
        highlight(locator);
        return decorated.getText(locator);
    }

    @Override
    public void selectByVisibleText(By locator, String text) {
        highlight(locator);
        decorated.selectByVisibleText(locator, text);
    }

    @Override
    public void selectByIndex(By locator, int index) {
        highlight(locator);
        decorated.selectByIndex(locator, index);
    }

    @Override
    public void selectByValue(By locator, String value) {
        highlight(locator);
        decorated.selectByValue(locator, value);
    }

    @Override
    public String getFirstSelectedOption(By locator) {
        highlight(locator);
        return decorated.getFirstSelectedOption(locator);
    }

    @Override
    public void assertEquals(Object actual, Object expected, String message) {
        // Assertions don't usually interact with elements directly via By locator here
        decorated.assertEquals(actual, expected, message);
    }
}
