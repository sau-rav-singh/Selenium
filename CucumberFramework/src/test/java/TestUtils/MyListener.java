package TestUtils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyListener implements WebDriverListener {
    private final WebDriver driver;

    public MyListener(WebDriver driver) {
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

    public void takeScreenshot(String message) {
        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        ExtentCucumberAdapter.addTestStepLog("Screenshot");
        ExtentCucumberAdapter.getCurrentStep()
                .info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }
}
