package com.transerainc.autoui.callmonitoring;

import java.util.ArrayList;
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

public class CheckLinks extends Login {

	private static final Logger log = Logger.getLogger(CheckLinks.class.getName());

	private static String testScript;
	private static WebDriver driver;

	public static void main(String[] args) {
		String threanN = null;
		if (args.length > 0) {
			threanN = args[0];
		}

		final int HELP = 1;
		final int CLOSEMODULE = 2;
		final int WELCOMEMESSAGE = 3;
		final int COPYRIGHT = 4;
		final int COPYRIGHTMESSAGE = 5;

		final Map<String, Integer> cdLinks = new HashMap<String, Integer>();
		{

			cdLinks.put("Help", HELP);
			cdLinks.put("CloseModule", CLOSEMODULE);
			cdLinks.put("WelcomeMessage", WELCOMEMESSAGE);
			cdLinks.put("Copyright", COPYRIGHT);
			cdLinks.put("copyRightMessage", COPYRIGHTMESSAGE);
		}

		Boolean testCasePass = true;
		driver = RunTestSuite.getDriver(threanN);
		loginToCD(threanN, driver);
		clickComponent(driver, "Call Monitoring");

		testScript = RunTestSuite.getTestScript(threanN);
		NamedNodeMap testData = getTestData(testScript);
		CallMonitoringPage callMonPgObj = new CallMonitoringPage(driver);

		List<String> allList = new ArrayList<String>();

		if (testData.getNamedItem("displayedLinks") != null) {
			allList.addAll(Arrays.asList(testData.getNamedItem("displayedLinks").getNodeValue().split(";")));
		}

		if (testData.getNamedItem("copyRightMessage") != null) {
			allList.add("copyRightMessage");
		}

		for (int i = 0; i < allList.size(); i++) {
			String link = allList.get(i);
			switch (cdLinks.get(link)) {
			case HELP:
				if (!callMonPgObj.helpLinkIsDisplayed()) {
					testCasePass = false;
					log.info("HELP link not found");
				}

				break;

			case CLOSEMODULE:
				if (!callMonPgObj.closeModuleLinkIsDisplayed()) {
					testCasePass = false;
					log.info("CLOSEMODULE link not found");
				}
				break;

			case WELCOMEMESSAGE:
				if (!callMonPgObj.welcomeMsgIsDisplayed()) {
					testCasePass = false;
					log.info("WELCOMEMESSAGE link not found");
				}

				break;

			case COPYRIGHT:
				if (!callMonPgObj.copyRightIsDisplayed()) {
					testCasePass = false;
					log.info("COPYRIGHT link not found");
				}

				break;

			case COPYRIGHTMESSAGE:
				String msg = testData.getNamedItem("copyRightMessage").getNodeValue();
				System.out.println("---COPYRIGHTMESSAGE----" + msg);
				if (!callMonPgObj.validateCopyRightMessage(msg.trim())) {
					testCasePass = false;
					log.info("Copy Right Message mismatch");
				}

				break;
			default:
				testCasePass = false;
			}
		}

		RunTestSuite.testCasePass.put(testScript, String.valueOf(testCasePass));

		closeWindow(threanN);
	}
}
