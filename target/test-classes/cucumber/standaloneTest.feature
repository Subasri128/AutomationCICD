
@tag
Feature: Purchase the order from ECOMMERCE website
  I want to use this template for my feature file
  		Background:
  		Given I landed on ECOMMERCE page
 
@Positivescenario
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <pdtname> to cart
    And Checkout <pdtname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name                | password   | pdtname         |
      | student01@gmail.com | Student@01 | ADIDAS ORIGINAL |
     
