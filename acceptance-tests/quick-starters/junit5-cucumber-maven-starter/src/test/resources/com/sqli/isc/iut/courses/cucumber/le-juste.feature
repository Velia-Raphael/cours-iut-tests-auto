Feature: Le Juste Cocktail Bar Management
  As the owner of Le Juste bar
  I want to manage seating and orders
  So that I can ensure the smooth operation of my establishment

  Background:
    Given Mr Pignon and Mr Leblanc go to the bar Le Juste
    And the bar has only 10 seats

  Scenario: Entry refused when bar is full
    Given there are 9 people in the bar
    When Mr Pignon and Mr Leblanc try to enter
    Then they are denied entry
    And the bar displays "Full"

  Scenario: Last available seats and ordering
    Given there are 8 people in the bar
    When Mr Pignon and Mr Leblanc enter the bar
    Then the person behind them cannot enter
    And the bar displays "Full"
    When they each order a cocktail of the month
    And Mr Leblanc pays for both drinks
    Then the bill is verified
    And Mr Leblanc pays
    And Mr Pignon is happy because they only had one drink

  Scenario: Multiple orders and liver issue
    Given there are 3 people in the bar
    When Mr Pignon and Mr Leblanc enter the bar
    And they each order a cocktail of the month
    Then they check their individual bills
    And Mr Pignon pays his bill
    And Mr Leblanc orders 2 more cocktails of the month
    When Mr Leblanc checks the bill and pays
    Then Mr Pignon is sad because he knows having more than one cocktail will lead to a bad night