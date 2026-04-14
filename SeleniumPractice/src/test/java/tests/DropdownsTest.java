package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.TestBase;

import java.util.List;

public class DropdownsTest extends TestBase {

    @Test
    public void selectTest() {
        commonActions().goTo("https://rahulshettyacademy.com/dropdownsPractise/");
        By currencyDropdown = By.id("ctl00_mainContent_DropDownListCurrency");

        commonActions().selectByIndex(currencyDropdown, 2);
        commonActions().assertEquals(commonActions().getFirstSelectedOption(currencyDropdown), "AED", "Verify selected currency by index 2");

        commonActions().selectByVisibleText(currencyDropdown, "INR");
        commonActions().assertEquals(commonActions().getFirstSelectedOption(currencyDropdown), "INR", "Verify selected currency by visible text 'INR'");

        commonActions().selectByValue(currencyDropdown, "USD");
        commonActions().assertEquals(commonActions().getFirstSelectedOption(currencyDropdown), "USD", "Verify selected currency by value 'USD'");
    }

    @Test
    public void passengerQtyTest() {
        commonActions().goTo("https://rahulshettyacademy.com/dropdownsPractise/");

        commonActions().click(By.id("divpaxinfo"));
        commonActions().click(By.id("hrefIncAdt"));

        commonActions().assertEquals(commonActions().getText(By.xpath("//span[@id='spanAudlt']")), "2", "Verify adult passenger count");

        commonActions().click(By.id("hrefIncChd"));
        commonActions().assertEquals(commonActions().getText(By.xpath("//span[@id='spanChild']")), "1", "Verify child passenger count");

        commonActions().click(By.id("btnclosepaxoption"));

        String passengerInfo = commonActions().getText(By.id("divpaxinfo"));
        commonActions().assertEquals(passengerInfo, "2 adult, 1 child", "Verify passenger summary info");
    }

    @Test(enabled = false)
    public void itineraryTest() {
        commonActions().goTo("https://rahulshettyacademy.com/dropdownsPractise/");
        commonActions().click(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
        commonActions().click(By.xpath("//a[@value='DEL']"));
        commonActions().click(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']"));
        commonActions().click(By.cssSelector("a.ui-state-active"));
        commonActions().click(By.id("ctl00_mainContent_rbtnl_Trip_1"));
        commonActions().click(By.id("Div1"));
        commonActions().click(By.cssSelector("a.ui-state-active"));
    }

    @Test
    public void selectCountryTest() {
        commonActions().goTo("https://rahulshettyacademy.com/dropdownsPractise/");
        commonActions().sendText(By.id("autosuggest"), "ind");
        
        // Directly find the specific element or stream it for better readability
        List<WebElement> options = commonActions().findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        WebElement country = options.stream()
                .filter(option -> option.getText().equalsIgnoreCase("India"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country 'India' not found in autosuggest"));
        
        commonActions().click(country);
    }

    @Test
    public void passengerTypeTest() {
        commonActions().goTo("https://rahulshettyacademy.com/dropdownsPractise/");
        By seniorCitizenCheckbox = By.cssSelector("input[id*='SeniorCitizen'][type='checkbox']");
        
        commonActions().click(seniorCitizenCheckbox);
        commonActions().assertEquals(
                commonActions().findElement(seniorCitizenCheckbox).isSelected(), 
                true, 
                "Verify Senior Citizen checkbox isSelected"
        );
    }
}
