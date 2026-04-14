package tests;

import utils.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AlertTest extends TestBase {

    @Test
    public void triggerAlertTest() {
        commonActions().goTo("https://rahulshettyacademy.com/AutomationPractice/");
        String text = "saurav";
        commonActions().sendText(By.id("name"), text);
        getUnDecoratedDriver().findElement(By.id("alertbtn")).click();
        String alertText = commonActions().getAlertText();
        commonActions().assertEquals(alertText.contains(text), true, "Verify alert contains name: " + text);
        commonActions().acceptAlert();
    }
}
