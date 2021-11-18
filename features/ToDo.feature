
Feature: Test ToDo feature
  I want to be able to add a ToDo, Complete and Delete it.

  Scenario: Add a ToDo
    Given I have already on the ToDo Page
    When I enter a ToDo name in search box and press enter
    Then I validate the ToDo created in the list
   
  Scenario: Coomplete a ToDo
    Given I have already on the ToDo Page
    When I enter a ToDo name in search box and press enter
    And I click on the ToDo checkbox to complete it
    Then I validate the ToDo is completed
   
  Scenario: Delete a ToDo
    Given I have already on the ToDo Page
    When I enter a ToDo name in search box and press enter
    And I mouse over on the ToDo
    And I click on the Cancel button
    Then I validate the ToDo is deleted from the list
