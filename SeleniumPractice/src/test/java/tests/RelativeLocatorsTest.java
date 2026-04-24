package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.TestBase;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorsTest extends TestBase {

    @Test
    public void testFormWithRelativeLocators() {
        commonActions().goTo("https://rahulshettyacademy.com/angularpractice/");

        // 1. ABOVE: Find the Name input field located above the Email label
        String name = "John Doe";
        By input = By.xpath("//input");
        By email = By.xpath("//label[text()='Email']");
        WebElement nameInput = commonActions().findElement(with(input).above(email));
        nameInput.sendKeys(name);
        commonActions().assertEquals(nameInput.getAttribute("value"), name, "Name input should match the expected name");

        // 2. BELOW: Find the Password input located below the Password label
        WebElement passwordLabel = commonActions().findElement(By.cssSelector("label[for='exampleInputPassword1']"));
        WebElement passwordInput = commonActions().findElement(with(By.tagName("input")).below(passwordLabel));
        passwordInput.sendKeys("Password123");

        // 3. TO LEFT OF: Find the Checkbox input to the left of its label
        WebElement iceCreamLabel = commonActions().findElement(By.xpath("//label[contains(text(), 'IceCreams')]"));
        WebElement iceCreamCheckbox = commonActions().findElement(with(By.tagName("input")).toLeftOf(iceCreamLabel));
        iceCreamCheckbox.click();
        commonActions().assertEquals(iceCreamCheckbox.isSelected(), true, "Checkbox should be selected");

        // 4. TO RIGHT OF: Find the Student radio label to the right of the radio button
        WebElement studentRadioButton = commonActions().findElement(By.id("inlineRadio1"));
        WebElement studentLabel = commonActions().findElement(with(By.tagName("label")).toRightOf(studentRadioButton));
        studentLabel.click();

        // 5. NEAR: Find the Submit button near the Date of Birth field
        WebElement dobInput = commonActions().findElement(By.name("bday"));
        WebElement submitBtn = commonActions().findElement(with(By.tagName("input")).near(dobInput));
        submitBtn.click();
    }
}
