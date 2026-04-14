package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import utils.TestBase;

public class ActionsTest extends TestBase {

    @Test
    public void amazonTest() {
        commonActions().goTo("https://www.amazon.in/");
        commonActions().moveToElement(By.id("nav-link-accountList"));
        commonActions().assertEquals(
                commonActions().findElement(By.xpath("//span[text()='Your Seller Account']")).isDisplayed(),
                true,
                "Verify 'Your Seller Account' is visible on hover"
        );

        commonActions().getActionsInstance()
                .moveToElement(commonActions().findElement(By.id("twotabsearchtextbox")))
                .click()
                .keyDown(Keys.SHIFT)
                .sendKeys("hello")
                .keyUp(Keys.SHIFT)
                .sendKeys(" world")
                .perform();

        commonActions().assertEquals(
                commonActions().findElement(By.id("twotabsearchtextbox")).getAttribute("value"),
                "HELLO world",
                "Verify text formatting in search bar"
        );

    }
}
