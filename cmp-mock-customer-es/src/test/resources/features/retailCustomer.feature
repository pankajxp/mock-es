Feature: Retail Customer API (CS)

  Background:
    * url 'http://localhost:8070/es/customer'

  Scenario: Retrieve one customer
    Given path '/1'
    When method get
    Then status 200

  Scenario: Add a new customer
    Given path ''
    And request {esName: 'karate', esIsSenior: true}
    When method post
    Then status 201