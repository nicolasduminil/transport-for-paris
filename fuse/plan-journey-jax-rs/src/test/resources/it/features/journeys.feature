Feature: Testing the Journeys REST API
  Users should be able to submit GET, POST, PUT and DELETE requests to the Journeys API in order to CRUD journeys

  Background:
    Given URI is initialized

  Scenario: Create a journey
    When user wants to create journey
    Then journey is successfully created

  Scenario: Get journeys
    When user wants to get journeys list
    Then journeys list is returned

  Scenario: Get a journey
    When user wants to get journey associated with name
    Then journey is returned

  Scenario: Update a journey
    When user wants to update journey
    Then journey is updated

  Scenario: Remove a journey
    When user wants to remove journey
    Then journey is removed
