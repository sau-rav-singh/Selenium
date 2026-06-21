package driver;

import listeners.DriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver = switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(BrowserOptionsFactory.getChromeOptions());
            case "firefox" -> new FirefoxDriver(BrowserOptionsFactory.getFirefoxOptions());
            default -> new EdgeDriver(BrowserOptionsFactory.getEdgeOptions());
        };

        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(new DriverListener());
        return decorator.decorate(driver);
    }
}
