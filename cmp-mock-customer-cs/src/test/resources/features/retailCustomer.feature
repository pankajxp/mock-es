Feature: Retail Customer API (CS)

  Background:
    * url 'http://localhost:8071/cs/customer'

  Scenario Outline: Retrieve customers

    Given path '/<customerID>'
    When method get
    Then status 200
    And match $ == {csCustomerID: <customerID>, csName: '<name>', csIsSenior: <isSenior>}

    Examples:
      | customerID | name    | isSenior |
      | 1          | Humaira | false    |
      | 2          | Pankaj  | false    |
      | 3          | John    | true     |

  Scenario: Add a new customer
    Given path ''
    And request {csName: 'karate', csIsSenior: true}
    When method post
    Then status 200
    And match $ == {csCustomerID: 4, csName: karate, csIsSenior: true}

  Scenario: Retrieve a customer which doesn't exist
    Given path '/10'
    When method get
    Then status 404
    And match $ == "Customer Not Found"