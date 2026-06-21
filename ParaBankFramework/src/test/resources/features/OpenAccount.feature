Feature: Open a new account in ParaBank

  @OpenAccount
  Scenario Outline: Open a new account in ParaBank
    Given User in on ParaBank HomePage
    When User logs into Parabank with username <username> and password <password>
    And User navigates to Open New Account link
    And User selects accountType as <accountType> and base account as <baseAccount>
    Then The new account number should be displayed
    Examples:
      | username | password | accountType | baseAccount |
      | admin    | admin    | SAVINGS     | 34545       |