package RahulDP.PageComponents;

import RahulDP.Utilities.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationBar {
    By flights = By.cssSelector("[title=\"Flights\"]");
    WebElement sectionElement;

    public NavigationBar(WebDriver driver, By sectionElement) {
        this.sectionElement = driver.findElement(sectionElement);
    }

    public void getFlightAttribute() {
        System.out.println(ElementUtil.findElement(sectionElement, flights).getDomAttribute("class"));
    }

    public void getLinkCount() {
        System.out.println(ElementUtil.findElements(sectionElement,By.tagName("a")).size());
    }
}
