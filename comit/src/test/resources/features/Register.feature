Feature: Register Test Feature

  Scenario: Registration is successful with a valid username and valid password
    Given comit app register page
    When the user fill register form, User object is posted
    Then Registration process should be successful
