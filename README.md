# Selenium Automation Ecosystem

This repository is a comprehensive collection of Selenium-based automation projects, ranging from architectural design patterns and BDD frameworks to containerized execution and real-world financial tracking tools.

## Project Structure

The repository is organized into five distinct modules, each focusing on a specific aspect of modern test automation:

### 1. [DesignPatterns](./DesignPatterns)
A specialized module demonstrating the implementation of software design patterns in the context of Selenium automation.
- **Key Patterns**: Singleton, Factory, Strategy, Decorator, Builder, and more.
- **Tech Stack**: Java 21, Selenium 4, Cucumber, TestNG, Lombok.
- **Goal**: To build more maintainable, scalable, and reusable automation code by applying proven software engineering principles.

### 2. [SeleniumDocker](./SeleniumDocker)
Provides a containerized environment for consistent and scalable test execution.
- **Features**: Custom Docker image (`singhsaurav/seleniumdocker`) including JDK 21, Maven, Chrome, and ChromeDriver.
- **Utility**: Includes shell scripts (`upgrade.sh`) for maintaining and updating browser/driver versions within the image.
- **Goal**: Facilitate "Run Anywhere" capabilities and simplify CI/CD integration.

### 3. [PortfolioTracker](./PortfolioTracker)
A real-world automation utility designed to track financial instruments like mutual funds and market indices.
- **Features**: Automated data extraction from financial portals, threshold-based email alerts, and BDD-style test scenarios.
- **Architecture**: Implements a robust `TestContextSetup` for dependency injection and state management across Cucumber steps.
- **Goal**: Automate personal finance tracking and alerting using Selenium.

### 4. [SeleniumPractice](./SeleniumPractice)
A module focused on building a high-quality, SRP-compliant (Single Responsibility Principle) automation framework.
- **Architecture**: 
    - `DriverManager`: Thread-safe driver lifecycle management.
    - `DriverFactory`: Centralized browser configuration.
    - `ExtentManager`: Thread-local reporting state.
    - `DriverListener`: Invisible logging of all browser interactions.
- **Goal**: Demonstrate a production-grade framework architecture that handles parallel execution and detailed reporting gracefully.

### 5. [CucumberFramework](./CucumberFramework)
A classic BDD (Behavior Driven Development) framework implementation.
- **Features**: Feature files written in Gherkin, reusable step definitions, and integration with TestNG for execution.
- **Example Scenarios**: E-commerce workflows including Landing Page, Offers Page, and Checkout processes.
- **Goal**: Master BDD practices and Page Object Model (POM) orchestration within a Cucumber ecosystem.

---

## Tech Stack Summary

- **Language**: Java 21
- **Automation**: Selenium WebDriver 4.x
- **Testing Frameworks**: TestNG, Cucumber (BDD)
- **Build Tool**: Maven
- **Infrastructure**: Docker, Docker Compose
- **Reporting**: Extent Reports (Spark)
- **Utilities**: Lombok, Jackson (JSON), Rest-Assured (API), Commons-IO

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/sau-rav-singh/Selenium.git
   ```

2. **Prerequisites**:
   - JDK 21+
   - Maven 3.9+
   - Chrome Browser (or Docker for containerized runs)

3. **Running a Module**:
   Navigate to any module directory and run tests using Maven:
   ```bash
   cd PortfolioTracker
   mvn clean test
   ```

---
**Author**: [sau-rav-singh](https://github.com/sau-rav-singh)
