package TestUtils;

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
        pageObjectManager = new PageObjectManager(testBase.initializeDriver());
        genericUtils=new GenericUtils(testBase.initializeDriver());
    }
}
