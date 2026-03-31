# Selenium Automation Framework Architecture

This document describes the architectural design and code flow of the Selenium automation framework.

## Framework Components (SRP-Compliant)

### 1. `DriverFactory` (Creation Logic)
- **Role**: Solely responsible for instantiating the correct `WebDriver` instance.
- **Functionality**: Contains the logic for Chrome, Firefox, and Edge configurations, including headless modes and options.

### 2. `DriverManager` (Lifecycle & Thread-Safety)
- **Role**: Manages the `ThreadLocal` storage and lifecycle of the active `WebDriver` session.
- **Functionality**: 
  - `setDriver()`: Uses `DriverFactory` to create a driver, wraps it with listeners, and stores it in `ThreadLocal`.
  - `getDriver()`: Provides thread-safe access to the decorated driver.
  - `quitDriver()`: Safely closes the session and clears thread storage.

### 3. `ExtentManager` (Reporting State)
- **Role**: Dedicated manager for the `ThreadLocal<ExtentTest>` instance.
- **Functionality**: Provides a single point of access for logging from any class without needing to pass the `test` object around.

### 4. `ExtentReportListener` (Global Reporting Lifecycle)
- **Role**: Implements TestNG's `ISuiteListener`.
- **Functionality**:
  - `onStart()`: Initializes the `ExtentReports` engine and Spark Reporter before the suite begins.
  - `onFinish()`: Flushes the report after all tests complete.

### 5. `TestListener` (Test Event Logging)
- **Role**: Implements `ITestListener` to capture the outcome of individual test methods.
- **Functionality**: Automatically logs Pass, Fail (with screenshots), and Skip statuses to the `ExtentManager`.

### 6. `TestBase` (Test Orchestrator)
- **Role**: Acts as the base class for all test scripts.
- **Functionality**: 
  - Focuses strictly on the **test-level setup and teardown** (`@BeforeMethod` / `@AfterMethod`).
  - Orchestrates between `ExtentManager`, `DriverManager`, and `ConfigReader` to prepare the environment.

### 7. `DriverListener` (Selenium Event Decorator)
- **Role**: Implements `WebDriverListener` for "Invisible Logging."
- **Functionality**: Automatically intercepts clicks and inputs to highlight elements and log screenshots to the report.

## Refactored Code Flow

### 1. Suite Startup
- `ExtentReportListener.onStart()` runs. The `ExtentReports` engine is ready.

### 2. Test Setup (`@BeforeMethod`)
- `TestBase.setUp()` is called.
- It asks `ExtentReportListener` to create a new test node.
- It saves that node in `ExtentManager`.
- It asks `DriverManager` to initialize the browser. `DriverManager` uses `DriverFactory` to get the raw driver, then decorates it.

### 3. Execution
- Test scripts interact with the browser.
- `DriverListener` automatically logs every action to the `ExtentTest` stored in `ExtentManager`.

### 4. Test Completion
- `TestListener` logs the final result.
- `TestBase.tearDown()` runs. It calls `DriverManager.quitDriver()` and `ExtentManager.removeExtentTest()` to prevent memory leaks and thread cross-talk.

### 5. Suite Finish
- `ExtentReportListener.onFinish()` runs. The HTML report is generated.

## Benefits of this Architecture
- **Maintainability**: Each class has exactly one reason to change.
- **Scalability**: New browsers are added in `DriverFactory`; new reporting tools are added via new listeners.
- **Robustness**: Strict use of `ThreadLocal` across managers ensures zero interference during parallel execution.
- **Clean Tests**: Test scripts remain focused on business logic, with all infrastructure concerns handled by the base and listeners.
