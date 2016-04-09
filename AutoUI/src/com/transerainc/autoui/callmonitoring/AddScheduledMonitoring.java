package com.transerainc.autoui.callmonitoring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.NamedNodeMap;

import com.transerainc.autoui.executor.RunTestSuite;
import com.transerainc.autoui.common.Login;

/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */

public class AddScheduledMonitoring extends Login {

	private static final Logger log = Logger.getLogger(AddScheduledMonitoring.class.getName());

	private static String testScript;
	private static WebDriver driver;

	public static void main(String[] args) {
		String threanN = null;
		if (args.length > 0) {
			threanN = args[0];
		}

		Boolean testCasePass = true;
		driver = RunTestSuite.getDriver(threanN);
		loginToCD(threanN, driver);
		clickComponent(driver, "Call Monitoring");

		testScript = RunTestSuite.getTestScript(threanN);
		NamedNodeMap testData = getTestData(testScript);

		
		for(String key: testData.keySet()){
			
		}
		
		
	
//		String strategyName = testData.getNamedItem("name").getNodeValue();
//		String startDate = testData.getNamedItem("startDate").getNodeValue();
//		String endDate = testData.getNamedItem("endDate").getNodeValue();
//		String startTime = testData.getNamedItem("startTime").getNodeValue();
//		String endTime = testData.getNamedItem("endTime").getNodeValue();
//		String daysOfWeek = testData.getNamedItem("daysOfWeek").getNodeValue();
//		String dn = testData.getNamedItem("dn").getNodeValue();
//		String expectedMsg = testData.getNamedItem("message").getNodeValue();
//		List<String> vTeamList = Arrays.asList(testData.getNamedItem("vTeam").getNodeValue().split(";"));
//		List<String> siteList = Arrays.asList(testData.getNamedItem("site").getNodeValue().split(";"));
//		List<String> teamList = Arrays.asList(testData.getNamedItem("team").getNodeValue().split(";"));
//		List<String> agentList = Arrays.asList(testData.getNamedItem("agent").getNodeValue().split(";"));
//		
//		
//		
//		SchedulePage schedulePgObj = new SchedulePage(driver);
//		schedulePgObj.clickScheduleTab();
//		schedulePgObj.clickListView();
//		schedulePgObj.clickAddButton();
//		
//		
//		
//		schedulePgObj.setName(strategyName);
//		schedulePgObj.setStartDate(startDate);
//		schedulePgObj.setEndDate(endDate);
//		schedulePgObj.setStartTime(startTime);
//		schedulePgObj.setEndTime(endTime);
//		schedulePgObj.setDaysOfWeek(daysOfWeek);
//		schedulePgObj.setCallBackNumber(dn);
//		schedulePgObj.selectVTeam(vTeamList);
//		schedulePgObj.selectSite(siteList);
//		schedulePgObj.selectTeam(teamList);
//		schedulePgObj.selectAgent(agentList);
//		schedulePgObj.clickSave();
//		schedulePgObj.validateServerResponce(expectedMsg);	
		
		
		
		
		
		RunTestSuite.testCasePass.put(testScript, String.valueOf(testCasePass));
		closeWindow(threanN);
	}
}
