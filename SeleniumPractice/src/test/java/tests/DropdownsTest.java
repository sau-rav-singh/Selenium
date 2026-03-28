package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.TestBase;

public class DropdownsTest extends TestBase {

    @Test
    public void selectTest() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        By currencyDropdown = By.id("ctl00_mainContent_DropDownListCurrency");
        
        commonActions.selectByIndex(currencyDropdown, 2);
        commonActions.assertEquals(commonActions.getFirstSelectedOption(currencyDropdown), "AED", "Verify selected currency by index 2");
        
        commonActions.selectByVisibleText(currencyDropdown, "INR");
        commonActions.assertEquals(commonActions.getFirstSelectedOption(currencyDropdown), "INR", "Verify selected currency by visible text 'INR'");
        
        commonActions.selectByValue(currencyDropdown, "USD");
        commonActions.assertEquals(commonActions.getFirstSelectedOption(currencyDropdown), "USD", "Verify selected currency by value 'USD'");
    }

    @Test
    public void passengerQtyTest() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        
        commonActions.click(By.id("divpaxinfo"));
        commonActions.click(By.id("hrefIncAdt"));
        
        String adultCount = commonActions.getText(By.xpath("//span[@id='spanAudlt']"));
        commonActions.assertEquals(adultCount, "2", "Verify adult passenger count");
        
        commonActions.click(By.id("hrefIncChd"));
        String childCount = commonActions.getText(By.xpath("//span[@id='spanChild']"));
        commonActions.assertEquals(childCount, "1", "Verify child passenger count");

        commonActions.click(By.id("btnclosepaxoption"));

        String passengerInfo = commonActions.getText(By.id("divpaxinfo"));
        commonActions.assertEquals(passengerInfo, "2 Adult, 1 Child", "Verify passenger summary info");
    }
}
