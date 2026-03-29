package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Actions {
    void click(By locator);

    void click(WebElement element);

    void sendText(By locator, String text);

    String getText(By locator);

    void selectByVisibleText(By locator, String text);

    void selectByIndex(By locator, int index);

    void selectByValue(By locator, String value);

    String getFirstSelectedOption(By locator);

    void assertEquals(Object actual, Object expected, String message);

    List<WebElement> findElements(By locator);

    WebElement findElement(By locator);

    void acceptAlert();

    void dismissAlert();

    String getAlertText();

    void sendTextToAlert(String text);
}
