package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage extends BasePage {


    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    private final By amountTextBox = By.cssSelector("input#amount");
    private final By fromAccountSelect = By.cssSelector("select#fromAccountId");
    private final By toAccountSelect = By.cssSelector("select#toAccountId");
    private final By transferButton = By.xpath("//input[@type='submit']");
    private final By statusMessage = By.xpath("//div[@id='showResult']/p");

    public void enterTransferAmount(String amount) {
        type(amountTextBox, amount);
    }

    public void selectFromAccount(String fromAccount) {
        Select select = new Select(getElement(fromAccountSelect));
        select.selectByVisibleText(fromAccount);
    }

    public void selectToAccount(String toAccount) {
        Select select = new Select(getElement(toAccountSelect));
        select.selectByVisibleText(toAccount);
    }

    public void clickTransfer() {
        click(transferButton);
    }

    public String getTransactionStatus() {
        return getText(statusMessage);
    }

}
