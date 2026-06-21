package flows;

import manager.PageObjectManager;
import pages.LoginPage;
import pages.OverviewPage;

public class LoginFlow {
    private final LoginPage loginPage;
    private final OverviewPage overviewPage;

    public LoginFlow(PageObjectManager pageObjectManager) {
        this.loginPage = pageObjectManager.getLoginPage();
        this.overviewPage = pageObjectManager.getOverviewPage();
    }

    public void login(String username, String password) {
        loginPage.enterUserName(username);
        loginPage.enterPassWord(password);
        loginPage.clickLogIn();
    }

    public boolean isUserLoggedIn() {
        return overviewPage.isAccountsOverviewDisplayed();
    }
}
