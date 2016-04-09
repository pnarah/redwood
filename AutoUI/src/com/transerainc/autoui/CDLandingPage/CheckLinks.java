package com.transerainc.autoui.CDLandingPage;

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

		final int REALTIMEREPORTS = 1;
		final int HISTORICALREPORTS = 2;
		final int CALLMONITORING = 3;
		final int CALLRECORDING = 4;
		final int ROUTINGSTRATEGY = 5;
		final int PROVISIONING = 6;
		final int PASSWORDRESET = 7;
		final int UNLOCKAGENT = 8;
		final int USAGEMETRICSREPORT = 9;
		final int MAPVIEW = 10;
		final int WEBCALLBACKREQUESTREPORT = 11;
		final int HELP = 12;
		final int SIGNOUT = 13;
		final int WELCOMEMESSAGE = 14;
		final int COPYRIGHT = 15;
		final int COPYRIGHTMESSAGE = 16;

		final Map<String, Integer> cdLinks = new HashMap<String, Integer>();
		{
			cdLinks.put("RealTimeReports", REALTIMEREPORTS);
			cdLinks.put("HistoricalReports", HISTORICALREPORTS);
			cdLinks.put("CallMonitoring", CALLMONITORING);
			cdLinks.put("CallRecording", CALLRECORDING);
			cdLinks.put("RoutingStrategy", ROUTINGSTRATEGY);
			cdLinks.put("Provisioning", PROVISIONING);
			cdLinks.put("PasswordReset", PASSWORDRESET);
			cdLinks.put("UnlockAgent", UNLOCKAGENT);
			cdLinks.put("UsageMetricsReport", USAGEMETRICSREPORT);
			cdLinks.put("MapView", MAPVIEW);
			cdLinks.put("WebCallbackRequestReport", WEBCALLBACKREQUESTREPORT);
			cdLinks.put("Help", HELP);
			cdLinks.put("Signout", SIGNOUT);
			cdLinks.put("WelcomeMessage", WELCOMEMESSAGE);
			cdLinks.put("Copyright", COPYRIGHT);
			cdLinks.put("copyRightMessage", COPYRIGHTMESSAGE);

		}

		Boolean testCasePass = true;
		driver = RunTestSuite.getDriver(threanN);
		loginToCD(threanN, driver);

		testScript = RunTestSuite.getTestScript(threanN);
		NamedNodeMap testData = getTestData(testScript);
		CDLandingPage landingPageObj = new CDLandingPage(driver);

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
			case REALTIMEREPORTS:

				if (!landingPageObj.rtmcLinkIsDisplayed()) {
					testCasePass = false;
					log.info("REALTIMEREPORTS link not found");
				}

				break;
			case HISTORICALREPORTS:

				if (!landingPageObj.hrsLinkIsDisplayed()) {
					testCasePass = false;
					log.info("HISTORICALREPORTS link not found");
				}
				break;

			case CALLMONITORING:

				if (!landingPageObj.callMonitoringLinkIsDisplayed()) {
					testCasePass = false;
					log.info("CALLMONITORING link not found");
				}
				break;
			case CALLRECORDING:

				if (!landingPageObj.callRecordingLinkIsDisplayed()) {
					testCasePass = false;
					log.info("CALLRECORDING link not found");
				}
				break;

			case ROUTINGSTRATEGY:

				if (!landingPageObj.routingStrategyLinkIsDisplayed()) {
					testCasePass = false;
					log.info("ROUTINGSTRATEGY link not found");
				}
				break;
			case PROVISIONING:

				if (!landingPageObj.selfProLinkIsDisplayed()) {
					testCasePass = false;
					log.info("PROVISIONING link not found");
				}
				break;

			case PASSWORDRESET:

				if (!landingPageObj.passwordResetLinkIsDisplayed()) {
					testCasePass = false;
					log.info("PASSWORDRESET link not found");
				}
				break;
			case UNLOCKAGENT:

				if (!landingPageObj.unlockAgentLinkIsDisplayed()) {
					testCasePass = false;
					log.info("UNLOCKAGENT link not found");
				}
				break;

			case USAGEMETRICSREPORT:

				if (!landingPageObj.usageMetricsReportLinkIsDisplayed()) {
					testCasePass = false;
					log.info("USAGEMETRICSREPORT link not found");
				}
				break;
			case MAPVIEW:

				if (!landingPageObj.mapViewLinkIsDisplayed()) {
					testCasePass = false;
					log.info("MAPVIEW link not found");
				}
				break;
			case WEBCALLBACKREQUESTREPORT:

				if (!landingPageObj.webCallBackLinkIsDisplayed()) {
					testCasePass = false;
					log.info("WEBCALLBACKREQUESTREPORT link not found");
				}
				break;
			case HELP:

				if (!landingPageObj.helpLinkIsDisplayed()) {
					testCasePass = false;
					log.info("HELP link not found");
				}
				break;

			case SIGNOUT:

				if (!landingPageObj.logoutLinkIsDisplayed()) {
					testCasePass = false;
					log.info("SIGNOUT link not found");
				}
				break;

			case WELCOMEMESSAGE:

				if (!landingPageObj.welcomeMsgIsDisplayed()) {
					testCasePass = false;
					log.info("WELCOMEMESSAGE link not found");
				}
				break;

			case COPYRIGHT:

				if (!landingPageObj.copyRightIsDisplayed()) {
					testCasePass = false;
					log.info("COPYRIGHT link not found");
				}
				break;

			case COPYRIGHTMESSAGE:
				String msg = testData.getNamedItem("copyRightMessage").getNodeValue();

				if (!landingPageObj.validateCopyRightMessage(msg.trim())) {
					testCasePass = false;
					log.info("Copy Right Message mismatch");
				}
				break;
			default:
				testCasePass = false;
			}
		}

		RunTestSuite.testCasePass.put(testScript, String.valueOf(testCasePass));

	}
}
