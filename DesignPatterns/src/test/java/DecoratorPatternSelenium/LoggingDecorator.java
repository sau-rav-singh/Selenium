package DecoratorPatternSelenium;

import org.openqa.selenium.WebElement;

public class LoggingDecorator extends WebElementDecoratorBase {
    public LoggingDecorator(WebElementDecorator decoratedElement) {
        super(decoratedElement);
    }

    @Override
    public void click(WebElement element) {
        System.out.println("Clicking of Element: " + element);
        super.click(element);
    }

    @Override
    public void sendKeys(WebElement element, String text) {
        System.out.println("Entering " + text + "on: " + element);
        super.sendKeys(element, text);
    }

}
