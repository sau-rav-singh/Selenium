package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import utils.TestBase;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class NewTabTest extends TestBase {

    @Test
    public void multiTabTest() {
        commonActions().goTo("https://rahulshettyacademy.com/angularpractice/");
        String primaryTab = getDriver().getWindowHandle();
        getDriver().switchTo().newWindow(WindowType.TAB);
        commonActions().goTo("https://rahulshettyacademy.com/");
        String text = commonActions().getText(By.cssSelector(".text-xl"));
        getDriver().switchTo().window(primaryTab);
        By input = By.xpath("//input");
        By email = By.xpath("//label[text()='Email']");
        WebElement nameInput = commonActions().findElement(with(input).above(email));
        nameInput.sendKeys(text);
        commonActions().assertEquals(nameInput.getAttribute("value"), text, "Name input should match the expected name");
    }
}
