package TestUtils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class GenericUtils {

    WebDriver driver;
    String currentWindowHandle;
    public GenericUtils(WebDriver driver){
        this.driver=driver;
    }
    public void switchToOffersPage() {
        currentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    public void switchToMainPage(){
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }
}
