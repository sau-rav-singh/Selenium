package manager;

import flows.LoginFlow;
import flows.OpenAccountFlow;
import flows.TransferFundsFlow;
import lombok.Getter;

public class FlowManager {
    @Getter
    private final LoginFlow loginFlow;
    @Getter
    private final OpenAccountFlow openAccountFlow;
    @Getter
    private final TransferFundsFlow transferFundsFlow;

    public FlowManager(PageObjectManager pageObjectManager) {
        this.loginFlow = new LoginFlow(pageObjectManager);
        this.openAccountFlow = new OpenAccountFlow(pageObjectManager);
        this.transferFundsFlow = new TransferFundsFlow(pageObjectManager);
    }

}
