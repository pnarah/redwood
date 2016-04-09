package com.transerainc.autoui.executor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.transerainc.autoui.common.HtmlReportGen;



/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */




public class StartExecution {
	private static final Logger log = Logger.getLogger(StartExecution.class.getName());
	
	public static String outputDirectory=null;
	public static String configDirectory=null;
	public static String testPlanDirectory=null;
	public static String testCasesDirectory=null;

	public static String cdUrl=null;
	public static String cdUserName=null;
	public static String cdPassword=null;
	public static String tenant=null;

	
	public static String odUrl=null;
	public static String odUserName=null;
	public static String odPassword=null;
	
	
	public static String  browser="firefox";
	public static boolean isGrid=false;
	
	
	static Properties pop=null;
	public static String tempOutputDirectory;
	
	static Document suiteDoc;
	static Node suiteCommand;

	
	static HashMap<Integer, ArrayList<String>> testSuiteResults = new HashMap<Integer, ArrayList<String>>(); 


	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		long TotalExecTime;
		
		Date ssdate= new Date();
		long timeStamp = ssdate.getTime();
		Format formatter = new SimpleDateFormat("dd-MMM-YYYY");
		String formatedDt = formatter.format(ssdate);	
		
		System.setProperty("baseDir",System.getProperty("user.dir"));
		String baseDir = System.getProperty("baseDir");

		
		pop = new Properties();	
		readConfigFile(pop, baseDir);	
		
		System.out.println("Is Runnins in Grid Configuration :"+isGrid);
		

		tempOutputDirectory = outputDirectory + File.separator + formatedDt+ File.separator + timeStamp;
		Boolean pass = (new File(tempOutputDirectory)).mkdirs();
		if(!pass){
			System.out.println("Output directory :" + tempOutputDirectory + " creation fail - Exiting execution");
			System.exit(1);
		}
		
//		log.info("Reading test suite xml file transera.xml");
		File testSuite = new File(testPlanDirectory+"/transera.xml");

		try {
			 suiteDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(testSuite);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		suiteDoc.getDocumentElement().normalize();
		

	  NodeList suiteNodes = suiteDoc.getElementsByTagName("testPlan");
	  
	  int sz=suiteNodes.getLength();
	  
	  List<RunTestSuite> runTestSuiteObj = new ArrayList<RunTestSuite>();

	  
	  for(int i=0; i < sz ;i++){
		  
		  Node suiteNode = suiteNodes.item(i);	  
		  NamedNodeMap nodeMap= suiteNode.getAttributes();
		  
		  Integer testSuiteId = Integer.parseInt(suiteNode.getAttributes().getNamedItem("id").getNodeValue());
		  String testSuiteName = suiteNode.getAttributes().getNamedItem("component").getNodeValue();
		  String testSuiteDesc = suiteNode.getAttributes().getNamedItem("desc").getNodeValue();		  
		  browser = nodeMap.getNamedItem("browser")!=null ? nodeMap.getNamedItem("browser").getNodeValue():browser;
				  
				  
		  log.info(browser+"******* Suite Id :" + testSuiteId + " component :"+testSuiteName +" description:"+ testSuiteDesc);
		  
		  RunTestSuite rtsObj1 = new RunTestSuite(baseDir,tempOutputDirectory,testPlanDirectory,testCasesDirectory,testSuiteId,testSuiteName,browser,isGrid);	    
		  runTestSuiteObj.add(rtsObj1);
  		  
	  }
	  
	  
	  while(RunTestSuite.completedThreads!=runTestSuiteObj.size()){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	  }
	  
	  
		Date sedate = new Date();						
		TotalExecTime = sedate.getTime() - ssdate.getTime();
		log.info("TOTAL EXECUTION TIME :" + TotalExecTime + " milliseconds");			
		
		createHtmlReport("Summary", testSuiteResults, TotalExecTime );
		testSuiteResults.clear();
		
		RunTestSuite.cleanUpResources();
	}
	


	private static void readConfigFile(Properties pop2, String baseDir) {
		InputStream is=null;
		
		try {
			is = new FileInputStream(baseDir+"/autoui.config");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			pop2.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		outputDirectory=pop2.getProperty("outputDirectory");
		configDirectory=pop2.getProperty("configDirectory");
		testPlanDirectory=pop2.getProperty("testPlanDirectory");	
		testCasesDirectory=pop2.getProperty("testCasesDirectory");
		cdUrl=pop2.getProperty("cdUrl");
		cdUserName=pop2.getProperty("cdUserName");
		cdPassword=pop2.getProperty("cdPassword");
		tenant=pop2.getProperty("tenant");
		
		odUrl=pop2.getProperty("odUrl");
		odUserName=pop2.getProperty("odUserName");
		odPassword=pop2.getProperty("odPassword");			
		
		browser=pop2.getProperty("browser");
		isGrid=Boolean.parseBoolean(pop2.getProperty("seleniumGrid"));

	}
	
	private static void createHtmlReport(String reportName, HashMap<Integer, ArrayList<String>> resultsMap,long TExeTime ) {

		SortedMap treeMap = new TreeMap(resultsMap);
		Iterator i = treeMap.entrySet().iterator();
		
		FileWriter writer = null;
		HtmlReportGen TestReport = null;
		String reportFileName = reportName + ".html";
		
		boolean suite;		
		suite=reportFileName.equalsIgnoreCase("Summary.html")?true:false;		
		
		try {
			TestReport = new HtmlReportGen(tempOutputDirectory + File.separator	+ reportFileName, treeMap.size(), TExeTime, suite);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();		
			ArrayList TestReportData = (ArrayList) entry.getValue();
						
			try {
			
				String Id = entry.getKey().toString();								

				String suiteName = (String) TestReportData.get(5);	
				String TestCaseReportFile = tempOutputDirectory + File.separator + suiteName + ".html";		

				TestReport.appendURI(TestCaseReportFile, Id, TestReportData);				
			
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
		TestReport.close();	
		resultsMap.clear();
		TExeTime = 0;			
		
	}
	
}
