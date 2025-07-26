# PortfolioTracker

A Java-based automation project for tracking mutual fund and index benchmarks using Selenium WebDriver, Cucumber (BDD), and TestNG. This project is designed to automate the process of capturing fund returns, category averages, and benchmark values from financial websites, and to send alerts based on configurable thresholds.

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Setup](#setup)
- [Running Tests](#running-tests)
- [Configuration](#configuration)
- [Reporting](#reporting)
- [Core Components](#core-components)
  - [TestContextSetup](#testcontextsetup)
  - [PageObjectManager](#pageobjectmanager)
- [Extending the Framework](#extending-the-framework)
- [Troubleshooting](#troubleshooting)
- [License](#license)

---

## Features

- **Automated Data Capture**: Extracts trailing and absolute returns, category averages, and benchmark values for mutual funds and indices.
- **Email Alerts**: Sends email notifications if index values breach specified thresholds.
- **BDD with Cucumber**: Write test scenarios in Gherkin for easy readability and collaboration.
- **Selenium WebDriver**: Automates browser interactions for data extraction.
- **TestNG Integration**: Supports assertions and parallel test execution.
- **Page Object Model**: Clean separation of page logic for maintainability.
- **Configurable**: Easily update browser, URLs, and thresholds via properties files.
- **Reporting**: Generates detailed test execution reports.

## Project Structure

```
PortfolioTracker/
├── pom.xml                        # Maven dependencies and build config
├── src/
│   └── test/
│       ├── java/
│       │   ├── customClasses/     # Custom classes (e.g., MutualFund)
│       │   ├── pageObjects/       # Page Object classes (e.g., MFBenchMarkPage, IndexAlertPage)
│       │   ├── stepDefinitions/   # Step definition files (Cucumber glue code)
│       │   ├── testRunner/        # Cucumber runner and hooks
│       │   └── testUtilities/     # Utility classes (e.g., TestContextSetup)
│       └── resources/
│           ├── features/          # Cucumber feature files
│           ├── global.properties  # Global config (browser, URLs, etc.)
│           └── extent.properties  # Extent Report config
```

## Getting Started

### Prerequisites

- Java 8 or higher (Java 21+ recommended)
### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/sau-rav-singh/Selenium.git
   cd PortfolioTracker
   ```

2. **Install dependencies:**
   ```sh
   mvn clean install
   ```

3. **Configure properties:**
   - Edit `src/test/resources/global.properties` to set browser, base URLs, email settings, and thresholds as needed.

## Running Tests

- **Run all tests with Maven:**
  ```sh
  mvn test
  ```

- **Run specific TestNG suite:**
  ```sh
  mvn test -DsuiteXmlFile=testng.xml
  ```

- **Run Cucumber features:**
  - Use the Cucumber runner class in the `testRunner` package, or configure via Maven Surefire plugin.

## Configuration

- **`global.properties`**: Set browser type, base URLs, email credentials, and alert thresholds.
- **`extent.properties`**: Configure Extent Report output and options.

## Reporting

- **Extent Reports**: After test execution, reports are generated in the `ExtentReports/SparkReports_<timestamp>` directory.
- **Logs**: Log4j is used for logging test execution details.

## Project Notes

### TestContextSetup

This class serves as the core dependency injection container for the entire test framework. Its primary role is to manage and share the state and common objects (like the WebDriver instance, Page Object Manager, and utility classes) across different steps of a single test scenario.

This approach, often called "Context Sharing" or "Dependency Injection," ensures that all components of a test (e.g., different step definition files in Cucumber) operate on the same browser instance and have access to the same set of helper objects.

#### Key Responsibilities:
1. **State Management**: It holds the single instance of the WebDriver, ensuring every part of the test uses the same browser session.
2. **Object Initialization**: In its constructor, it initializes all the essential helper and manager classes required for the tests to run.
3. **Centralized Access**: It provides a single, shared point of access for other classes (like step definitions) to get the objects they need.

#### How it Works
When an instance of TestContextSetup is created, its constructor performs the following actions:
1. **new TestBase()**: It creates an instance of TestBase, which is responsible for the low-level WebDriver setup, browser launch, and configuration.
2. **new PageObjectManager(...)**: It creates an instance of the PageObjectManager, passing it the WebDriver instance obtained from TestBase. The PageObjectManager is responsible for creating and providing instances of all page objects.
3. **new GenericUtils(...)**: It creates an instance of GenericUtils, also passing it the WebDriver instance. This class contains generic, reusable methods (like custom waits, screenshot utilities, etc.) that can be used across any page or test step.


### TestBase

### PageObjectManager

- Central factory for all Page Object classes.
- Ensures single instance per scenario (lazy initialization).
- Promotes code reuse and efficient memory management.

## Extending the Framework

- **Add new page objects**: Create a new class in `pageObjects/` and add a getter in `PageObjectManager`.
- **Add new step definitions**: Create a new class in `stepDefinitions/` and implement Cucumber steps.
- **Add new features**: Write `.feature` files in `src/test/resources/features/`.

## Troubleshooting

- **WebDriver errors**: Ensure browser drivers are compatible and available in PATH.
- **Email alerts not sent**: Check SMTP settings in `global.properties`.
- **Test failures**: Review Extent Reports and logs for detailed error messages.

## License

This project is licensed under the MIT License.

---

**Author:** [sau-rav-singh](https://github.com/sau-rav-singh)