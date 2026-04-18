package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.TestBase;

import java.util.List;

public class CalendarTest extends TestBase {

    @Test
    public void calendarSelectionTest() {
        String monthNumber = "6";
        String date = "15";
        String year = "2027";
        String[] expectedList = {monthNumber, date, year};

        commonActions().goTo("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        commonActions().click(By.cssSelector(".react-date-picker__inputGroup"));
        commonActions().click(By.cssSelector(".react-calendar__navigation__label"));
        commonActions().click(By.cssSelector(".react-calendar__navigation__label"));
        commonActions().click(By.xpath("//button[text()='" + year + "']"));
        List<WebElement> monthOptions = commonActions().findElements(By.cssSelector(".react-calendar__year-view__months__month"));
        commonActions().click(monthOptions.get(Integer.parseInt(monthNumber) - 1));
        commonActions().click(By.xpath("//abbr[text()='" + date + "']"));
        List<WebElement> actualList = commonActions().findElements(By.cssSelector(".react-date-picker__inputGroup__input"));

        for (int i = 0; i < actualList.size(); i++) {
            String actualValue = actualList.get(i).getAttribute("value");
            commonActions().assertEquals(actualValue, expectedList[i], "Verify " + (i == 0 ? "month" : i == 1 ? "date" : "year") + " value");
        }
    }
}
