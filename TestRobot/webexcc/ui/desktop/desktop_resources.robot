*** Settings ***
#Resource    ../common/login_resources.robot

*** Keywords ***
Accept Call
#    [Arguments]  ${DESKTOP_PROPERTIES_QAUS1}[USERS][0][AGENT1]
    Log ${DESKTOP_PROPERTIES_QAUS1}[USERS][0][AGENT1][AGENTID]
    Log To Console "Hi there welcome"
