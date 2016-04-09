package com.transerainc.autoui.executor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.transerainc.autoui.common.Login;
import com.transerainc.autoui.common.HtmlReportGen;



/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */

public class RunTestSuite extends Thread {

	private static final Logger log = Logger.getLogger(RunTestSuite.class.getName());

	private static String threadName = null;
	public static Integer completedThreads = 0;

	String opDir;
	String tPlanDir;
	String tCaseDir;
	String baseDir;
	//Integer suiteId;
	boolean isSelGrid;
	
	String threadBrowser;
	
	String testSuiteName;
	Integer testSuiteId;
	
	private static boolean testSuitePass = true;


	static Map<String, WebDriver> threadSuiteDriverMap = new HashMap<String, WebDriver>();
	static Map<String, String> threadTestScriptMap = new HashMap<String, String>();

	public static HashMap<String, String> testCasePass;


	static Document testPalnDoc;

	public  RunTestSuite(String baseDir, String opDir, String tPlanDir, String tCaseDir, Integer suiteId,
			String component,String lBrowser, boolean isSelGrid) {
		this.opDir = opDir;
		this.tPlanDir = tPlanDir;
		this.tCaseDir = tCaseDir;
		this.baseDir=baseDir;
		this.isSelGrid=isSelGrid;

		threadName = component + suiteId;		
		
		this.threadBrowser = new String(lBrowser);
		this.testSuiteName = new String(component);
		this.testSuiteId = new Integer(suiteId);

//		System.out.println(threadName + "In Constructor ");

		testCasePass = new HashMap<String, String>();
		
		
		if(lBrowser.equalsIgnoreCase("firefox")){
			startFFDriver(threadName);				
		}else if(lBrowser.equalsIgnoreCase("chrome")){
			startChromeDriver(threadName);		
		}else if(lBrowser.equalsIgnoreCase("ie")){
			startIEDriver(threadName);
		}else{			
			startFFDriver(threadName);				
		}

		Thread thread = new Thread(this, threadName);
		thread.start();
	}


	public void run() {
		String runningThread = threadName;
		System.out.println("Running thread :" + runningThread);

		long TotalPlanExecTime;
		HashMap<Integer, ArrayList<String>> testCaseResults = new HashMap<Integer, ArrayList<String>>();

		Date testSuiteStartDate = new Date();
		Format psformatter = new SimpleDateFormat("E,MMM dd HH:mm:ss.SSS");
		String TestPlanStartTime = psformatter.format(testSuiteStartDate);

		File testPlan = new File(tPlanDir + File.separator + "testplan_" + testSuiteName + ".xml");
		log.info("Reading cases from Test plan :" + testPlan);

		try {
			testPalnDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(testPlan);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		testPalnDoc.getDocumentElement().normalize();
		NodeList testCaseNodes = testPalnDoc.getElementsByTagName("testCase");
		int nlSize = testCaseNodes.getLength();

		for (int i = 0; i < nlSize; i++) {
			Date date = new Date();
			Format formatter = new SimpleDateFormat("E,MMM dd HH:mm:ss.SSS");
			String TestStartTime = formatter.format(date);

			NamedNodeMap nodeAttributes = testCaseNodes.item(i).getAttributes();

			String testCaseId = nodeAttributes.getNamedItem("id").getNodeValue();
			String script = nodeAttributes.getNamedItem("script").getNodeValue();
			String testDescription = nodeAttributes.getNamedItem("desc").getNodeValue();
			String className = nodeAttributes.getNamedItem("class").getNodeValue();
			
			//    added for multiple component running in same test suite, like add agent in OD and check in CD
			String testCaseLevelComponent=testSuiteName;
			String testCaseThreadName =runningThread;	
			Boolean isComponent = nodeAttributes.getNamedItem("component")!=null;
			if(isComponent){

				testCaseLevelComponent =nodeAttributes.getNamedItem("component").getNodeValue();
				testCaseThreadName = testCaseLevelComponent+testSuiteId;
//				System.out.println(testCaseThreadName+"====Test Case Level==isComponent=true===="+testCaseLevelComponent);
				 
					if(threadBrowser.equalsIgnoreCase("firefox")){
						startFFDriver(testCaseThreadName);				
					}else if(threadBrowser.equalsIgnoreCase("chrome")){
						startChromeDriver(testCaseThreadName);		
					}else if(threadBrowser.equalsIgnoreCase("ie")){
						startIEDriver(testCaseThreadName);
					}else{			
						startFFDriver(testCaseThreadName);				
					}
			}
			
			threadTestScriptMap.put(testCaseThreadName, script);	

//			threadTestScriptMap.put(runningThread, script);			


			String pkgName = null;
			String pkgInitials="com.transerainc.autoui.";
			
			if (className.equalsIgnoreCase("LoginCD")) {
				pkgName = pkgInitials+"common.";
			} else if (testCaseLevelComponent.equalsIgnoreCase("rs")) {
				pkgName =  pkgInitials+"rs.";
			} else if (testCaseLevelComponent.equalsIgnoreCase("hrs")) {
				pkgName =  pkgInitials+"hrs.";
			} else if (testCaseLevelComponent.equalsIgnoreCase("monitoring")) {
				pkgName =  pkgInitials+"callmonitoring.";
			}else if(testCaseLevelComponent.equalsIgnoreCase("provisioning")){
				pkgName =  pkgInitials+"provisioning.";
			}else if(testCaseLevelComponent.equalsIgnoreCase("CDLandingPage")){
				pkgName =  pkgInitials+"CDLandingPage.";
			}
			
			

			String funClass = pkgName + className;
			Class<?> c;
			try {
				c = Class.forName(funClass);

				Class[] argTypes = new Class[] { String[].class };

				Method main = c.getDeclaredMethod("main", argTypes);

//				String[] mainArgs = { runningThread };
				String[] mainArgs = { testCaseThreadName };			
				
				main.invoke(null, (Object) mainArgs);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			String isPass = testCasePass.get(script);
			if (isPass.equals("false"))
				testSuitePass = false;

			log.info(testCaseId + " - " + testDescription + " : " + testCasePass);
			
			Integer testID = Integer.parseInt(testCaseId.split("-")[1]);


			
			Date end_date = new Date();
			Format date_formatter = new SimpleDateFormat("E,MMM dd HH:mm:ss.SSS");
			String TestEndTime = date_formatter.format(end_date);

			testCaseResults.put(testID, new ArrayList<String>());
			
			ArrayList<String> arrlist = new ArrayList<String>(5);
			arrlist.add(TestStartTime);
			arrlist.add(TestEndTime);
			arrlist.add(String.valueOf(isPass));
			arrlist.add(testDescription);
			arrlist.add(threadBrowser);

			testCaseResults.get(testID).addAll(arrlist);			

			testCasePass.remove(script);
		}

		Date testSuiteEndDate = new Date();
		Format peformatter = new SimpleDateFormat("E,MMM dd HH:mm:ss.SSS");
		String TestPlanEndTime = peformatter.format(testSuiteEndDate);

		StartExecution.testSuiteResults.put(testSuiteId, new ArrayList<String>());		
		
		ArrayList<String> suitArrlist = new ArrayList<String>(6);
		suitArrlist.add(TestPlanStartTime);
		suitArrlist.add(TestPlanEndTime);
		suitArrlist.add(String.valueOf(testSuitePass));
		suitArrlist.add(testSuiteName + " test suite description");
		suitArrlist.add(threadBrowser);
		suitArrlist.add(testSuiteName);
		
		StartExecution.testSuiteResults.get(testSuiteId).addAll(suitArrlist);

		TotalPlanExecTime = testSuiteEndDate.getTime() - testSuiteStartDate.getTime();
		log.info(testSuiteName + " - TOTAL PLAN EXECUTION TIME :" + TotalPlanExecTime + " milliseconds");
		testSuitePass = true;
		createHtmlReport(runningThread, testSuiteName, testCaseResults, TotalPlanExecTime);
		testCaseResults.clear();
		completedThreads++;
	}
	
	private void startChromeDriver(String tdName) {
		WebDriver driver;
		DesiredCapabilities dc = new DesiredCapabilities();
		
		System.setProperty("webdriver.chrome.driver", baseDir + "/lib/chromedriver.exe");
		dc = DesiredCapabilities.chrome();
		dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
		dc.setPlatform(org.openqa.selenium.Platform.ANY);
		

		if(isSelGrid){
			driver = new RemoteWebDriver(dc);		
		}else{
			driver = new ChromeDriver(dc);	
		}
		
		driver.manage().window().maximize();
		
		threadSuiteDriverMap.put(tdName, driver);
	}
	
	
	private void startIEDriver(String tdName){
		WebDriver driver;
		DesiredCapabilities dc = new DesiredCapabilities();
	
		System.setProperty("webdriver.ie.driver", baseDir + "/lib/IEDriverServer.exe");

		dc = DesiredCapabilities.internetExplorer();
		dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
		dc.setPlatform(org.openqa.selenium.Platform.ANY);
		
		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		dc.setCapability("ignoreProtectedModeSettings", true);
		

		if(isSelGrid){
			driver = new RemoteWebDriver(dc);		
		}else{
			driver = new InternetExplorerDriver(dc);	
		}
		
		driver.manage().window().maximize();
		
		threadSuiteDriverMap.put(tdName, driver);
	}
	

	private void startFFDriver(String tdName ) {
		WebDriver driver;
		DesiredCapabilities dc = new DesiredCapabilities();
		
		ProfilesIni pini = new ProfilesIni();
		FirefoxProfile fp = pini.getProfile("selenium");

		dc = DesiredCapabilities.firefox();
		dc.setCapability(FirefoxDriver.PROFILE, fp);
		dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
		dc.setPlatform(org.openqa.selenium.Platform.ANY);
		
		
		if(isSelGrid){
			driver = new RemoteWebDriver(dc);		
		}else{
			driver = new FirefoxDriver(dc);	
		}		
		
		driver.manage().window().maximize();
			
		threadSuiteDriverMap.put(tdName, driver);
	}

	

	public static String getTestScript(String tName) {
		return threadTestScriptMap.get(tName);
	}

	public static void setDriver(String thName, WebDriver dvr) {
		threadSuiteDriverMap.put(thName, dvr);
	}

	public static WebDriver getDriver(String tName) {
		return threadSuiteDriverMap.get(tName);
	}

	private  void createHtmlReport(String rThread, String reportName,
			HashMap<Integer, ArrayList<String>> resultsMap, long TExeTime) {

		SortedMap treeMap = new TreeMap(resultsMap);
		Iterator i = treeMap.entrySet().iterator();

		FileWriter writer = null;
		HtmlReportGen TestReport = null;
		String reportFileName = reportName + ".html";

		try {
			TestReport = new HtmlReportGen(opDir + File.separator + reportFileName, treeMap.size(), TExeTime, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			ArrayList TestReportData = (ArrayList) entry.getValue();

			try {

				String Id = entry.getKey().toString();

				String TestCaseReportFile = opDir + File.separator + "Summary.html";

				TestReport.appendURI(TestCaseReportFile, Id, TestReportData);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		TestReport.close();
		resultsMap.clear();
		TExeTime = 0;

		Login.closeWindow(rThread);
		threadSuiteDriverMap.remove(rThread);
	}

	public static void cleanUpResources() {
		threadSuiteDriverMap.clear();
		threadTestScriptMap.clear();
		threadName = null;
		completedThreads = 0;
	}
	
	private static void waitFor(long mSec){
		try {
			Thread.sleep(mSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
