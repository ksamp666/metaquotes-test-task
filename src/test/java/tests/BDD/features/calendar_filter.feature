Feature: MetaQuites Test Task

  Scenario: Calendar filter
    Given Calendar page is opened
    When User closes cookies notification panel
    And User sets period filter to current month
    And User sets all importance checkboxes to false except "Medium"
    And User sets all currency checkboxes to false except "CHF"
    And User waits for events table to be updated
    And User clicks the first item in table with currency "CHF"
    Then Event importance should be set to "Medium"
    And Event country should be set to "Switzerland"
    And Event history for last year should be printed to log
    And Short url for event page should be generated and printed to log
