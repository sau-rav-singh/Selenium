package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.TestBase;

import java.util.ArrayList;
import java.util.List;

public class WindowsTest extends TestBase {

    @Test
    public void switchWindowTest() {
        commonActions().goTo("https://rahulshettyacademy.com/loginpagePractise/");
        String parentWindow = getDriver().getWindowHandle();
        commonActions().click(By.className("blinkingText"));
        List<String> windowHandles = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windowHandles.get(1));
        String redText = commonActions().getText(By.cssSelector(".im-para.red"));
        String email = redText.split("at")[1].trim().split(" ")[0];
        getDriver().close();
        getDriver().switchTo().window(parentWindow);
        commonActions().sendText(By.id("username"), email);
        commonActions().assertEquals(
                commonActions().findElement(By.id("username")).getAttribute("value"), 
                email, 
                "Verify email/username is correctly populated in the field"
        );
    }
}
