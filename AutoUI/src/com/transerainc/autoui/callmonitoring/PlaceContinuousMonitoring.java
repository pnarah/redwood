package com.transerainc.autoui.callmonitoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.NamedNodeMap;

import com.transerainc.autoui.CDLandingPage.CheckLinks;
import com.transerainc.autoui.common.Login;
import com.transerainc.autoui.executor.RunTestSuite;

/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */

public class PlaceContinuousMonitoring extends Login{

	
	private static final Logger log = Logger.getLogger(CheckLinks.class.getName());
	
	private static String testScript;
	private static WebDriver driver;
		
	public static void main(String[] args){
			String threanN=null;
			if(args.length>0){
				threanN= args[0];
			}
			
			Boolean testCasePass= true;			
			driver=RunTestSuite.getDriver(threanN);				
			loginToCD(threanN,driver);			
			clickComponent(driver,"Call Monitoring");
			
			testScript=RunTestSuite.getTestScript(threanN);
			NamedNodeMap testData=getTestData(testScript);
			CallMonitoringPage callMonPgObj = new CallMonitoringPage(driver);
			

			
			List<String> vTeamList = new ArrayList<String>();
			List<String> siteList = new ArrayList<String>();
			List<String> teamList= new ArrayList<String>();
			List<String> agentList = new ArrayList<String>();
			String dn="";
			
			if(testData.getNamedItem("vTeam")!=null){
				vTeamList = Arrays.asList(testData.getNamedItem("vTeam").getNodeValue().split(";"));
				if(!callMonPgObj.selectVTeam(vTeamList))
				{
					testCasePass=false;
				}
			}
			
			if(testData.getNamedItem("site")!=null){
				siteList = Arrays.asList(testData.getNamedItem("site").getNodeValue().split(";"));
				if(!callMonPgObj.selectSite(siteList))
				{
					testCasePass=false;
				}
			}
			
			if(testData.getNamedItem("team")!=null){
				teamList = Arrays.asList(testData.getNamedItem("team").getNodeValue().split(";"));
				if(!callMonPgObj.selectTeam(teamList))
				{
					testCasePass=false;
				}
			}

			if(testData.getNamedItem("agent")!=null){
				agentList = Arrays.asList(testData.getNamedItem("agent").getNodeValue().split(";"));				
				if(!callMonPgObj.selectAgent(agentList))
				{
					testCasePass=false;
				}
			}
							
			if((testData.getNamedItem("dn")) != null){
		 		dn = testData.getNamedItem("dn").getNodeValue();		 		
		 		if(!callMonPgObj.setCallBackNumber(dn))
				{
					testCasePass=false;
				}
			}
			
			callMonPgObj.placeContinuousMonitor();
			
			RunTestSuite.testCasePass.put(testScript, String.valueOf(testCasePass));
			 closeWindow(threanN);
	}
}
