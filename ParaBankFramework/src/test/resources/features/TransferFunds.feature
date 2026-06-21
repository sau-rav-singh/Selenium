Feature: Transfer funds between accounts

  @Transfer
  Scenario Outline: Fund Transfer between existing Accounts
    Given User in on ParaBank HomePage
    When User logs into Parabank with username <username> and password <password>
    When User navigates to transfer funds page
    When User enters amount as<amount> from account as<fromAccount> and to account as<toAccount>
    Then Transfer should get completed
    Examples:
      | amount | fromAccount | toAccount | username | password |
      | 2      | 34545       | 34545     | admin    | admin    |

  @NewAccountTransfer
  Scenario Outline: Open a new account and transfer funds to it
    Given User in on ParaBank HomePage
    When User logs into Parabank with username <username> and password <password>
    And User navigates to Open New Account link
    And User selects accountType as <accountType> and base account as <baseAccount>
    Then The new account number should be displayed
    When User navigates to transfer funds page
    When User enters amount as<amount> from baseAccount as<baseAccount> and to new account
    Then Transfer should get completed
    Examples:
      | username | password | accountType | baseAccount | amount |
      | admin    | admin    | SAVINGS     | 34545       | 2      |