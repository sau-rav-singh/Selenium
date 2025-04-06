package RahulDP.AbstractComponents;

import RahulDP.PageComponents.MultiTrip;
import RahulDP.PageComponents.RoundTrip;
import org.openqa.selenium.WebDriver;

public class StrategyFactory {
    WebDriver driver;

    public StrategyFactory(WebDriver driver) {
        this.driver = driver;
    }

    public SearchFlightAvail createStrategy(String tripType) {
        if (tripType.equalsIgnoreCase("MultiTrip")) {
            return new MultiTrip(driver);
        } else {
            return new RoundTrip(driver);
        }
    }
}
