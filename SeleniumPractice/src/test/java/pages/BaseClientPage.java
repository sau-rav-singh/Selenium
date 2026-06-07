package pages;

import utils.CommonActions;

public class BaseClientPage {
    protected final CommonActions commonActions;
    private MainMenuSection mainMenuSection;

    public BaseClientPage(CommonActions commonActions) {
        this.commonActions = commonActions;
    }

    public MainMenuSection mainMenuSection() {
        if (mainMenuSection == null) {
            mainMenuSection = new MainMenuSection(commonActions);
        }
        return mainMenuSection;
    }
}
