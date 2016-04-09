package com.transerainc.autoui.provisioning;


import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.NamedNodeMap;

import com.transerainc.autoui.common.Login;
import com.transerainc.autoui.executor.RunTestSuite;


/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */


public class AddTenant extends Login{
	private static final Logger log = Logger.getLogger(AddTenant.class.getName());
	
	private static String testScript;
	private static WebDriver driver;

	public static void main(String[] args){
		String threanN=null;
		if(args.length>0){
			threanN= args[0];
		}
		
		testScript=RunTestSuite.getTestScript(threanN);
		NamedNodeMap testData=getTestData(testScript);
	//	printTestData(testData);
		
		
		driver=RunTestSuite.getDriver(threanN);
			
		loginToOD(threanN,driver);
		clickComponent(driver,"Service Provisioning");
		driver.switchTo().frame("topFrame");
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("blue_bold")));		
       driver.findElement(By.id("fili")).click();        
       
       driver.switchTo().defaultContent();
       driver.switchTo().frame("mainFrame");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("heading")));
		
	
        
        closeWindow(threanN);
        RunTestSuite.testCasePass.put(testScript, "true");
	}

}
