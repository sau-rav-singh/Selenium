Feature: Search and Place the order for Products

  @OffersPage
  Scenario Outline: Search Experience for product search in both home and Offers page
    Given User is on GreenCart Landing page
    When User searches with Shortname <Name> and extracts actual name of product
    Then User searches for <Name> shortname in offers page
    And  Validate that product name in offers page matches with Landing Page

    Examples:
      | Name   |
      | Tom    |
      | Carrot |
      | Potato |