Feature: Product CRUD Test Feature

  Scenario: Admin type user should add new prodcut
    Given comit app prodcut add form page
    When user fill product add form, new product is posted
    Then adding process should be successful

 Scenario: Admin type user should edit a product
   Given comit app prodcut edit form page
   When user fill product edit form, edited product is posted
     | name                    | description                   | price          | picByte       |
     | mockProduct             | New Mock Description          | 250            | null          |
   Then editing process should be successful

 Scenario: Admin type user should delete a product
   Given comit app product delete page
   When user push delete button
   Then delete process should be successful

 Scenario: All users should see product list
   Given comit app shop page
   When user should see all products
   Then listing process should be successfull
