package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import java.util.List;

public class ReportingDecorator implements Actions {
    private final Actions decorated;
    private final ExtentTest test;
    private final WebDriver driver;

    public ReportingDecorator(Actions decorated, ExtentTest test, WebDriver driver) {
        this.decorated = decorated;
        this.test = test;
        this.driver = driver;
    }

    private String captureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    @Override
    public void click(By locator) {
        try {
            decorated.click(locator);
            test.log(Status.PASS, "Clicked on element: " + locator);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click on element: " + locator + ". Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public void click(WebElement element) {
        try {
            decorated.click(element);
            test.log(Status.PASS, "Clicked on element: " + element.toString());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click on element: " + element.toString() + ". Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public void sendText(By locator, String text) {
        try {
            decorated.sendText(locator, text);
            test.log(Status.PASS, "Sent text '" + text + "' to element: " + locator);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to send text to element: " + locator + ". Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public String getText(By locator) {
        try {
            String text = decorated.getText(locator);
            test.log(Status.INFO, "Retrieved text '" + text + "' from element: " + locator,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            return text;
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to get text from element: " + locator + ". Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public void selectByVisibleText(By locator, String text) {
        try {
            decorated.selectByVisibleText(locator, text);
            test.log(Status.INFO, "Selected by visible text '" + text + "' from dropdown: " + locator,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to select by visible text from dropdown: " + locator + ". Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public void selectByIndex(By locator, int index) {
        try {
            decorated.selectByIndex(locator, index);
            test.log(Status.INFO, "Selected by index '" + index + "' from dropdown: " + locator,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to select by index from dropdown: " + locator + ". Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public void selectByValue(By locator, String value) {
        try {
            decorated.selectByValue(locator, value);
            test.log(Status.INFO, "Selected by value '" + value + "' from dropdown: " + locator,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to select by value from dropdown: " + locator + ". Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public String getFirstSelectedOption(By locator) {
        try {
            String selectedOption = decorated.getFirstSelectedOption(locator);
            test.log(Status.INFO, "Retrieved first selected option '" + selectedOption + "' from dropdown: " + locator,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            return selectedOption;
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to get first selected option from dropdown: " + locator + ". Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public void assertEquals(Object actual, Object expected, String message) {
        try {
            decorated.assertEquals(actual, expected, message);
            test.log(Status.PASS, message + " - Expected: [" + expected + "] Actual: [" + actual + "]");
        } catch (AssertionError e) {
            test.log(Status.FAIL, message + " - Expected: [" + expected + "] Actual: [" + actual + "] - Error: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
            throw e;
        }
    }

    @Override
    public List<WebElement> findElements(By locator) {
        return decorated.findElements(locator);
    }
}
