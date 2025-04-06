package FactoryPattern;

public class DriverFactory {

    public static BrowserDriver getDriver(String browserType) {
        return switch (browserType.toLowerCase()) {
            case "chrome" -> new ChromeDriverManager();
            case "firefox" -> new FirefoxDriverManager();
            case "edge" -> new EdgeDriverManager();
            default -> throw new IllegalArgumentException("Unknown browser type: " + browserType);
        };
    }

}