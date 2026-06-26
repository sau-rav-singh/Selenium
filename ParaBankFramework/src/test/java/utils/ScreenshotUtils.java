package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static String capture(WebDriver driver, String scenarioName) throws IOException {

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/reports/screenshots/" + scenarioName.replace(" ", "_") + ".png";

        FileUtils.copyFile(source, new File(destination));

        return destination;

    }

}