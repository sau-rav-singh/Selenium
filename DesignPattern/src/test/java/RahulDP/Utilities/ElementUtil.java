package RahulDP.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementUtil {

    public static WebElement findElement(WebElement sectionElement, By locator) {
        return sectionElement.findElement(locator);
    }

    public static List<WebElement> findElements(WebElement sectionElement, By locator) {
        return sectionElement.findElements(locator);
    }
}