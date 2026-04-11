package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FluentWaitExample {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("#start button")).click();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .withMessage("Timeout: Element not found after 30s");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
        System.out.println(element.getText());

        driver.quit();
    }

    @Test
    public void closeQuitTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bing.com");
        int millis = 1000;
        Thread.sleep(millis);
        ((JavascriptExecutor) driver).executeScript("window.open('https://google.com')");
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandles);
        driver.switchTo().window(windowHandlesList.get(1));
        System.out.println("Page Title of get1 is " + driver.getTitle());
        Thread.sleep(millis);
        driver.close();
        driver.switchTo().window(windowHandlesList.getFirst());
        System.out.println("Page Title of get0 is " + driver.getTitle());
        Thread.sleep(millis);
        driver.quit();
    }
}
