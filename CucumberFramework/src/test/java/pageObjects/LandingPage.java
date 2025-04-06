package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class LandingPage {

    private final By SEARCH = By.xpath("//input[@type='search']");
    private final By PRODUCTNAMES = By.xpath("//div[@class='product']");
    private final By INCREMENT = By.cssSelector("a.increment");
    private final By ADDTOCART = By.cssSelector(".product-action button");


    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchTerm(String productName) {
        driver.findElement(SEARCH).clear();
        driver.findElement(SEARCH).sendKeys(productName);
    }

    public List<String> extractProductNames() {
        List<WebElement> products = driver.findElements(PRODUCTNAMES);
        return products.stream().map(product -> product.findElement(By.xpath(".//h4")).getText()).map(name -> name.split("-")[0].trim()).collect(Collectors.toList());
    }

    public String getTitleLandingPage() {
        return driver.getTitle();
    }

    public void incrementQuantity(int quantity) {
        int i = quantity - 1;
        while (i > 0) {
            driver.findElement(INCREMENT).click();
            i--;
        }
    }

    public void addToCart() {
        driver.findElement(ADDTOCART).click();
    }

}
