Feature: Test tenant creation in control hub customer

Scenario: Login customer to control hub
Given Open firefox browser and start application
When enter admin "sssssss@mailinator.com" with "sssss" to login page
Then admin "customer" in landing page
Then logout "customer" from control hub