package DriverListner;

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
        System.out.println(element + " is going to be clicked");
        // takeScreenshot("before_click");
    }

    @Override
    public void afterClick(WebElement element) {
        System.out.println(element + " was clicked");
        takeScreenshot("after_click");
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        takeScreenshot("On Error");
    }

    private void takeScreenshot(String phase) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileName = "screenshot_" + phase + "_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(screenshot, new File(fileName));
            System.out.println("Screenshot captured: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
