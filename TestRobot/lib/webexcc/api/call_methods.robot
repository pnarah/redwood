*** Settings ***
Library   OperatingSystem
Library  ./RestApiMethods.py      WITH NAME     restmethods


*** Keywords ***
Test Method For Rest
    [Arguments]  ${dc}
    Log To Console      ${CURDIR}
    restmethods.Request Method Test     ${dc}

