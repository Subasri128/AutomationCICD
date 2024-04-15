
@tag
Feature: error validation
  I want to use this template for my feature file

  Background:
  		Given I landed on ECOMMERCE page

  @Errorvalidation
 
  Scenario Outline: Fail Scenario
    Given I landed on ECOMMERCE page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is visible

    Examples: 
       | name                | password   |
      | student01@gmail.com | Student@07 | 
