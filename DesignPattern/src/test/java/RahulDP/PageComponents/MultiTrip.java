package RahulDP.PageComponents;

import RahulDP.AbstractComponents.SearchFlightAvail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.function.Consumer;

public class MultiTrip implements SearchFlightAvail {
    private final By from = By.id("ctl00_mainContent_ddl_originStation1_CTXT");
    private final By to = By.id("ctl00_mainContent_ddl_destinationStation1_CTXT");
    private final By multiCity_rdo = By.id("ctl00_mainContent_rbtnl_Trip_2");
    private final By destination_2 = By.id("ctl00_mainContent_ddl_originStation2_CTXT");
    private final By modalPopUp = By.id("MultiCityModelAlert");
    WebDriver driver;
    //private By submit = By.id("ctl00_mainContent_btn_FindFlights");

    public MultiTrip(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void checkAvail(HashMap<String, String> reservationDetails) {
        makeStateReady(s -> selectOriginCity(reservationDetails.get("origin")));
        selectDestinationCity(reservationDetails.get("destination"));
        selectDestinationCity2(reservationDetails.get("destination2"));
    }

    public void selectOriginCity(String origin) {
        driver.findElement(from).click();
        driver.findElement(By.xpath("//a[@value='" + origin + "']")).click();
    }

    public void selectDestinationCity(String destination) {
        driver.findElement(to).click();
        driver.findElement(By.xpath("(//a[@value='" + destination + "'])[2]")).click();
    }

    public void selectDestinationCity2(String destination2) {
        driver.findElement(destination_2).click();
        driver.findElement(By.xpath("(//a[@value='" + destination2 + "'])[3]")).click();
    }

    public void makeStateReady(Consumer<MultiTrip> consumer) {
        driver.findElement(multiCity_rdo).click();
        driver.findElement(modalPopUp).click();
        waitForElementToDisappear(modalPopUp);
        consumer.accept(this);
    }

    public void waitForElementToDisappear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

}
