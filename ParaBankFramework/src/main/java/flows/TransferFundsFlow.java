package flows;

import manager.PageObjectManager;
import pages.OverviewPage;
import pages.TransferFundsPage;

public class TransferFundsFlow {
    private final TransferFundsPage transferFundsPage;
    private final OverviewPage overviewPage;

    public TransferFundsFlow(PageObjectManager pageObjectManager) {
        this.transferFundsPage = pageObjectManager.getTransferFundsPage();
        this.overviewPage = pageObjectManager.getOverviewPage();
    }

    public void navigateToFundTransferPage() {
        overviewPage.clickTransferFundsLink();
    }

    public void transferToNewAccount(String amount, String fromAccount, String toAccount) {
        transferFundsPage.enterTransferAmount(amount);
        transferFundsPage.selectFromAccount(fromAccount);
        transferFundsPage.selectToAccount(toAccount);
        transferFundsPage.clickTransfer();
    }

    public boolean isTransferSuccessful() {
        String status = transferFundsPage.getTransactionStatus();
        return status.contains("has been transferred from account");
    }
}
