Feature: Login to ParaBank

  @Login
  Scenario Outline: Login to the website
    Given User in on ParaBank HomePage
    When User logs into Parabank with username <username> and password <password>
    Then accounts overview page should be displayed
    Examples:
      | username | password |
      | admin    | admin    |