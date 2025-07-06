package RahulDP.PageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static RahulDP.Utilities.ElementUtil.findElement;
import static RahulDP.Utilities.ElementUtil.findElements;

public class FooterNavigation {
    By flights = By.cssSelector("[title=\"Flights\"]");
    WebElement sectionElement;

    public FooterNavigation(WebDriver driver, By sectionElement) {
        this.sectionElement = driver.findElement(sectionElement);
    }

    public void getFlightAttribute() {
        System.out.println(findElement(sectionElement,flights).getDomAttribute("class"));
    }

    public void getLinkCount() {
        System.out.println(findElements(sectionElement,By.tagName("a")).size());
    }
}
