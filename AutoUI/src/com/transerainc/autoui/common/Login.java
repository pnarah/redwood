package com.transerainc.autoui.common;


import java.util.HashMap;
import java.util.Map;

import java.util.logging.Logger;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.transerainc.autoui.executor.RunTestSuite;
import com.transerainc.autoui.executor.StartExecution;

/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */

public class Login extends Common{
	private static final Logger logC = Logger.getLogger(Login.class.getName());
	
	static String odUrl = StartExecution.odUrl;
	static String odUser = StartExecution.odUserName;
	static String odPassword=StartExecution.odPassword;

	static String cdUrl = StartExecution.cdUrl;
	static String cdUser = StartExecution.cdUserName;
	static String cdPassword=StartExecution.cdPassword;
	static String tenant = StartExecution.tenant;
	
	private static Map<String, String> threadCDLoginMap =  new HashMap<String, String>();
	private static Map<String, String> threadODLoginMap =  new HashMap<String, String>();
	private static Map<String, String> parentWinHandlerMap = new HashMap<String, String>();
	

	
	public static void loginToOD(String tName, WebDriver wDriver){		
		
		String isLogedIn="false";
		
		if(threadODLoginMap.containsKey(tName)){
			isLogedIn=threadODLoginMap.get(tName);
		}

		if( isLogedIn.equals("false")){		
			
			try{
				
			wDriver.get(odUrl);
			new WebDriverWait(wDriver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
			
			wDriver.findElement(By.name("userName")).clear();
			wDriver.findElement(By.name("userName")).sendKeys(odUser);
			wDriver.findElement(By.name("userPassword")).clear();
			wDriver.findElement(By.name("userPassword")).sendKeys(odPassword);
			wDriver.findElement(By.name("Submit")).click();		
		
			new WebDriverWait(wDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("blue_bold")));


			parentWinHandlerMap.put(tName, wDriver.getWindowHandle());

			threadODLoginMap.put(tName, "true");
//			logC.info(tName+"- User successfully logedIn to OD");

			}catch (NoSuchElementException e){
				logC.info(tName+"---Login to OD exception----");
			}					
		}else{
//			logC.info(tName+"- User is already logedIn to OD");
		}
		
	}	
	
	
public static void loginToCD(String tName, WebDriver wDriver){		
		
		String isLogedIn="false";
		
		if(threadCDLoginMap.containsKey(tName)){
			isLogedIn=threadCDLoginMap.get(tName);
		}

		if( isLogedIn.equals("false")){		
			
			try{
				
			wDriver.get(cdUrl);
			new WebDriverWait(wDriver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
			
		    wDriver.switchTo().defaultContent();
			wDriver.findElement(By.name("userName")).clear();
			wDriver.findElement(By.name("userName")).sendKeys(cdUser);
			wDriver.findElement(By.name("password")).clear();
			wDriver.findElement(By.name("password")).sendKeys(cdPassword);
			wDriver.findElement(By.name("organization")).clear();
			wDriver.findElement(By.name("organization")).sendKeys(tenant);
			wDriver.findElement(By.name("Submit")).click();		
		
			new WebDriverWait(wDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("headerTextStyleLeft")));


			parentWinHandlerMap.put(tName, wDriver.getWindowHandle());

			threadCDLoginMap.put(tName, "true");

			}catch (NoSuchElementException e){
				System.out.println(tName+"---Login to CD exception----");
			}		
			
		}else{
//			logC.info(tName+"- Supervisor is already logedIn to CD");
		}
		
	}	

	
	public static void closeWindow(String tName){
		WebDriver wDriver=RunTestSuite.getDriver(tName);
		String parentWindowHandler = parentWinHandlerMap.get(tName);
			
		String handle = wDriver.getWindowHandle();
//		System.out.println(tName+"*********Closing Window***********"+wDriver.getTitle());
		
		if(!handle.equals(parentWindowHandler)){
			wDriver.close();			
			wDriver.switchTo().window(parentWindowHandler);
//			System.out.println(tName+"*********Switched to Parent Window***********"+wDriver.getTitle());			
		}else{
			wDriver.close();
			parentWinHandlerMap.remove(tName);
		}
	}
}
