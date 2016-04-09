package com.transerainc.autoui.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.transerainc.autoui.executor.StartExecution;

public class Common {

	static String testCasesDirectory=StartExecution.testCasesDirectory;
	
	public static void switchWindow(WebDriver wDriver ,String cmp){
		Set<String> windowHandles = wDriver.getWindowHandles();
		Iterator ite = windowHandles.iterator();
		while(ite.hasNext()){
			String handle = (String) ite.next();
			wDriver.switchTo().window(handle);
			if(wDriver.getTitle().equals(cmp)){
				wDriver.switchTo().defaultContent();
//				System.out.println("*********Switched to Component***********"+wDriver.getTitle());			
				break;
			}
		}		
	}
	
	public static void clickComponent(WebDriver wDriver,String component){
//		System.out.println("*********Opening component ***********:"+component);	
		wDriver.switchTo().defaultContent();
		wDriver.findElement(By.linkText(component)).click();
		switchWindow(wDriver,component);
	}	
	
	
	public static  NamedNodeMap getTestData(String testScript){
		Document doc=null;
		NamedNodeMap testData= null ;
		

		
		File testScriptFile = new File(testCasesDirectory+File.separator+testScript+".xml");
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(testScriptFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		doc.getDocumentElement().normalize();
		
		NodeList testNodeList = doc.getElementsByTagName("script");
		Node testNode = testNodeList.item(0);
		testNode=testNode.getFirstChild();
		
		while (testNode != null && testNode.getNodeType() != Node.ELEMENT_NODE) {
			testNode = testNode.getNextSibling();
		}
		
		testData=testNode.getAttributes();
		
//		Map<String, String> testDataMap = new HashMap<String, String>();
//		for (int i =0; i < testData.getLength();i++){
//			testDataMap.put(testData.item(i).getNodeName() ,testData.item(i).getNodeValue());			
//		}		

//		return testDataMap;
		
		return testData;
	}	


		public static void printTestData(NamedNodeMap testData){		
			for (int i =0; i < testData.getLength();i++){
				System.out.println("\t Attribute name :"+testData.item(i).getNodeName() +"   value  :"+testData.item(i).getNodeValue());
			}		
		}
		
		public static void waitFor(long mSec){
			try {
				Thread.sleep(mSec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
}
