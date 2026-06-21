package context;

import driver.DriverManager;
import lombok.Getter;
import manager.FlowManager;
import manager.PageObjectManager;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class TestContext {
    private PageObjectManager pageObjectManager;
    private FlowManager flowManager;
    @Getter
    private final ScenarioContext scenarioContext;

    public TestContext() {
        this.scenarioContext = new ScenarioContext();
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public void navigateToHomePage() {
        getDriver().get(ConfigReader.getBaseUrl());
    }

    public PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            WebDriver driver = getDriver();
            pageObjectManager = new PageObjectManager(driver);
        }
        return pageObjectManager;
    }

    public FlowManager getFlowManager() {
        if (flowManager == null) {
            flowManager = new FlowManager(getPageObjectManager());
        }
        return flowManager;
    }

}
