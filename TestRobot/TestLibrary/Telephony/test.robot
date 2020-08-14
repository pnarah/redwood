*** Settings ***

Resource  ../../webexcc/ui/ui_resources.robot
Variables  ./desktop.yaml



*** Test Cases ***
Basic Call
    [Tags]  TEST
    Log         ${DESKTOP_URL_${ENV}}
    Log To Console      ${DESKTOP_URL_${ENV}}
#    Log         @{DESKTOP_AGENTS_QAUS1}
    Login To Agent Desktop      ${DESKTOP_URL_${ENV}}        @{DESKTOP_AGENTS_${ENV}}