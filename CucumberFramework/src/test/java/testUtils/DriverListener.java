package testUtils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DriverListener implements WebDriverListener {
    private final WebDriver driver;

    public DriverListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void beforeGetTitle(WebDriver driver) {
        System.out.println("Getting the title");
    }

    @Override
    public void beforeClick(WebElement element) {
        takeScreenshot("before_click");
    }

    @Override
    public void afterClick(WebElement element) {
        takeScreenshot("after_click");
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        takeScreenshot("On Error");
    }

//    public void takeScreenshot(String message) {
//        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//        ExtentCucumberAdapter.addTestStepLog("Screenshot");
//        ExtentCucumberAdapter.getCurrentStep()
//                .info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//    }

    public void takeScreenshot(String message) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date());
            String screenshotName = "screenshot_" + timestamp + ".png";
            String destPath = System.getProperty("user.dir") + "/ExtentReports/screenshots/" + screenshotName;
            FileUtils.copyFile(screenshot, new File(destPath));
            ExtentCucumberAdapter.addTestStepLog("Screenshot:");
            ExtentCucumberAdapter.getCurrentStep().info(message, MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}