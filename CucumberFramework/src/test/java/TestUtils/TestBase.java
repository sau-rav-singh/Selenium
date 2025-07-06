package TestUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    public WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public WebDriver initializeDriver() throws IOException {
        if (getDriver() == null) {
            String env = System.getProperty("env", "qa"); // Default to 'qa' if not provided
            Properties prop = new Properties();
            String propertiesFile = "src/test/resources/global.properties";
            FileInputStream fis = new FileInputStream(propertiesFile);
            prop.load(fis);

            String url = prop.getProperty(env+".url");
            String browser = prop.getProperty(env+".browser");

            if (browser.equalsIgnoreCase("chrome")) {
                webDriverThreadLocal.set(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--blink-settings=imagesEnabled=false");
                webDriverThreadLocal.set(new EdgeDriver(options));
            }
            WebDriver driver=webDriverThreadLocal.get();
            WebDriverListener myListner = new DriverListener(driver);
            driver=new EventFiringDecorator<>(myListner).decorate(driver);
            webDriverThreadLocal.set(driver);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            getDriver().manage().window().maximize();
            getDriver().get(url);
        }
        return getDriver();
    }
    public static void clearDriver() {
        webDriverThreadLocal.remove();
    }
}