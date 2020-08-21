*** Settings ***
Library     SeleniumLibrary
Library     Collections
Library     OperatingSystem
Library     common.py

Variables   ./login_page_factory.yaml

*** Keywords ***
Login To Agent Desktop
    [Arguments]     ${desktopUrl}       @{agentsList}
    FOR     ${agentInfoDic}    IN      @{agentsList}
            Log      ${agentInfoDic}
            Log To Console      ${agentInfoDic}
            ${keysList} =   Get Dictionary Keys   ${agentInfoDic}
#            &{agentInfo} =  Create Dictionary   ${agentInfoDic}[${keysList}[0]]

            Lounch Browser And Login User   ${desktopUrl}     ${agentInfoDic}[${keysList}[0]]

            WaitAndFill     ${STATIONDN_TXT}    ${agentInfoDic}[${keysList}[0]][DN]
            Click element   ${OTHER_BUTTOM}
            Click element   ${TEAMS_DROP_BOX}
            ${selectTeam} =    common.form_element_locator     ${TEAM}      ${agentInfoDic}[${keysList}[0]][TEAM]
            WaitAndClick    ${selectTeam}
            Click element   ${STATION_SUBMIT_BUTTON}
            Wait Until Page Contains Element   ${DESKTOP_LOGEDIN}   timeout=40      error="Navigating to agent desktop landing page fail"

    END



Lounch Browser And Login User
    [Arguments]  ${applicationUrl}     ${userInfo}
#    Set Environment Variable  webdriver.chrome.driver  /Users/pnarah/automation/drivers/chromedriver

    Log     "Browser : " + ${userInfo}[BROWSER]
    Log     "UserId: " + ${userInfo}[USERID]
    Log     "Password: " + ${userInfo}[PASSWORD]
    Open Browser   ${applicationUrl}     ${userInfo}[BROWSER]        alias = ${userInfo}[USERID]
    WaitAndFill  ${LOGINID_TXT}  ${userInfo}[USERID]
    WaitAndClick  ${NEXT_BUTTOM}
    WaitAndFill  ${PASSWORD_TXT}  ${userInfo}[PASSWORD]
    WaitAndClick  ${SUBMIT_BUTTON}


WaitAndClick
    [Arguments]  ${element}
    Wait Until Element Is Visible   ${element}  30
    click element   ${element}

WaitAndFill
    [Arguments]  ${element}  ${text}
    Wait Until Element Is Visible   ${element}  30
    input text  ${element}   ${text}


Switch To Different Browser
    [Arguments]  ${agentId}
    Log To Console      "Switching Browser to ${agentId}"
    Switch To Browser   ${agentId}
