package listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@SuppressWarnings("NullableProblems")
public class DriverListener implements WebDriverListener {

    private static final Logger logger = LoggerFactory.getLogger(DriverListener.class);

    @Override
    public void beforeClick(WebElement element) {
        logger.info("Before clicking on element: {}", element);
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("After clicking on element: {}", element);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        logger.info("Before finding element with locator: {}", locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        logger.info("After finding element with locator: {}", locator);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("Before sending keys to element: {}", Arrays.toString(keysToSend));
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("After sending keys to element: {}", Arrays.toString(keysToSend));
    }

}
