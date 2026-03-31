# Test Automation Framework Architecture

This document provides a high-level overview of the Selenium-Java test automation framework to help new team members understand its structure and execution flow.

## 🏗️ Framework Structure

The framework is built using **Java**, **Selenium WebDriver**, **TestNG**, and **Maven**. It follows a modular design for scalability and maintainability.

### Key Components:

1.  **`base/TestBase.java`**:
    *   The core configuration class that all test classes must extend.
    *   Handles the lifecycle of tests using TestNG annotations (`@BeforeSuite`, `@BeforeMethod`, `@AfterMethod`, `@AfterSuite`).
    *   Initializes reporting and driver setup.

2.  **`utils/DriverManager.java`**:
    *   **Thread-Safe Management**: Uses `ThreadLocal` to ensure that each test run in parallel has its own isolated WebDriver instance.
    *   **Centralized Access**: Provides static methods to get the current driver, wait, and common actions.

3.  **`utils/CommonActions.java`**:
    *   A wrapper around standard Selenium commands (click, sendKeys, select, etc.).
    *   **Robustness**: Implements "Safe Actions" that automatically handle `StaleElementReferenceException` and wait for element visibility/clickability.
    *   **Logging**: Every UI interaction is automatically logged to the console via SLF4J.

4.  **`utils/ConfigReader.java` & `config.properties`**:
    *   **Unified Configuration**: Manages environment settings like `browser`, `headless`, `timeout`, and `baseUrl`.
    *   **CLI Overrides**: Allows overriding configurations via command line (e.g., `mvn test -Dbrowser=firefox`).

5.  **`utils/BrowserFactory.java`**:
    *   Responsible for instantiating the specific WebDriver (Chrome, Firefox, Edge) based on configuration.

6.  **`listeners/`**:
    *   **TestListener**: Captures test success/failure and logs them to ExtentReports.
    *   **DriverListener**: Uses Selenium's `EventFiringDecorator` to intercept driver events for automated logging and screenshots.

---

## 🔄 Execution Flow

When you run a test (e.g., `mvn test` or running a test method in IntelliJ):

1.  **Suite Setup**: `TestBase.setupSuite()` initializes the **ExtentReports** engine.
2.  **Method Setup**: Before each test method:
    *   `TestBase.setUp()` is called.
    *   `ConfigReader` fetches the browser and timeout settings.
    *   `DriverManager` requests a new driver from `BrowserFactory`.
    *   A `DriverListener` is attached to the driver for automated logging.
    *   The driver is stored in a `ThreadLocal` variable for the current thread.
3.  **Test Execution**:
    *   The test method executes logic.
    *   UI interactions are performed through `commonActions()`, which ensures elements are ready and logs every step.
4.  **Method Teardown**:
    *   `TestBase.tearDown()` calls `DriverManager.quitDriver()`.
    *   The browser is closed, and the `ThreadLocal` storage is cleared to prevent memory leaks.
5.  **Suite Teardown**: `TestBase.tearDownSuite()` flushes the ExtentReport to `target/ExtentReport.html`.

---

## 🚀 How to Add a New Test

1.  **Locators**: (Recommended) Create a Page Object class in `src/main/java/pageObjects`.
2.  **Test Class**: Create a class in `src/test/java/tests/` that extends `TestBase`.
3.  **Code**: Use `getDriver()` to navigate and `commonActions()` to interact with elements.

```java
public class MyNewTest extends TestBase {
    @Test
    public void exampleTest() {
        getDriver().get("https://example.com");
        commonActions().click(By.id("submit"));
        // Assertions...
    }
}
```

## 📊 Reporting & Logs
*   **HTML Report**: Found in `target/ExtentReport.html` after execution.
*   **Console Logs**: Real-time execution details are printed to the IDE/Terminal console.
