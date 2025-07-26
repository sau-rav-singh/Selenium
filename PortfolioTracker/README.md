```markdown
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
│   ├── main/
│   │   └── java/
│   │       └── (core logic, if any)
│   └── test/
│       ├── java/
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
- Maven 3.6+
- Chrome or Firefox browser installed

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

## Core Components

### TestContextSetup

- Acts as a Dependency Injection container.
- Manages WebDriver lifecycle and shares state across step definitions.
- Instantiates `TestBase`, which reads configuration and initializes the browser.

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
```