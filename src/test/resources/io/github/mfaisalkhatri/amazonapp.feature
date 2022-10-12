@android
Feature: Search product and add to cart

  Scenario: Search for the product
    Given I search for "Samsung Galaxy S20" Mobile
    When I click on first search result
    And I add Samsung Galaxy S20 mobile to cart
    And I click on Cart button
    Then I should be able to see Samsung Galaxy S20 mobile phone details and its price