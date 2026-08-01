package manager;

import flows.LoginFlow;
import flows.OpenAccountFlow;
import flows.TransferFundsFlow;
import flows.RegisterFlow;
import lombok.Getter;

public class FlowManager {
    @Getter
    private final LoginFlow loginFlow;
    @Getter
    private final OpenAccountFlow openAccountFlow;
    @Getter
    private final TransferFundsFlow transferFundsFlow;
    @Getter
    private final RegisterFlow registerFlow;

    public FlowManager(PageObjectManager pageObjectManager) {
        this.loginFlow = new LoginFlow(pageObjectManager);
        this.openAccountFlow = new OpenAccountFlow(pageObjectManager);
        this.transferFundsFlow = new TransferFundsFlow(pageObjectManager);
        this.registerFlow = new RegisterFlow(pageObjectManager);
    }

}
