# ParaBank Framework

## Overview
ParaBank Framework is a hybrid test automation framework using Selenium WebDriver, Cucumber BDD, and TestNG for testing the ParaBank banking application. It's designed for maintainability, scalability, and parallel execution.

## Design Patterns Used

### 1. Page Object Model (POM)
- Separates page locators and interactions from test logic
- `LoginPage`, `RegisterPage`, `OverviewPage` extend `BasePage`
- Centralized element locators and reusable methods

### 2. Factory Pattern
- `DriverFactory` creates WebDriver instances based on browser configuration
- `TestDataFactory` generates dynamic test data using Faker

### 3. Singleton Pattern
- `ExtentManager` provides single ExtentReports instance
- ConfigReader loads configuration once via static initializer

### 4. Builder Pattern
- `TestDataFactory.UserRegistrationData` uses Lombok @Builder for fluent data creation

### 5. Dependency Injection
- Cucumber PicoContainer injects `TestContext` into step definitions and hooks
- Eliminates manual object passing between classes

### 6. ThreadLocal Pattern
- `DriverManager` uses ThreadLocal for thread-safe WebDriver instances
- `TestDataFactory` uses ThreadLocal Faker for parallel data generation

## Framework Components

### 1. Driver Layer
- `DriverManager`: ThreadLocal WebDriver management
- `DriverFactory`: Browser instance creation
- `BrowserOptionsFactory`: Browser-specific configurations

### 2. Page Object Layer
- `BasePage`: Common page interactions (click, type, wait)
- Specific pages: `LoginPage`, `RegisterPage`, `OpenAccountPage`, etc.

### 3. Flow Layer
- `RegisterFlow`, `LoginFlow`: Business logic orchestration
- Encapsulates multi-step operations

### 4. Manager Layer
- `PageObjectManager`: Lazy initialization of page objects
- `FlowManager`: Manages flow instances
- `ExtentTestManager`: Test reporting

### 5. Context Layer
- `TestContext`: Per-scenario shared state via DI
- `ScenarioContext`: Type-safe data storage with enum keys
- `ScenarioContextKey`: Centralized key definitions

### 6. Step Definition Layer
- `RegisterSteps`, `LoginSteps`: Cucumber step implementations
- Uses `TestContext` for data sharing across steps

### 7. Hooks Layer
- `DriverHook`: Setup/teardown for WebDriver and reporting
- Handles scenario context cleanup for parallel execution

### 8. Utilities
- `TestDataFactory`: Dynamic test data generation with Faker
- `ConfigReader`: Configuration management
- `CleanupManager`: Test data cleanup strategies

## How Components Work Together

### Test Execution Flow
1. **TestNG Runner** triggers Cucumber scenarios
2. **PicoContainer** creates `TestContext` per scenario
3. **DriverHook @Before** creates ThreadLocal WebDriver
4. **Step Definitions** receive injected `TestContext`
5. **Page Objects** interact with application via WebDriver
6. **Flow Layer** orchestrates business logic
7. **ScenarioContext** stores data across steps (account numbers, user data)
8. **DriverHook @After** cleans up context, thread-local resources, and quits driver

### Parallel Execution
- TestNG configured with `parallel="methods" thread-count="4"`
- ThreadLocal WebDriver ensures each thread has isolated browser
- ThreadLocal Faker prevents data collisions
- Per-scenario TestContext prevents cross-scenario data leakage
- UUID suffixes on usernames ensure uniqueness

### Data Flow
```
TestDataFactory → RegisterSteps → ScenarioContext → Other Steps
     ↓                    ↓                 ↓
Dynamic Data        Store Data        Retrieve Data
```

## Key Features

- **BDD with Cucumber**: Human-readable test scenarios
- **Parallel Execution**: Thread-safe design for faster test runs
- **Dynamic Data Generation**: Faker for realistic test data
- **Type-Safe Context**: Enum-based keys prevent runtime errors
- **Comprehensive Reporting**: ExtentReports integration
- **Cleanup Strategies**: Framework for API/DB cleanup
- **Configuration Management**: Externalized config via properties

## Project Structure

```
ParaBankFramework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── context/          # Context management
│   │   │   ├── flows/            # Business logic flows
│   │   │   ├── manager/          # Manager classes
│   │   │   ├── pages/            # Page objects
│   │   │   └── utils/            # Utilities (TestDataFactory)
│   └── test/
│       ├── java/
│       │   ├── context/          # Test context
│       │   ├── driver/           # WebDriver management
│       │   ├── hooks/            # Cucumber hooks
│       │   ├── listeners/        # TestNG listeners
│       │   ├── runner/           # Test runners
│       │   ├── stepdefinitions/  # Cucumber step definitions
│       │   └── utils/            # Test utilities
│       └── resources/
│           ├── config.properties # Configuration
│           └── features/         # Cucumber feature files
├── pom.xml
└── testng.xml
```

## Configuration

Edit `src/test/resources/config.properties` to configure:
- Base URL
- Browser selection
- Headless mode
- Download directory
- Page load strategy

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific tags
Edit `TestNGTestRunner.java` to change the `@tags` parameter:
```java
tags = "@Register",  // Run only registration tests
tags = "@Login",     // Run only login tests
```

### Parallel execution
Configure thread count in `testng.xml`:
```xml
<suite name="ParaBank Test Suite" parallel="methods" thread-count="4">
```

## Dependencies

- Selenium WebDriver 4.45.0
- Cucumber 7.15.0
- TestNG 7.12.0
- JavaFaker 1.0.2
- Lombok 1.18.46
- ExtentReports 5.1.2

## Test Data Generation

The framework uses JavaFaker for dynamic test data generation with ThreadLocal support for parallel execution:

```java
TestDataFactory.UserRegistrationData userData = 
    TestDataFactory.generateUserRegistrationData().withRepeatPassword();
```

Generated data includes:
- Unique usernames with UUID/timestamp suffixes
- Random passwords
- Realistic names, addresses, phone numbers
- Valid SSN numbers

## Cleanup Strategy

The framework includes `CleanupManager` for test data cleanup with support for:
- API cleanup (when available)
- Database cleanup (when necessary)
- Scheduled purge jobs
- Collision prevention via unique identifiers

## Reporting

Tests generate ExtentReports in:
- HTML: `target/cucumber.html`
- JSON: `target/cucumber.json`

## Browser Support

- Chrome
- Firefox
- Edge
- Safari (macOS)

## Best Practices Implemented

- Thread-safe design for parallel execution
- Type-safe context management
- Lazy initialization of page objects
- Comprehensive error handling
- Proper resource cleanup
- Separation of concerns
- Reusable components
- Externalized configuration
