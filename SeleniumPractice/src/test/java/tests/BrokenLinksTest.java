package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestBase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class BrokenLinksTest extends TestBase {

    @Test
    public void validateBrokenLinksTest() throws IOException, URISyntaxException {
        commonActions().goTo("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> links = commonActions().findElements(By.cssSelector("li[class='gf-li'] a"));
        for (WebElement link : links) {
            String urlString = link.getAttribute("href");
            Assert.assertNotNull(urlString);
            HttpURLConnection conn = (HttpURLConnection) new URI(urlString).toURL().openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int responseCode = conn.getResponseCode();
            String linkText = link.getText();
            if (responseCode > 400) {
                System.out.println("The link with text '" + linkText + "' is broken with code " + responseCode + " (URL: " + urlString + ")");
            }
        }
    }
}
