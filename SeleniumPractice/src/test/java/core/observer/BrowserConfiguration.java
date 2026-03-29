package core.observer;

import core.Browser;
import lombok.Getter;

@Getter
public class BrowserConfiguration {
    private final Browser browser;
    private final BrowserBehavior browserBehavior;

    public BrowserConfiguration(Browser browser, BrowserBehavior browserBehavior) {
        this.browser = browser;
        this.browserBehavior = browserBehavior;
    }

}
