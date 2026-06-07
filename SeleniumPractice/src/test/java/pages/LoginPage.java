package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CommonActions;

public class LoginPage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final CommonActions commonActions;

    private final By emailInputLocator = By.id("userEmail");
    private final By passwordInputLocator = By.id("userPassword");
    private final By loginButtonLocator = By.id("login");

    public LoginPage(CommonActions commonActions) {
        this.commonActions = commonActions;
    }

    public void login(String email, String password) {
        logger.info("Attempting login with email: {}", email);
        commonActions.sendText(emailInputLocator, email);
        commonActions.sendText(passwordInputLocator, password);
        commonActions.click(loginButtonLocator);
        logger.info("Login successful");
    }

    public void loginWithDefaultCredentials() {
        login("selena@gomez.com", "Iamking@000");
    }
}


