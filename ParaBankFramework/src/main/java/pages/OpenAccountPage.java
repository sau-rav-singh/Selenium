package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    private final By accountTypeDropdown = By.cssSelector("select#type");
    private final By baseAccountDropdown = By.cssSelector("select#fromAccountId");
    private final By openNewAccountButton = By.xpath("//input[@type='button']");
    private final By newAccountNumberLink = By.cssSelector("a#newAccountId");

    public void selectAccountType(String accountType) {
        Select select = new Select(getElement(accountTypeDropdown));
        select.selectByVisibleText(accountType);
    }

    public void selectBaseAccount(String baseAccount) {
        Select select = new Select(getElement(baseAccountDropdown));
        select.selectByVisibleText(baseAccount);
    }

    public void clickOpenNewAccountButton() {
        click(openNewAccountButton);
    }

    public String getNewAccountNumber() {
        return getText(newAccountNumberLink);
    }
}
