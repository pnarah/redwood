Feature: Test tenant creation in control hub partner

Scenario: Login partner to control hub
Given Open firefox browser and start application
When enter admin "dddddd@gmail.com" with "dddddd" to login page
Then admin "partner" in landing page
Then logout "partner" from control hub