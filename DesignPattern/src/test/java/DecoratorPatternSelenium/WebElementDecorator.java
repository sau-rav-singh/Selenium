package DecoratorPatternSelenium;

import org.openqa.selenium.WebElement;

public interface WebElementDecorator {
    void click(WebElement element);
    void sendKeys(WebElement element,String text);
}
