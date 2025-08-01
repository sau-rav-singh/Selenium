package testUtilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static final String GRID_URL = "http://localhost:4444/wd/hub";

    public WebDriver getDriver() {
        if (webDriverThreadLocal.get() == null) {
            initializeWebDriver();
        }
        return webDriverThreadLocal.get();
    }

    private void initializeWebDriver() {
        Properties prop = GenericUtils.getDataFromPropertyFile("global");
        String isHeadless = System.getProperty("headless", prop.getProperty("headless", "false"));
        //browser and gridEnabled are coming from Surefire in Pom
        String browser = System.getProperty("browser", prop.getProperty("browser", "chrome"));
        boolean gridEnabled = Boolean.parseBoolean(System.getProperty("selenium.grid.enabled", prop.getProperty("selenium.grid.enabled", "false")));

        if (gridEnabled) {
            initializeRemoteWebDriver(browser, isHeadless);
        } else {
            switch (browser.toLowerCase()) {
                case "chrome":
                    initializeChromeDriver(isHeadless);
                    break;
                case "edge":
                    initializeEdgeDriver(isHeadless);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
    }

    private void initializeRemoteWebDriver(String browser, String headlessMode) {
        MutableCapabilities capabilities = switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeOptions();
            case "edge" -> new EdgeOptions();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

        if ("true".equalsIgnoreCase(headlessMode)) {
            assert capabilities instanceof ChromeOptions;
            ((ChromeOptions) capabilities).addArguments("--headless");
        }

        try {
            webDriverThreadLocal.set(new RemoteWebDriver(new URI(GRID_URL).toURL(), capabilities));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Grid URL: " + GRID_URL, e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeChromeDriver(String headlessMode) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        File extensionFile = new File("src/test/resources/adguard.crx");
        chromeOptions.addExtensions(extensionFile);
        if ("true".equalsIgnoreCase(headlessMode)) {
            chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1920,1080");
        }
        webDriverThreadLocal.set(new ChromeDriver(chromeOptions));
    }

    private void initializeEdgeDriver(String headlessMode) {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--disable-notifications");
        if ("true".equalsIgnoreCase(headlessMode)) {
            edgeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1920,1080");
        }
        webDriverThreadLocal.set(new EdgeDriver(edgeOptions));
    }

    public void quitWebDriver() {
        if (webDriverThreadLocal.get() != null) {
            webDriverThreadLocal.get().quit();
            webDriverThreadLocal.remove();
        }
    }
}

