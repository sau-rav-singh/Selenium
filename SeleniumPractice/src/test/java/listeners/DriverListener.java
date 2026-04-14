package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

public class DriverListener implements WebDriverListener {
    private final WebDriver driver;
    private final ExtentTest test;
    private String lastElementName;
    private String lastScreenshot;

    public DriverListener(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
    }

    private void setHighlight(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
            Thread.sleep(50); // Small sleep to ensure rendering
        } catch (Exception e) {
            // Ignore highlighting errors
        }
    }

    private void clearHighlight(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '');", element);
        } catch (Exception e) {
            // Ignore staleness or errors
        }
    }

    private String captureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    private void logWithScreenshot(Status status, String message) {
        test.log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
    }

    private String getElementName(WebElement element) {
        try {
            String name = element.getAttribute("id");
            if (name == null || name.isEmpty()) name = element.getAttribute("name");
            if (name == null || name.isEmpty()) name = element.getText();
            if (name.isEmpty()) name = element.getAttribute("placeholder");
            return (name != null && !name.isEmpty()) ? name : element.toString();
        } catch (Exception e) {
            return "Unknown Element";
        }
    }

    @Override
    public void beforeClick(WebElement element) {
        lastElementName = getElementName(element);
        setHighlight(element);
        lastScreenshot = captureScreenshot();
    }

    @Override
    public void afterClick(WebElement element) {
        test.log(Status.PASS, "Clicked on element: " + lastElementName, MediaEntityBuilder.createScreenCaptureFromBase64String(lastScreenshot).build());
        clearHighlight(element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keys) {
        lastElementName = getElementName(element);
        setHighlight(element);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keys) {
        logWithScreenshot(Status.PASS, "Sent text to element: " + lastElementName);
        clearHighlight(element);
    }

    @Override
    public void beforeGetText(WebElement element) {
        lastElementName = getElementName(element);
        setHighlight(element);
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        logWithScreenshot(Status.INFO, "Retrieved text '" + result + "' from element: " + lastElementName);
        clearHighlight(element);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logWithScreenshot(Status.PASS, "Navigated to URL: " + url);
    }
}
