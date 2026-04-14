package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import utils.TestBase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FluentwaitTest extends TestBase {

    @Test
    public void fluentWaitTest() {
        commonActions().goTo("https://the-internet.herokuapp.com/dynamic_loading/1");
        commonActions().click(By.cssSelector("#start button"));

        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .withMessage("Timeout: Element not found after 30s");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
        System.out.println(element.getText());
    }

    @Test
    public void closeQuitTest() {
        commonActions().goTo("https://bing.com");
        ((JavascriptExecutor) getDriver()).executeScript("window.open('https://google.com')");
        Set<String> windowHandles = getDriver().getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);
        getDriver().switchTo().window(windowHandlesList.get(1));
        System.out.println("Page Title of get1 is " + getDriver().getTitle());
        getDriver().close();
        getDriver().switchTo().window(windowHandlesList.getFirst());
        System.out.println("Page Title of get0 is " + getDriver().getTitle());
    }
}
