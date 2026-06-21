package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage extends BasePage {
    private final By overviewHeader = By.xpath("//*[@id='showOverview']/h1");
    private final By openNewAccountLink = By.xpath("//a[text()='Open New Account']");
    private final By transferFundsLink = By.xpath("//a[text()='Transfer Funds']");

    public OverviewPage(WebDriver driver){
        super(driver);
    }

    public boolean isAccountsOverviewDisplayed(){
        return isDisplayed(overviewHeader);
    }

    public void clickOpenNewAccount(){
        click(openNewAccountLink);
    }

    public void clickTransferFundsLink(){
        click(transferFundsLink);
    }
}
