package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.TestBase;

import java.util.ArrayList;
import java.util.List;

public class LinksTest extends TestBase {
    By footerLinksLocator = By.cssSelector("table.gf-t td:nth-child(1) ul li a");

    @Test
    public void clickAllLinksTest() {
        commonActions().goTo("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> discountLinks = commonActions().findElements(footerLinksLocator);

        List<String> allLinks = discountLinks.stream().map(link -> link.getAttribute("href")).filter(href -> href != null && !href.isEmpty()).toList();

        for (String link : allLinks) {
            commonActions().goTo(link);
            String pageTitle = getDriver().getTitle();
            commonActions().assertEquals(pageTitle != null && !pageTitle.isEmpty(), true, "Verify page loaded for link: " + link + " (Title: " + pageTitle + ")");
        }
    }

    @Test
    public void clickLinksInNewTabsTest() {
        commonActions().goTo("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> discountLinks = commonActions().findElements(footerLinksLocator);
        for (WebElement link : discountLinks) {
            link.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        }

        List<String> handlesList = new ArrayList<>(getDriver().getWindowHandles());
        for (String handle : handlesList) {
            getDriver().switchTo().window(handle);
            String title = getDriver().getTitle();
            commonActions().assertEquals(title != null && !title.isEmpty(), true, "Verify title of opened tab: " + title);
        }
    }
}
