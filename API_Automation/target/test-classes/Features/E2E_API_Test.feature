#Title				:		API TESTING
#Description	:		API Testing with GET, POST, PUT and DELETE Method
#Author				:		Sanket B
#Date					: 	14052025
Feature: Execute E2E Functionality for API Testing
  Description: Verify Functionality with GET, POST, PUT and DELETE

  @smoketest
  Scenario Outline: Verify GET for Product Data
    Given I have API Testing with ID "<id>" for "<URL>"
    When I get the Product Information
    Then I validate the status code "<statuscode>"
    And I validate product Id and Description

    Examples: 
      | URL                                 | id   | statuscode |
      | https://abc.tmc.gov.in/product_app/ | view |        200 |

  @smoketest
  Scenario Outline: Verify  POST for Product Data
    Given I have base "<URL>" URL
    When I will add new Product "<productName>"  "<productType>" "<description>" "<price>"
    Then I validate the status code "<statuscode>"
    
    Examples: 
      | URL                                 | statuscode | productName | productType | description  | price |
      | https://abc.tmc.gov.in/product_app/ |        200 | One Plus    | Tablet      | One Plus Tab | 50000 |