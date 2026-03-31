package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverManager;
import utils.ExtentManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Test started - can be logged if needed
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Test passed successfully
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            test.log(Status.PASS, "Test passed successfully");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            // Log the failure message
            Throwable throwable = result.getThrowable();
            test.log(Status.FAIL, "Test failed with exception: " + throwable.getMessage());

            // Try to capture screenshot
            try {
                WebDriver driver = DriverManager.getDriver();
                if (driver instanceof TakesScreenshot) {
                    String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                    test.log(Status.FAIL, "Screenshot on failure",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
                }
            } catch (Exception e) {
                test.log(Status.FAIL, "Could not capture screenshot: " + e.getMessage());
            }

            // Log the stack trace
            test.log(Status.FAIL, throwable);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentManager.getExtentTest();
        if (test != null) {
            String skipReason = "Test skipped";
            Throwable throwable = result.getThrowable();
            if (throwable != null) {
                skipReason += ": " + throwable.getMessage();
            }
            test.log(Status.SKIP, skipReason);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not commonly used, but can be implemented if needed
    }

    @Override
    public void onStart(ITestContext context) {
        // Test suite started
    }

    @Override
    public void onFinish(ITestContext context) {
        // Test suite finished
    }
}
