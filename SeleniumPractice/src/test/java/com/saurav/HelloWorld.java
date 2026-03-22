package com.saurav;

import org.openqa.selenium.WebDriver;

public class HelloWorld {

    static void main() {
        WebDriver driver = BrowserFactory.getDriver("firefox");
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
