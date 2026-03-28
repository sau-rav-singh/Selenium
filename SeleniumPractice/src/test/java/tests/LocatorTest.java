package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.TestBase;

public class LocatorTest extends TestBase {

    @Test
    public void locatorExamples() {
        getDriver().get("https://rahulshettyacademy.com/locatorspractice/");

        actions().sendText(By.id("inputUsername"), "rahul");
        actions().sendText(By.name("inputPassword"), "hello123");
        actions().click(By.className("signInBtn"));
        
        System.out.println(actions().getText(By.cssSelector("p.error")));
        
        actions().click(By.linkText("Forgot your password?"));
        
        actions().sendText(By.xpath("//input[@placeholder='Name']"), "John");
        actions().sendText(By.cssSelector("input[placeholder='Email']"), "john@rsa.com");
        actions().click(By.xpath("//input[@type='text'][2]")); // Focus
        getDriver().findElement(By.xpath("//input[@type='text'][2]")).clear();
        actions().sendText(By.cssSelector("input[type='text']:nth-child(3)"), "john@gmail.com");
        actions().sendText(By.xpath("//form/input[3]"), "9864353253");
        actions().click(By.cssSelector(".reset-pwd-btn"));
        
        System.out.println(actions().getText(By.cssSelector("form p")));
        
        actions().click(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]"));
        
        actions().sendText(By.cssSelector("#inputUsername"), "rahul");
        actions().sendText(By.cssSelector("input[type*='pass']"), "rahulshettyacademy");
        
        actions().click(By.id("chkboxOne"));
        actions().click(By.xpath("//button[contains(@class,'submit')]"));
    }

    @Test
    public void locatorExamples2() {
        String name = "rahul";
        String password = getPassword();

        getDriver().get("https://rahulshettyacademy.com/locatorspractice/");
        actions().sendText(By.id("inputUsername"), name);
        actions().sendText(By.name("inputPassword"), password);
        actions().click(By.className("signInBtn"));
        
        actions().assertEquals(actions().getText(By.tagName("p")), "You are successfully logged in.", "Verify login success message");
        String userName = actions().getText(By.cssSelector("div[class='login-container'] h2"));
        actions().assertEquals(userName, "Hello " + name + ",", "Verify username in welcome message");
        
        actions().click(By.xpath("//*[text()='Log Out']"));
    }

    private String getPassword() {
        getDriver().get("https://rahulshettyacademy.com/locatorspractice/");
        actions().click(By.linkText("Forgot your password?"));
        
        actions().click(By.cssSelector(".reset-pwd-btn"));
        String passwordText = actions().getText(By.cssSelector("form p"));
        
        String[] passwordArray = passwordText.split("'");
        return passwordArray[1];
    }

    @Test
    public void locatorExamples3() {
        getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        System.out.println(actions().getText(By.xpath("//header/div/button[1]/following-sibling::button[1]")));
        System.out.println(actions().getText(By.xpath("//header/div/button[1]/parent::div/button[2]")));
    }
}
