Feature: Search for Products

  @SearchProduct
  Scenario Outline: Search for product on HomePage and OffersPage
    Given User is on GreenCart Landing page
    When User searches with Shortname <Name> and extracts actual name of product
    Then User searches for <Name> shortname in offers page
    And  Validate that product name in offers page matches with Landing Page

    Examples:
      | Name   |
      | Tom    |
      | Carrot |
      | Potato |