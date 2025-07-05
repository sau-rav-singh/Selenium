package DecoratorPatternSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDecorator extends WebElementDecoratorBase {
    private final WebDriver driver;

    public WaitDecorator(WebElementDecorator decoratedElement, WebDriver driver) {
        super(decoratedElement);
        this.driver = driver;
    }

    @Override
    public void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Clicking of Element after waiting: " + element);
        super.click(element);
    }

    @Override
    public void sendKeys(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Entering " + text + "on: " + element + " after waiting");
        super.sendKeys(element, text);
    }

}
