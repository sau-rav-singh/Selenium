# Cucumber Framework

A Java-based test automation framework using Cucumber, Selenium WebDriver, and TestNG. This project demonstrates Behavior Driven Development (BDD) for web application testing.

## Features
- BDD with Cucumber and Gherkin syntax
- Selenium WebDriver for browser automation
- TestNG for test assertions
- Page Object Model for maintainable test code
- Global configuration via `global.properties`
- Extent Reports via `extent.properties` with Screenshot logs before click, after click and on error

## Project Structure
```
CucumberFramework/
├── pom.xml                # Maven dependencies and build config
├── testng.xml             # TestNG suite configuration
├── src/
│   └── test/
│       ├── java/
│       │   ├── pageObjects/      # Page Object classes
│       │   ├── stepDefinitions/  # Step definition files
│       │   ├── testRunner/       # Cucumber runner and hooks
│       │   └── testUtils/        # Utility classes
│       └── resources/
│           ├── features/         # Cucumber feature files
|           ├── global.properties # Global config
|           ├── extent.properties # Extent Report config
```

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven

### Setup
1. Clone the repository:
   ```
   git clone https://github.com/sau-rav-singh/Selenium.git
   ```
2. Navigate to the project directory:
   ```
   cd CucumberFramework
   ```
3. Install dependencies:
   ```
   mvn clean install
   ```

### Running Tests
- To run tests using TestNG:
  ```
  mvn test -DsuiteXmlFile=testng.xml
  ```
- To run Cucumber features:
  - Use the Cucumber runner class in `testRunner` package.

### Configuration
- Update `src/test/resources/global.properties` for environment-specific settings (e.g., browser, base URL).

### Reporting
- Extent reports are generated in the `ExtentReports/SparkReports_Timestamp` directory after test execution.

## Project Notes

### 1. TestContextSetup
The Core of State Management -The TestContextSetup class is the backbone of this framework's test execution flow, acting as a Dependency Injection container. Its primary role is to create and share state between different step definition files within a single Cucumber scenario.
#### How it Initiates the Browser
When a new test scenario begins, Cucumber creates an instance of TestContextSetup. This triggers its constructor, which orchestrates the entire setup process:
1. **testBase** = new TestBase();: It first instantiates the TestBase class, which contains the low-level logic for browser and driver management.
2. **testBase.initializeDriver()**: It then calls the initializeDriver() method from the TestBase object. This is the key method that:
   - Reads the config.properties file to determine which browser to use.
   - Instantiates the correct WebDriver (e.g., ChromeDriver).
   - Applies implicit waits and maximizes the browser window.
   - Returns the fully configured WebDriver instance.
3. **Sharing the Driver**: The WebDriver instance returned by initializeDriver() is then passed to the constructors of PageObjectManager and GenericUtils, ensuring all parts of the framework use the same browser instance for the duration of the test.
### 2. PageObjectManager
The PageObjectManager class acts as a factory for creating and managing all Page Object instances within the test framework. It ensures that only a single instance of each page object is created per test scenario, promoting code reusability and efficient memory management.
### Key Responsibilities
1. **Centralized Instantiation**: Provides a single, central point of access for all page objects. Instead of creating new PageObject() in every step definition file, you can request it from the manager.
2. **Lazy Initialization**: Page objects are only created when they are first requested. This "on-demand" creation prevents unnecessary object instantiation, which can improve the performance of test startup.
3. **State Management**: By returning the same instance of a page object throughout a test scenario, it allows for state to be easily shared between different steps that interact with the same page.
### 3. Test Runner (TestNGTestRunner.java)
The TestNGTestRunner.java class is the primary entry point for executing Cucumber tests using the TestNG framework. It acts as a bridge, allowing TestNG to discover and run Cucumber features as if they were standard TestNG tests.This class uses the @CucumberOptions annotation to configure the test run and extends AbstractTestNGCucumberTests to integrate with TestNG's execution lifecycle.
### Key Responsibilities
1. **Test Configuration**: Defines where to find feature files (features), step definitions (glue), and which scenarios to run (tags).
2. **Reporting**: Configures output formats for test reports, such as HTML, JSON, and the ExtentReports adapter.
3. **Enabling Parallel Execution**: Enables scenarios to be run in parallel by leveraging TestNG's Data Provider mechanism.
### How Parallel Execution is Controlled
While the TestNGTestRunner.java file enables parallel execution by annotating the scenarios() method with @DataProvider(parallel = true), the number of parallel threads (and thus, the number of concurrent browsers) is controlled externally by the Maven Surefire Plugin in the pom.xml.

This separation of concerns allows you to change the degree of parallelism without modifying the test runner code.

The configuration in pom.xml looks like this:
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>${maven.surefire.plugin.version}</version>
    <configuration>
        <suiteXmlFiles>
            <suiteXmlFile>testng.xml</suiteXmlFile>
        </suiteXmlFiles>
        <properties>
            <property>
                <name>dataproviderthreadcount</name>
                <value>2</value>
            </property>
        </properties>
    </configuration>
</plugin>
```
### Explanation:
1. **@DataProvider(parallel = true)**: This annotation in TestNGTestRunner tells TestNG that the scenarios from Cucumber should be run using a parallel data provider.
2. **<name>dataproviderthreadcount</name>**: This property is passed by Maven Surefire directly to the TestNG runtime. It specifically sets the size of the thread pool for parallel data providers.
3. **value>2</value**: This value instructs TestNG to use 2 threads for the data provider. Consequently, a maximum of 2 Cucumber scenarios will run in parallel at any given time, each typically launching its own browser instance.

By adjusting the <value> in the pom.xml, you can easily scale the number of parallel test executions up or down to suit your needs.

### 4. Failed Test Re-run (`FailedTestRunner.java`)

This framework includes a dedicated test runner, `FailedTestRunner.java`, designed specifically to re-execute only the scenarios that failed during a previous test run.
This is a crucial feature for handling flaky tests and quickly verifying bug fixes without needing to run the entire test suite again.

---

## Purpose

- **Efficiency**: Saves significant time by isolating and re-running only the failed tests.
- **CI/CD Integration**: Can be integrated into a CI/CD pipeline as a separate stage that runs only if the main test stage has failures.
- **Flaky Test Management**: Helps determine if a test failure was a one-time "flaky" issue or a consistent, reproducible bug.

---

## How It Works

The `FailedTestRunner` works by reading a special text file that contains the exact location of each failed scenario.

### 1. Generating the Failure File

The main `TestNGTestRunner` must be configured with the **rerun plugin**. This plugin automatically creates a file (e.g., `rerun:target/failed_scenarios.txt`) and logs the path to any scenario that fails.

### 2. Reading the Failure File

The `FailedTestRunner` is configured with:

```java
@CucumberOptions(features = "@target/failed_scenarios.txt")
```

### 5. Event Handling with `DriverListener`

The `DriverListener.java` class is a custom implementation of Selenium's `WebDriverListener` interface.
It's designed to **"listen" for specific events** during a test run—such as clicks, navigation, or errors—and execute custom code when those events occur.

Its primary role in this framework is to provide **automatic screenshot capture** at critical points, which significantly improves the traceability and debuggability of tests.

---

## Key Responsibilities

- **Automated Screenshots**: Automatically takes a screenshot **before and after** a click action on any `WebElement`.
- **Error Capture**: Instantly captures a screenshot the moment any WebDriver command fails, providing a clear visual of the application's state at the point of error.
- **Enhanced Reporting**: Embeds these screenshots directly into the **ExtentReport**, linking them to the specific test step where the event occurred. This creates a **rich, visual log** of the test execution.

---

## How It Works

1. **Implementation**
   The class implements the `WebDriverListener` interface.

2. **Event Overriding**
   It overrides methods corresponding to WebDriver events, such as `beforeClick`, `afterClick`, and `onError`.

3. **Screenshot Logic**
   Inside these overridden methods, it calls a helper method `takeScreenshot()` which:
   - Uses Selenium's `TakesScreenshot` interface to capture the current browser viewport.
   - Saves the screenshot as a `.png` file with a unique timestamp to avoid overwrites.
   - Uses `ExtentCucumberAdapter` to log the event and attach the saved screenshot file to the current step in the HTML report.

---

## Integration

This listener is **not used automatically**. It must be registered with the `WebDriver` instance when it is created.
This is typically done in a base class or context setup file using Selenium's `EventFiringDecorator`.

### Example: Registering the Listener

```java
// In your WebDriver setup class (e.g., TestBase or TestContextSetup)
WebDriver originalDriver = new ChromeDriver();
WebDriverListener listener = new DriverListener(originalDriver);

// Wrap the original driver with the listener
WebDriver decoratedDriver = new EventFiringDecorator(listener).decorate(originalDriver);

// Use 'decoratedDriver' for all your tests
this.driver = decoratedDriver;
```

By using the decoratedDriver instance throughout the framework, all subsequent actions will be intercepted by the DriverListener, enabling the automated logging and screenshot functionality.

### 6. WebDriver Management (`TestBase.java`)

The `TestBase` class is the foundational component of this framework, responsible for the entire lifecycle of the WebDriver instance.
It is engineered to handle driver creation, configuration, and cleanup in a robust and thread-safe manner, which is essential for running tests in parallel.

---

### Key Responsibilities

- **Thread-Safe WebDriver Instantiation**
  Utilizes `ThreadLocal` to ensure that each test execution thread gets its own isolated WebDriver instance.
  This is the cornerstone of preventing state conflicts and errors during parallel test runs.

- **Environment-Based Configuration**
  Reads connection details (`URL`) and browser choice from a `global.properties` file.
  It can dynamically switch configurations based on a Maven system property:

  ```
  -Denv=qa   # or staging / prod

  ```
- **Driver Initialization**: Encapsulates all browser setup logic, including Applying browser-specific options (like disabling notifications in Edge),
Setting implicit waits and Maximizing the browser window.
- **Event Listener Registration**:
Automatically wraps the WebDriver instance with an EventFiringDecorator to attach the DriverListener.
This transparently enables features like automatic screenshotting on clicks and errors for all tests.

- **Clean Driver Teardown**:
Provides a mechanism to properly clean up WebDriver instances and remove them from the ThreadLocal pool after a test is complete, preventing memory leaks.

### Usage in ```Cucumber Hooks```
The TestBase methods are designed to be called from a Cucumber Hooks class (@Before and @After annotations) to manage the browser lifecycle for each scenario.Example (Hooks.java):
```public class Hooks {
    TestContextSetup testContextSetup;

    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @Before
    public void beforeScenario() throws IOException {
        // Initialize the driver before each scenario
        testContextSetup.testBase.initializeDriver();
    }

    @After
    public void afterScenario() {
        // Quit the driver and clean up the thread after each scenario
        WebDriver driver = testContextSetup.testBase.getDriver();
        if (driver != null) {
            driver.quit();
        }
        TestBase.clearDriver();
    }
}
```