Feature: Shared State Example

  @DI
  Scenario: TS_01 Sharing state between steps
    Given I set the important data to "expectedData" in step definition 1
    Then I should have access to the important data in step definition 2

  @DI
  Scenario: TS_02 Sharing state between Scenario
    Then I should have access to the important data in Feature 1