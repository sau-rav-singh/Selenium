Feature: User Registration

  @Register
  Scenario: Register a new user with dynamically generated test data
    Given User is on ParaBank HomePage
    When User navigates to registration page
    And User registers with dynamically generated test data
    Then Registration should be successful
    And User should be logged in with the new account

  @Register @Parallel
  Scenario Outline: Register multiple users in parallel with unique data
    Given User is on ParaBank HomePage
    When User navigates to registration page
    And User registers with dynamically generated test data for test run <testRun>
    Then Registration should be successful
    And User should be logged in with the new account
    Examples:
      | testRun |
      | 1       |
      | 2       |
      | 3       |
      | 4       |
