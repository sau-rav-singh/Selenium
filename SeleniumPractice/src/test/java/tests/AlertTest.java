package tests;

import utils.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTest extends TestBase {

    @Test
    public void triggerAlertTest() {
        getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        String text = "saurav";
        commonActions().sendText(By.id("name"), text);
        getUnDecoratedDriver().findElement(By.id("alertbtn")).click();
        String alertText = commonActions().getAlertText();
        System.out.println("Text is " + alertText);
        Assert.assertTrue(alertText.contains(text));
        commonActions().acceptAlert();
    }
}
