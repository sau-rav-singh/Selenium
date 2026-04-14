package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.TestBase;

public class FramesTest extends TestBase {

    @Test
    public void framesTest() {
        commonActions().goTo("https://jqueryui.com/droppable/");
        commonActions().switchToFrame(By.tagName("iframe"));
        commonActions().dragAndDrop(
                By.id("draggable"), 
                By.id("droppable")
        );
        commonActions().assertEquals(
                commonActions().getText(By.xpath("//div[@id='droppable']/p")), 
                "dropped!", 
                "Verify drag and drop success message"
        );
        commonActions().switchToDefaultContent();
    }
}
