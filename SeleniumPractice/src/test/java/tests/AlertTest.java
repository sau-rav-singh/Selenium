package tests;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTest extends TestBase {

    @Test
    public void triggerAlertTest() {
        getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        String text = "saurav";
        actions().sendText(By.id("name"), text);
        actions().click(By.id("alertbtn"));
        String alertText = actions().getAlertText();
        Assert.assertTrue(alertText.contains(text));
        actions().acceptAlert();
    }
}
