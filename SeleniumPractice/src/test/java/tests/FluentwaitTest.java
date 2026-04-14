package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import utils.TestBase;
import java.time.Duration;

public class FluentwaitTest extends TestBase {

    @Test
    public void fluentWaitTest() {
        commonActions().goTo("https://the-internet.herokuapp.com/dynamic_loading/1");
        commonActions().click(By.cssSelector("#start button"));

        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .withMessage("Timeout: Finish element not found after 30s");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));

        commonActions().assertEquals(
                element.getText(),
                "Hello World!",
                "Verify text after dynamic loading"
        );
    }
}
