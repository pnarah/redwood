package com.transerainc.autoui.callmonitoring;

import java.util.HashMap;
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

public class SelectScheduleView extends Login {

	private static final Logger log = Logger.getLogger(SelectScheduleView.class.getName());

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
//		NamedNodeMap testData = getTestData(testScript);
		NamedNodeMap testData = getTestData(testScript);
		
		final int LISTVIEW = 1;
		final int CALENDERVIEW = 2;

		final Map<String, Integer> viewMap = new HashMap<String, Integer>();
		{
			viewMap.put("list", LISTVIEW);
			viewMap.put("calender", CALENDERVIEW);
		}

//		String view = testData.getNamedItem("view").getNodeValue().toLowerCase();
		String view = testData.get("view");
		
		SchedulePage schedulePgObj = new SchedulePage(driver);
		schedulePgObj.clickScheduleTab();

		switch (viewMap.get(view)) {
		case LISTVIEW:
			if (!schedulePgObj.clickListView()) {
				log.info("Select LISTVIEW fail!!");
				testCasePass = false;
			}
			break;
		case CALENDERVIEW:
			if (!schedulePgObj.clickCalenderView()) {
				log.info("Select CALENDERVIEW fail!!");
				testCasePass = false;
			}
			break;
		default:
			testCasePass = false;
		}

		RunTestSuite.testCasePass.put(testScript, String.valueOf(testCasePass));
		closeWindow(threanN);
	}
}
