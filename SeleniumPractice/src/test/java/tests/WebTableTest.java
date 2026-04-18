package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.TestBase;

import java.util.List;

public class WebTableTest extends TestBase {

    @Test
    public void scrollToTableTest() {
        commonActions().goTo("https://rahulshettyacademy.com/AutomationPractice/");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        commonActions().scrollToElement(By.cssSelector(".tableFixHead"));
        List<WebElement> amountList = commonActions().findElements(By.xpath("//div[@class='tableFixHead'] //tr/td[4]"));
        javascriptExecutor.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
        int sum = 0;
        for (WebElement amount : amountList) {
            sum += Integer.parseInt(amount.getText());
        }
        commonActions().assertEquals(sum, 296, "Verify sum of all amounts");
    }
}
