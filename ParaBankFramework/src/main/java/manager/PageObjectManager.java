package manager;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.OverviewPage;
import pages.OpenAccountPage;
import pages.TransferFundsPage;
import pages.RegisterPage;

public class PageObjectManager {
    private final WebDriver driver;

    private LoginPage loginPage;
    private OverviewPage overviewPage;
    private OpenAccountPage openAccountPage;
    private TransferFundsPage transferFundsPage;
    private RegisterPage registerPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public OverviewPage getOverviewPage() {
        if (overviewPage == null) {
            overviewPage = new OverviewPage(driver);
        }
        return overviewPage;
    }

    public OpenAccountPage getOpenAccountPage() {
        if (openAccountPage == null) {
            openAccountPage = new OpenAccountPage(driver);
        }
        return openAccountPage;
    }

    public TransferFundsPage getTransferFundsPage() {
        if (transferFundsPage == null) {
            transferFundsPage = new TransferFundsPage(driver);
        }
        return transferFundsPage;
    }

    public RegisterPage getRegisterPage() {
        if (registerPage == null) {
            registerPage = new RegisterPage(driver);
        }
        return registerPage;
    }
}
