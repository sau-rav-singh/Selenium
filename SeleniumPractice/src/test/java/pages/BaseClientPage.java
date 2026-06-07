package pages;

import utils.CommonActions;

public class BaseClientPage {
    protected final CommonActions commonActions;

    public BaseClientPage(CommonActions commonActions){
        this.commonActions=commonActions;
    }

    public MainMenuSection mainMenuSection(){
        return new MainMenuSection(commonActions);
    }
}
