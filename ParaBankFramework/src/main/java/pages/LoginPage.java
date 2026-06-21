package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By userNameField = By.xpath("//input[@name='username']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//input[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String username) {
        type(userNameField, username);
    }

    public void enterPassWord(String password) {
        type(passwordField, password);
    }

    public void clickLogIn() {
        click(loginButton);
    }
}
