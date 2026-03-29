package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.TestBase;

import java.util.List;

public class DropdownsTest extends TestBase {

    @Test
    public void selectTest() {
        getDriver().get("https://rahulshettyacademy.com/dropdownsPractise/");
        By currencyDropdown = By.id("ctl00_mainContent_DropDownListCurrency");

        actions().selectByIndex(currencyDropdown, 2);
        actions().assertEquals(actions().getFirstSelectedOption(currencyDropdown), "AED", "Verify selected currency by index 2");

        actions().selectByVisibleText(currencyDropdown, "INR");
        actions().assertEquals(actions().getFirstSelectedOption(currencyDropdown), "INR", "Verify selected currency by visible text 'INR'");

        actions().selectByValue(currencyDropdown, "USD");
        actions().assertEquals(actions().getFirstSelectedOption(currencyDropdown), "USD", "Verify selected currency by value 'USD'");
    }

    @Test
    public void passengerQtyTest() {
        getDriver().get("https://rahulshettyacademy.com/dropdownsPractise/");

        actions().click(By.id("divpaxinfo"));
        actions().click(By.id("hrefIncAdt"));

        String adultCount = actions().getText(By.xpath("//span[@id='spanAudlt']"));
        actions().assertEquals(adultCount, "2", "Verify adult passenger count");

        actions().click(By.id("hrefIncChd"));
        String childCount = actions().getText(By.xpath("//span[@id='spanChild']"));
        actions().assertEquals(childCount, "1", "Verify child passenger count");

        actions().click(By.id("btnclosepaxoption"));

        String passengerInfo = actions().getText(By.id("divpaxinfo"));
        actions().assertEquals(passengerInfo, "2 Adult, 1 Child", "Verify passenger summary info");
    }

    @Test
    public void itineraryTest() {
        getDriver().get("https://rahulshettyacademy.com/dropdownsPractise/");
        actions().click(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
        actions().click(By.xpath("//a[@value='DEL']"));
        actions().click(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']"));//"(//a[@value='MAA'])[2]"
        actions().click(By.cssSelector("a.ui-state-active"));
        actions().click(By.id("ctl00_mainContent_rbtnl_Trip_1"));
        actions().click(By.id("Div1"));
        actions().click(By.cssSelector("a.ui-state-active"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void selectCountryTest() {
        getDriver().get("https://rahulshettyacademy.com/dropdownsPractise/");
        actions().sendText(By.id("autosuggest"), "ind");
        List<WebElement> options = actions().findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase("India")) {
                actions().click(option);
                break;
            }
        }
    }

    @Test
    public void passengerTypeTest() {
        getDriver().get("https://rahulshettyacademy.com/dropdownsPractise/");
        actions().click(By.cssSelector("input[id*='SeniorCitizen'][type='checkbox']"));
        actions().assertEquals(actions().findElement(By.cssSelector("input[id*='SeniorCitizen'][type='checkbox']")).isSelected(), true, "Verify Senior Citizen checkbox isSelected");

    }
}
