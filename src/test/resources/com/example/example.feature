Feature: Calculator operations

  Scenario: Simple addition
    Given I have a calculator
    When I add 2 and 3
    Then the result should be 5

  Scenario Outline: Multiple additions
    Given I have a calculator
    When I add <a> and <b>
    Then the result should be <sum>
    Examples:
      | a | b | sum |
      | 1 | 2 | 3   |
      | 5 | 10| 15  |
      | -1| -2| -3  |
