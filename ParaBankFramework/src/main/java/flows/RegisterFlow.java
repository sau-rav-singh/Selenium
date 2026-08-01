package flows;

import manager.PageObjectManager;
import pages.RegisterPage;
import utils.TestDataFactory;

public class RegisterFlow {
    private final RegisterPage registerPage;

    public RegisterFlow(PageObjectManager pageObjectManager) {
        this.registerPage = pageObjectManager.getRegisterPage();
    }

    public void registerUser(TestDataFactory.UserRegistrationData userData) {
        registerPage.fillRegistrationForm(userData.getFirstName(), userData.getLastName(), userData.getAddress(), userData.getCity(), userData.getState(), userData.getZipCode(), userData.getPhoneNumber(), userData.getSsn(), userData.getUsername(), userData.getPassword(), userData.getRepeatPassword());
        registerPage.clickRegister();
    }

    public boolean isRegistrationSuccessful() {
        return registerPage.isRegistrationSuccessful();
    }
}
