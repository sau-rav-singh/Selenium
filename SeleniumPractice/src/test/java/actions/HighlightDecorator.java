package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
            highlight(element);
        } catch (Exception e) {
            // Ignore highlighting errors
        }
    }

    private void highlight(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
            Thread.sleep(100); 
            js.executeScript("arguments[0].setAttribute('style', '');", element);
        } catch (Exception e) {
            // Ignore highlighting errors
        }
    }

    @Override
    public void click(By locator) {
        highlight(locator);
        decorated.click(locator);
    }

    @Override
    public void click(WebElement element) {
        highlight(element);
        decorated.click(element);
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
        decorated.selectByIndex(locator,index);
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
        decorated.assertEquals(actual, expected, message);
    }

    @Override
    public List<WebElement> findElements(By locator) {
        highlight(locator);
        return decorated.findElements(locator);
    }

    @Override
    public WebElement findElement(By locator) {
        highlight(locator);
        return decorated.findElement(locator);
    }
}
