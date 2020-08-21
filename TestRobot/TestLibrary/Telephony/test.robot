*** Settings ***
Resource  ../../lib/webexcc/api/api_resources.robot
Resource  ../../lib/webexcc/ui/ui_resources.robot
Variables  ./desktop.yaml



*** Test Cases ***
Basic Call
    [Tags]  TEST
    Log         ${DESKTOP_URL_${ENV}}
    Log To Console      ${DESKTOP_URL_${ENV}}
#    Log         @{DESKTOP_AGENTS_${ENV}}
    Login To Agent Desktop      ${DESKTOP_URL_${ENV}}        @{DESKTOP_AGENTS_${ENV}}
    Test Method For Rest        ${ENV}
    Switch To Different Browser       ${DESKTOP_AGENTS_${ENV}}[0][AGENT1][USERID]
    Go Available
    Switch To Different Browser       ${DESKTOP_AGENTS_${ENV}}[1][AGENT2][USERID]
    Go Available