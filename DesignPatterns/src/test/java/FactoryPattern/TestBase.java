package FactoryPattern;

import org.openqa.selenium.WebDriver;

public class TestBase {

    public static void main(String[] args) {
        String browserType = "edge";
        WebDriver driver = DriverFactory.getDriver(browserType).createDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
