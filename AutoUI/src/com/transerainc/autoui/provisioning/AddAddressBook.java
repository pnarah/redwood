package com.transerainc.autoui.provisioning;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.w3c.dom.NamedNodeMap;


import com.transerainc.autoui.executor.RunTestSuite;
import com.transerainc.autoui.provpages.AddressBookPage;
import com.transerainc.autoui.provpages.BasePageTenantProv;
import com.transerainc.autoui.common.Login;

public class AddAddressBook extends Login {
	
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
			
			
			driver=RunTestSuite.getDriver(threanN);
				
			loginToOD(threanN,driver);
			
	//		clickComponent(driver,"Service Provisioning");
			
		//end
		
//		String un = XMLUtil.readDataFromXML("ChangesBeforeExecution.xml", "changesBeforeExecution", "UserName");
//		String pw = XMLUtil.readDataFromXML("ChangesBeforeExecution.xml", "changesBeforeExecution", "Password");
//		String tenantName = XMLUtil.readDataFromXML("ChangesBeforeExecution.xml","changesBeforeExecution","tenant");
	
//		LoginPage LoginObject = new LoginPage(driver);
//		LoginObject.login(un,pw );
		
		BasePageTenantProv BasePageTenantProvObject = new BasePageTenantProv(driver);
		BasePageTenantProvObject.clickLnkServProv();
		
		
		String tenantName= testData.getNamedItem("tenant").getNodeValue();
		
		BasePageTenantProvObject.doubleClickTenantName(tenantName);
		BasePageTenantProvObject.clickAddressBookLink();
		BasePageTenantProvObject.switchMainFrame();
		
		String addrBookName =testData.getNamedItem("addrBookName").getNodeValue();
		String addrBookDesc =testData.getNamedItem("addrBookDesc").getNodeValue();
		String entryName=testData.getNamedItem("entryName").getNodeValue();
		String phNo =testData.getNamedItem("phNo").getNodeValue();
		String anotherEntryName =testData.getNamedItem("anotherEntryName").getNodeValue();
		String anotherPhNo=testData.getNamedItem("anotherPhNo").getNodeValue();
		String expected=testData.getNamedItem("expected").getNodeValue();
		
		AddressBookPage AddressBookObject = new AddressBookPage(driver);
		AddressBookObject.clickAddAddrBookButton();
		AddressBookObject.enterAddrBookName(addrBookName);
		AddressBookObject.enterAddrBookDesc(addrBookDesc);
		AddressBookObject.clickAddEntryButton();
		AddressBookObject.doubleClickNameGrid();
		AddressBookObject.enterName(entryName);
		AddressBookObject.doubleClickPhNoGrid();
		AddressBookObject.doubleClickPhNoGrid();
		AddressBookObject.enterPhNo(phNo);
		AddressBookObject.clickAddEntryButton();
		AddressBookObject.doubleClickNameGrid();
		AddressBookObject.enterAnotherName(anotherEntryName);
		AddressBookObject.doubleClickPhNoGrid();
		AddressBookObject.doubleClickPhNoGrid();
		AddressBookObject.enterAnotherPhNo(anotherPhNo);
		AddressBookObject.clickAddrBookSaveButton();
		
		boolean isP = AddressBookObject.verifyAddAddrBookMsg(expected);
		
		BasePageTenantProvObject.closeProvPage();
		
//        closeWindow(threanN);
        RunTestSuite.testCasePass.put(testScript, String.valueOf(isP));
		
	}
}
