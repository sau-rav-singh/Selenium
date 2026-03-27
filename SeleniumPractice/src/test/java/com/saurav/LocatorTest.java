package com.saurav;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocatorTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void locatorExamples() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername"))).sendKeys("rahul");
        driver.findElement(By.name("inputPassword")).sendKeys("hello123");
        driver.findElement(By.className("signInBtn")).click();
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error"))).getText());
        driver.findElement(By.linkText("Forgot your password?")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Name']"))).sendKeys("John");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@rsa.com");
        driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("john@gmail.com");
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form p"))).getText());
        driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputUsername"))).sendKeys("rahul");
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("chkboxOne"))).click();
        driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
    }

    @Test
    public void locatorExamples2() {
        String name = "rahul";
        String password = getPassword();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername"))).sendKeys(name);
        driver.findElement(By.name("inputPassword")).sendKeys(password);
        driver.findElement(By.className("signInBtn")).click();
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p"))).getText(), "You are successfully logged in.");
        String userName = driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText();
        Assert.assertEquals(userName, "Hello " + name + ",");
        driver.findElement(By.xpath("//*[text()='Log Out']")).click();
    }

    private String getPassword() {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgot your password?"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".reset-pwd-btn"))).click();
        String passwordText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form p"))).getText();
        String[] passwordArray = passwordText.split("'");
        return passwordArray[1];
    }

    @Test
    public void locatorExamples3() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header/div/button[1]/following-sibling::button[1]"))).getText());
        System.out.println(driver.findElement(By.xpath("//header/div/button[1]/parent::div/button[2]")).getText());
    }
}
