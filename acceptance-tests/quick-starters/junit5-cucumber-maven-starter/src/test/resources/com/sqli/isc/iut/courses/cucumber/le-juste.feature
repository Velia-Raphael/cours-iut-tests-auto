Feature: Le Juste Cocktail Bar Management
  As the owner of Le Juste bar
  I want to manage seating and orders
  So that I can ensure the smooth operation of my establishment

  Background:
    Given Le Juste is a cocktail bar
    And the bar has only 10 seats

  Scenario: Entry refused when bar is full
    Given there are 9 people in the bar
    When Mr Pignon and Mr Leblanc arrive all at the bar
    Then they are denied entry
    And the bar displays "Full"

  Scenario: Bar full after two customers enter and bill paid
    Given there are 8 people in the bar
    When Mr Pignon and Mr Leblanc arrive all at the bar
    Then the next customer is denied entry
    And the bar displays "Full"
    When they order a cocktail of the month at 10€ each
    Then Mr Leblanc pays for both
    When they finish their drinks, the bill is verified
    Then Mr Pignon is happy because he drank only one glass

  Scenario: Multiple orders and liver issue
    Given there are 3 people in the bar
    When Mr Pignon and Mr Leblanc arrive all at the bar
    Then they order a cocktail of the month at 10€ each
    And nobody pays for the other’s drink
    When they finish their drinks, they verify their bills
    Then Mr Pignon pays his bill
    And Mr Leblanc insists on buying another round
    When Mr Leblanc orders 2 more cocktails of the month
    Then at the end of his drink, Mr Leblanc verifies the bill and pays.
    And Mr Pignon is sad because he knows having more than one cocktail will lead to a bad night