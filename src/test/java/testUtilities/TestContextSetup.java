package testUtilities;

import pageObjects.PageObjectManager;

public class TestContextSetup {
    public PageObjectManager pageObjectManager;
    public GenericUtils genericUtils;
    public TestBase testBase;

    public TestContextSetup() {
        testBase = new TestBase();
        pageObjectManager = new PageObjectManager(testBase.getDriver());
        genericUtils = new GenericUtils(testBase.getDriver());
    }
}
