package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class OffersPage {
    WebDriver driver;
    private By TOPDEALS= By.linkText("Top Deals");
    private By SEARCH=By.id("search-field");

    public OffersPage(WebDriver driver) {
        this.driver=driver;
    }

    public void clickTopDeals(){
        driver.findElement(TOPDEALS).click();
    }

    public List<String> searchProduct(String productName){
        driver.findElement(SEARCH).sendKeys(productName);
        return driver.findElements(By.xpath("//tbody/tr/td[1]")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void closeBrowser(){
        driver.quit();
    }
}
