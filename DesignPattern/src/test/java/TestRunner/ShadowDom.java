package TestRunner;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ShadowDom {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://selectorshub.com/iframe-in-shadow-dom/");
        WebElement shadowHost = driver.findElement(By.id("userName"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement usernameField = shadowRoot.findElement(By.cssSelector("#kils"));
        usernameField.sendKeys("your_username");
        //document.querySelector("#userName").shadowRoot.querySelector("#app2").shadowRoot.querySelector("#pizza")
        WebElement shadowHost2 = shadowRoot.findElement(By.cssSelector("#app2"));
        SearchContext shadowRoot2 = shadowHost2.getShadowRoot();
        WebElement pizzaText = shadowRoot2.findElement(By.cssSelector("#pizza"));
        pizzaText.sendKeys("Dominos");
    }
}
