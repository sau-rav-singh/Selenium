package utils;

import org.openqa.selenium.By;

public interface Actions {
    void click(By locator);
    void sendText(By locator, String text);
    String getText(By locator);
    void selectByVisibleText(By locator, String text);
    void selectByIndex(By locator, int index);
    void selectByValue(By locator, String value);
    String getFirstSelectedOption(By locator);
    void assertEquals(Object actual, Object expected, String message);
}
