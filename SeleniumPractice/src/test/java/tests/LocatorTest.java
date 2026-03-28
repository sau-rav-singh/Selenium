package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestBase;

public class LocatorTest extends TestBase {

    @Test
    public void locatorExamples() {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        
        commonActions.sendText(By.id("inputUsername"), "rahul");
        commonActions.sendText(By.name("inputPassword"), "hello123");
        commonActions.click(By.className("signInBtn"));
        
        System.out.println(commonActions.getText(By.cssSelector("p.error")));
        
        commonActions.click(By.linkText("Forgot your password?"));
        
        commonActions.sendText(By.xpath("//input[@placeholder='Name']"), "John");
        commonActions.sendText(By.cssSelector("input[placeholder='Email']"), "john@rsa.com");
        commonActions.click(By.xpath("//input[@type='text'][2]")); // Focus
        driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
        commonActions.sendText(By.cssSelector("input[type='text']:nth-child(3)"), "john@gmail.com");
        commonActions.sendText(By.xpath("//form/input[3]"), "9864353253");
        commonActions.click(By.cssSelector(".reset-pwd-btn"));
        
        System.out.println(commonActions.getText(By.cssSelector("form p")));
        
        commonActions.click(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]"));
        
        commonActions.sendText(By.cssSelector("#inputUsername"), "rahul");
        commonActions.sendText(By.cssSelector("input[type*='pass']"), "rahulshettyacademy");
        
        commonActions.click(By.id("chkboxOne"));
        commonActions.click(By.xpath("//button[contains(@class,'submit')]"));
    }

    @Test
    public void locatorExamples2() {
        String name = "rahul";
        String password = getPassword();
        
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        commonActions.sendText(By.id("inputUsername"), name);
        commonActions.sendText(By.name("inputPassword"), password);
        commonActions.click(By.className("signInBtn"));
        
        Assert.assertEquals(commonActions.getText(By.tagName("p")), "You are successfully logged in.");
        String userName = commonActions.getText(By.cssSelector("div[class='login-container'] h2"));
        Assert.assertEquals(userName, "Hello " + name + ",");
        
        commonActions.click(By.xpath("//*[text()='Log Out']"));
    }

    private String getPassword() {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        commonActions.click(By.linkText("Forgot your password?"));
        
        commonActions.click(By.cssSelector(".reset-pwd-btn"));
        String passwordText = commonActions.getText(By.cssSelector("form p"));
        
        String[] passwordArray = passwordText.split("'");
        return passwordArray[1];
    }

    @Test
    public void locatorExamples3() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        System.out.println(commonActions.getText(By.xpath("//header/div/button[1]/following-sibling::button[1]")));
        System.out.println(commonActions.getText(By.xpath("//header/div/button[1]/parent::div/button[2]")));
    }
}
