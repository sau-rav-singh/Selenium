package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.TestBase;

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
}
