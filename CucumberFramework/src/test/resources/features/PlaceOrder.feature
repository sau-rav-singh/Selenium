Feature: Place the order

  @PlaceOrder
  Scenario Outline: Search the product and place the order
    Given User is on GreenCart Landing page
    When User searches with Shortname <Name> and extracts actual name of product
    And Added "3" items of the selected product to cart
    Then User proceeds to Checkout and validate the <Name> items in checkout page
    And verify user has ability to enter promo code and place the order

    Examples:
      | Name |
      | Tom  |