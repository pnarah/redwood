package com.transerainc.autoui.rs;


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


public class AddRoutingStrategy extends Login{
	private static final Logger log = Logger.getLogger(AddRoutingStrategy.class.getName());
	
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
			
		loginToCD(threanN,driver);
		clickComponent(driver,"Routing Strategy");
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("welcome_msg")));
       driver.findElement(By.xpath("//nobr[contains(text(),'Call Routing')]")).click();        
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("heading")));
        driver.findElement(By.partialLinkText("Routing Strategies"));
        driver.findElement(By.id("ext-gen9")).click();
        driver.switchTo().activeElement();        
        driver.findElement(By.xpath("//div[contains(text(),'EP_Apple_Inbound_4')]")).click();
        closeWindow(threanN);
        RunTestSuite.testCasePass.put(testScript, "true");
	}

}
