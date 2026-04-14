package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.TestBase;

public class LocatorTest extends TestBase {

    @Test
    public void locatorExamples() {
        commonActions().goTo("https://rahulshettyacademy.com/locatorspractice/");

        commonActions().sendText(By.id("inputUsername"), "rahul");
        commonActions().sendText(By.name("inputPassword"), "hello123");
        commonActions().click(By.className("signInBtn"));

        commonActions().getText(By.cssSelector("p.error"));

        commonActions().click(By.linkText("Forgot your password?"));

        commonActions().sendText(By.xpath("//input[@placeholder='Name']"), "John");
        commonActions().sendText(By.cssSelector("input[placeholder='Email']"), "john@rsa.com");
        
        commonActions().sendText(By.cssSelector("input[type='text']:nth-of-type(2)"), "john@gmail.com");
        commonActions().sendText(By.xpath("//form/input[3]"), "9864353253");
        commonActions().click(By.cssSelector(".reset-pwd-btn"));

        commonActions().getText(By.cssSelector("form p"));

        commonActions().click(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]"));

        commonActions().sendText(By.cssSelector("#inputUsername"), "rahul");
        commonActions().sendText(By.cssSelector("input[type*='pass']"), "rahulshettyacademy");

        commonActions().click(By.id("chkboxOne"));
        commonActions().click(By.xpath("//button[contains(@class,'submit')]"));
    }

    @Test
    public void locatorExamples2() {
        String name = "rahul";
        commonActions().goTo("https://rahulshettyacademy.com/locatorspractice/");
        String password = getPasswordFromUI();

        commonActions().sendText(By.id("inputUsername"), name);
        commonActions().sendText(By.name("inputPassword"), password);
        commonActions().click(By.className("signInBtn"));

        commonActions().assertEquals(commonActions().getText(By.tagName("p")), "you are successfully logged in.", "Verify login success message");
        String userName = commonActions().getText(By.cssSelector("div[class='login-container'] h2"));
        commonActions().assertEquals(userName, "hello " + name + ",", "Verify username in welcome message");

        commonActions().click(By.xpath("//*[text()='Log Out']"));
    }

    /**
     * Helper to extract password without reloading the page if already present
     */
    private String getPasswordFromUI() {
        commonActions().click(By.linkText("Forgot your password?"));
        commonActions().click(By.cssSelector(".reset-pwd-btn"));
        String passwordText = commonActions().getText(By.cssSelector("form p"));

        String[] passwordArray = passwordText.split("'");
        String password = passwordArray[1];
        
        // Go back to login
        commonActions().click(By.className("go-to-login-btn"));
        return password;
    }

    @Test
    public void locatorExamples3() {
        commonActions().goTo("https://rahulshettyacademy.com/AutomationPractice/");
        commonActions().getText(By.xpath("//header/div/button[1]/following-sibling::button[1]"));
        commonActions().getText(By.xpath("//header/div/button[1]/parent::div/button[2]"));
    }
}
