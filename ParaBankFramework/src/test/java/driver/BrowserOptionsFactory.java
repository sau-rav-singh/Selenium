package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ConfigReader;

public class BrowserOptionsFactory {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        if (ConfigReader.isIncognito()) {
            options.addArguments("--incognito");
        }

        String downloadDir = ConfigReader.getDownloadDirectory();
        if (downloadDir != null && !downloadDir.isEmpty()) {
            options.addArguments("--download-default-directory=" + downloadDir);
        }

        if (ConfigReader.isDisableNotifications()) {
            options.addArguments("--disable-notifications");
        }

        options.setPageLoadStrategy(PageLoadStrategy.valueOf(ConfigReader.getPageLoadStrategy().toUpperCase()));

        if (!ConfigReader.isHeadless()) {
            options.addArguments("--start-maximized");
        }

        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("-headless");
        }

        if (ConfigReader.isIncognito()) {
            options.addArguments("-private");
        }

        String downloadDir = ConfigReader.getDownloadDirectory();
        if (downloadDir != null && !downloadDir.isEmpty()) {
            options.addArguments("-download.default_folder=" + downloadDir);
        }

        if (ConfigReader.isDisableNotifications()) {
            options.addArguments("-disable-notifications");
        }

        options.setPageLoadStrategy(PageLoadStrategy.valueOf(ConfigReader.getPageLoadStrategy().toUpperCase()));

        if (!ConfigReader.isHeadless()) {
            options.addArguments("-start-maximized");
        }

        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        if (ConfigReader.isIncognito()) {
            options.addArguments("--inprivate");
        }

        String downloadDir = ConfigReader.getDownloadDirectory();
        if (downloadDir != null && !downloadDir.isEmpty()) {
            options.addArguments("--download-default-directory=" + downloadDir);
        }

        if (ConfigReader.isDisableNotifications()) {
            options.addArguments("--disable-notifications");
        }

        options.setPageLoadStrategy(PageLoadStrategy.valueOf(ConfigReader.getPageLoadStrategy().toUpperCase()));

        if (!ConfigReader.isHeadless()) {
            options.addArguments("--start-maximized");
        }

        return options;
    }
}
