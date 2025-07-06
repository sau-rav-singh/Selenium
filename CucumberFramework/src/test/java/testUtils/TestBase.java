package testUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public WebDriver initializeDriver() throws IOException {
        if (webDriverThreadLocal.get() != null) {
            return webDriverThreadLocal.get();
        }
        Properties prop = new Properties();
        try (var inputStream = getClass().getClassLoader().getResourceAsStream("global.properties")) {
            if (inputStream == null) {
                throw new IOException("global.properties not found in the classpath");
            }
            prop.load(inputStream);
        }

        String env = System.getProperty("env", "qa");
        String url = prop.getProperty(env + ".url");
        String browser = prop.getProperty(env + ".browser", "chrome");

        WebDriver originalDriver;
        if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--blink-settings=imagesEnabled=false");
            originalDriver = new EdgeDriver(options);
        } else {
            originalDriver = new ChromeDriver();
        }

        WebDriverListener listener = new DriverListener(originalDriver);
        WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(originalDriver);
        decoratedDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        decoratedDriver.manage().window().maximize();
        decoratedDriver.get(url);
        webDriverThreadLocal.set(decoratedDriver);
        return getDriver();
    }

    public static void clearDriver() {
        webDriverThreadLocal.remove();
    }
}