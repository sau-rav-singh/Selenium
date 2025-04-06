package RahulDP.Tests;

import RahulDP.PageComponents.FooterNavigation;
import RahulDP.PageComponents.NavigationBar;
import RahulDP.PageObjects.TravelHomePage;
import RahulDP.Utilities.TestBase;
import RahulDP.data.DataProviders;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FooterTest extends TestBase {
    TravelHomePage travelHomePage;
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = startDriver();
        travelHomePage = new TravelHomePage(driver);

    }

    @Test(dataProvider = "flights", dataProviderClass = DataProviders.class)
    public void flightTest(HashMap<String, String> reservationDetails) {
        travelHomePage.goTo();
        FooterNavigation footer = travelHomePage.getFooterBar();
        footer.getFlightAttribute();
        footer.getLinkCount();
        NavigationBar navBar = travelHomePage.getNavigationBar();
        navBar.getFlightAttribute();
        navBar.getLinkCount();
        travelHomePage.setBookingStrategy("multiTrip");
        travelHomePage.checkAvailability(reservationDetails);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
