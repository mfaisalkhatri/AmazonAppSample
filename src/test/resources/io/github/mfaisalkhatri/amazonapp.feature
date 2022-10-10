Feature: Search product and add to cart

  Scenario: Search for the product
    Given I search for "Samsung Galaxy S20" Mobile
    When I click on first search result
    Then I should be able to see "Samsung Galaxy S20" mobile phone details and its price

  Scenario: Add product to the cart
    Given I add "Samsung Galaxy S20" mobile to cart
    When I click on "Cart" button
    Then I should be able to see "Samsung Galaxy S20" mobile phone details with its price
