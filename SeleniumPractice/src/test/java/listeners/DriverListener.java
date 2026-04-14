package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class DriverListener implements WebDriverListener {
    private final WebDriver driver;
    private final ExtentTest test;

    public DriverListener(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    private void highlight(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
            Thread.sleep(100);
            js.executeScript("arguments[0].setAttribute('style', '');", element);
        } catch (Exception e) {
            // Ignore highlighting errors
        }
    }

    private String captureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    private void logWithScreenshot(Status status, String message) {
        test.log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
    }

    @Override
    public void beforeClick(WebElement element) {
        highlight(element);
    }

    @Override
    public void afterClick(WebElement element) {
        logWithScreenshot(Status.PASS, "Clicked on element");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keys) {
        highlight(element);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keys) {
        logWithScreenshot(Status.PASS, "Sent text to element");
    }

    @Override
    public void beforeGetText(WebElement element) {
        highlight(element);
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        logWithScreenshot(Status.INFO, "Retrieved text '" + result + "' from element");
    }

    @Override
    public void afterGet(WebDriver driver, String url){
        logWithScreenshot(Status.PASS, "Navigated to URL: " + url);
    }
}