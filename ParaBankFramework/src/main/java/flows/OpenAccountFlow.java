package flows;

import manager.PageObjectManager;
import pages.OpenAccountPage;
import pages.OverviewPage;

public class OpenAccountFlow {
    private final OpenAccountPage openAccountPage;
    private final OverviewPage overviewPage;

    public OpenAccountFlow(PageObjectManager pageObjectManager) {
        this.openAccountPage = pageObjectManager.getOpenAccountPage();
        this.overviewPage = pageObjectManager.getOverviewPage();
    }

    public void navigateToOpenAccountPage() {
        overviewPage.clickOpenNewAccount();
    }

    public String createNewAccount(String accountType, String baseAccount) {
        openAccountPage.selectAccountType(accountType);
        openAccountPage.selectBaseAccount(baseAccount);
        openAccountPage.clickOpenNewAccountButton();
        return openAccountPage.getNewAccountNumber();
    }

}
