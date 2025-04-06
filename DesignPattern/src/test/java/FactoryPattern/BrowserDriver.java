package FactoryPattern;

import org.openqa.selenium.WebDriver;

public interface BrowserDriver {
    WebDriver createDriver();
}
