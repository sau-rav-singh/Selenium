# CucumberFramework

A Java-based test automation framework using Cucumber, Selenium WebDriver, and TestNG. This project demonstrates Behavior Driven Development (BDD) for web application testing.

## Features
- BDD with Cucumber and Gherkin syntax
- Selenium WebDriver for browser automation
- TestNG for test execution and reporting
- Page Object Model for maintainable test code
- Global configuration via `global.properties`

## Project Structure
```
CucumberFramework/
├── pom.xml                # Maven dependencies and build config
├── testng.xml             # TestNG suite configuration
├── src/
│   └── test/
│       ├── java/
│       │   ├── cucumberOptions/      # Cucumber runner and hooks
│       │   ├── pageObjects/          # Page Object classes
│       │   ├── stepDefinitions/      # Step definition files
│       │   └── TestUtils/            # Utility classes
│       └── resources/
│           ├── global.properties     # Global config
│           └── features/             # Cucumber feature files
```

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven
- ChromeDriver/GeckoDriver (as required)

### Setup
1. Clone the repository:
   ```
   git clone <repo-url>
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
  - Use the Cucumber runner class in `cucumberOptions` package.

### Configuration
- Update `src/test/resources/global.properties` for environment-specific settings (e.g., browser, base URL).

## Reporting
- TestNG and Cucumber reports are generated in the `target/` directory after test execution.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License.

