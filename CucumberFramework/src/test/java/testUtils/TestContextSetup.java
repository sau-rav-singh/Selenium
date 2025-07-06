package testUtils;

import org.openqa.selenium.WebDriver;
import pageObjects.PageObjectManager;
import java.io.IOException;
import java.util.List;

public class TestContextSetup {

    public List<String> searchResults, productsOnDeal;
    public PageObjectManager pageObjectManager;
    public TestBase testBase;
    public GenericUtils genericUtils;

    public TestContextSetup() throws IOException {
        testBase = new TestBase();
        WebDriver driver = testBase.initializeDriver();
        pageObjectManager = new PageObjectManager(driver);
        genericUtils=new GenericUtils(driver);
    }
}
