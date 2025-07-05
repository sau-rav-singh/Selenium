package RahulDP.PageComponents;

import RahulDP.AbstractComponents.SearchFlightAvail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;


public class RoundTrip implements SearchFlightAvail {

    WebDriver driver;
    private final By rdo = By.id("ctl00_mainContent_rbtnl_Trip_1");
    private final By from = By.id("ctl00_mainContent_ddl_originStation1_CTXT");
    private final By to = By.id("ctl00_mainContent_ddl_destinationStation1_CTXT");
    private final By cb = By.id("ctl00_mainContent_chk_IndArm");
    private final By search = By.id("ctl00_mainContent_btn_FindFlights");

    public RoundTrip(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void checkAvail(HashMap<String, String> reservationDetails) {
        System.out.println("I am inside round trip ");
        driver.findElement(rdo).click();
        driver.findElement(from).click();
        selectOriginCity(reservationDetails.get("origin"));
        selectDestinationCity(reservationDetails.get("destination"));
        driver.findElement(cb).click();
        driver.findElement(search).click();
    }

    public void selectOriginCity(String origin) {
        driver.findElement(from).click();
        driver.findElement(By.xpath("//a[@value='" + origin + "']")).click();
    }

    public void selectDestinationCity(String destination) {
        driver.findElement(to).click();
        driver.findElement(By.xpath("(//a[@value='" + destination + "'])[2]")).click();
    }

}
