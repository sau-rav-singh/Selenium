package SingletonPattern;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Objects;

public class TestExample {

    private WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        driver = WebDriverSingleton.getDriver(browser);
    }

    @Test
    public void testExample1() {
        driver.get("https://www.google.com/");
        Assert.assertEquals(driver.getTitle(), "Google");
    }

    @Test
    public void testExample2() {
        driver.get("https://www.google.com/");
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("google"));
    }
    @AfterMethod
    public void teardown() {
        WebDriverSingleton.quitDriver();
    }
}
