package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MiscTest {

    @Test
    public void sslTest() throws IOException {
        EdgeOptions edgeOptions = new EdgeOptions();

        Proxy proxy = new Proxy();
        proxy.setHttpProxy("ipaddress:4444");
        edgeOptions.setCapability("proxy", proxy);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "/");
        edgeOptions.setExperimentalOption("prefs", prefs);

        edgeOptions.setAcceptInsecureCerts(true);

        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://expired.badssl.com/");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("screenshot/ssl.png"));
        Assert.assertEquals(driver.getTitle(), "expired.badssl.com", "Verify page title");
        driver.quit();
    }
}
