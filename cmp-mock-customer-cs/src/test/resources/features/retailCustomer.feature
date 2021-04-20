Feature: Retail Customer API (CS)

  Background:
    * url 'http://localhost:8071/cs/customer'

  Scenario: Retrieve one customer
    Given path '/1'
    When method get
    Then status 200

  Scenario: Add a new customer
    Given path ''
    And request {csName: 'karate', csIsSenior: true}
    When method post
    Then status 200