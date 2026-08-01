package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By firstNameField = By.id("customer.firstName");
    private final By lastNameField = By.id("customer.lastName");
    private final By addressField = By.id("customer.address.street");
    private final By cityField = By.id("customer.address.city");
    private final By stateField = By.id("customer.address.state");
    private final By zipCodeField = By.id("customer.address.zipCode");
    private final By phoneNumberField = By.id("customer.phoneNumber");
    private final By ssnField = By.id("customer.ssn");
    private final By usernameField = By.id("customer.username");
    private final By passwordField = By.id("customer.password");
    private final By confirmPasswordField = By.id("repeatedPassword");
    private final By registerButton = By.xpath("//input[@value='Register']");
    private final By registrationSuccessMessage = By.xpath("//h1[contains(text(),'Welcome')]");
    private final By registrationErrorMessage = By.xpath("//p[@class='error']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterAddress(String address) {
        type(addressField, address);
    }

    public void enterCity(String city) {
        type(cityField, city);
    }

    public void enterState(String state) {
        type(stateField, state);
    }

    public void enterZipCode(String zipCode) {
        type(zipCodeField, zipCode);
    }

    public void enterPhoneNumber(String phoneNumber) {
        type(phoneNumberField, phoneNumber);
    }

    public void enterSSN(String ssn) {
        type(ssnField, ssn);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }

    public void clickRegister() {
        click(registerButton);
    }

    public boolean isRegistrationSuccessful() {
        return isDisplayed(registrationSuccessMessage);
    }

    public String getRegistrationErrorMessage() {
        return getText(registrationErrorMessage);
    }

    public boolean isRegistrationErrorDisplayed() {
        return isDisplayed(registrationErrorMessage);
    }

    public void fillRegistrationForm(String firstName, String lastName, String address, String city,
                                      String state, String zipCode, String phoneNumber, String ssn,
                                      String username, String password, String confirmPassword) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterZipCode(zipCode);
        enterPhoneNumber(phoneNumber);
        enterSSN(ssn);
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
    }
}
