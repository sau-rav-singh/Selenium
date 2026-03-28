package com.saurav;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownsTest extends TestBase {

    @Test
    public void selectTest() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        By currencyDropdown = By.id("ctl00_mainContent_DropDownListCurrency");
        
        commonActions.selectByIndex(currencyDropdown, 2);
        Assert.assertEquals(commonActions.getFirstSelectedOption(currencyDropdown), "AED");
        
        commonActions.selectByVisibleText(currencyDropdown, "INR");
        Assert.assertEquals(commonActions.getFirstSelectedOption(currencyDropdown), "INR");
        
        commonActions.selectByValue(currencyDropdown, "USD");
        Assert.assertEquals(commonActions.getFirstSelectedOption(currencyDropdown), "USD");
    }

    @Test
    public void passengerQtyTest() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        
        commonActions.click(By.id("divpaxinfo"));
        commonActions.click(By.id("hrefIncAdt"));
        
        String adultCount = commonActions.getText(By.xpath("//span[@id='spanAudlt']"));
        Assert.assertEquals(adultCount, "2");
        
        commonActions.click(By.id("hrefIncChd"));
        String childCount = commonActions.getText(By.xpath("//span[@id='spanChild']"));
        Assert.assertEquals(childCount, "1");

        commonActions.click(By.id("btnclosepaxoption"));

        String passengerInfo = commonActions.getText(By.id("divpaxinfo"));
        Assert.assertEquals(passengerInfo, "2 Adult, 1 Child");
    }
}
