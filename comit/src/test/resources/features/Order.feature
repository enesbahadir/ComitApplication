Feature: Order CRUD process test feature
  Scenario: An order should be added successfully
    Given comit app check out page
    When user select products and push Complete Order button
    Then An order is successfully added

    Scenario: Admin type user should see all orders
      Given comit app orders page
      When admin type user push orders, open orders page
      Then admin type user should see all orders

      Scenario: User type user should see his orders
        Given comit app orders page with user type user
        When user type user push orders, open orders page
        Then user type user should see his orders
