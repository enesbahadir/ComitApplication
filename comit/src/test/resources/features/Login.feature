Feature: Login Process Test Fature

  Scenario: Login process is successful with a valid username and valid password
    Given comit app login page, valid user with a valid username and valid password
      | username                                | password          |
      | Zaphod                                  | zaphod@galaxy.net |
    When the user fill login form
    Then login process should be successful

    Scenario: Login process is unsuccessful with a valid username and wrong password
      Given comit app login page, valid user with a valid username and wrong password
        | username                                | password          |
        | Zaphod                                  | zaphod@galaxy     |
      When the user fill login form with this user
      Then login process should be unsuccessful

  Scenario: Login process is unsuccessful with a wrong username and valid password
    Given comit app login page, valid user with a wrong username and valid password
      | username                                | password          |
      | Zaphod1                                  | zaphod@galaxy.net     |
    When the user fill login form with this wrong username and valid password
    Then login process with wrong username should be unsuccessful