
Feature: API tests for the Weather Service
  I want to test all the API for Weather Sercie

  Scenario: Test the Forecase API
    When I invoke the forecast API
    Then I validate the api response is successful
    And I validate the timezone