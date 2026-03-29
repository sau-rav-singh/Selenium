package core.observer;

public enum BrowserBehavior {
    NOT_SET,
    REUSE_IF_STARTED,       // Reuse existing browser instance
    RESTART_EVERY_TIME,     // Close and reopen before every test
    RESTART_ON_FAIL,        // Only restart if the previous test failed
}
