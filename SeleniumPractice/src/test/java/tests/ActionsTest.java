package tests;

import org.testng.annotations.Test;
import utils.TestBase;

public class ActionsTest extends TestBase {

    @Test
    public void amazonTest(){
        commonActions().goTo("https://www.amazon.in/");
    }
}
