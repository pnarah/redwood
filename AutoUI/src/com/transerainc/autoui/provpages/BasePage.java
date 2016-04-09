package com.transerainc.autoui.provpages;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BasePage {

	WebDriver driver;
	
	@FindBy(xpath="//a[contains(text(),'Sign Out')]")
	private WebElement lnkLogout;
	
	@FindBy(xpath="//a[contains(text(),'ACL Administrator')]")
	private WebElement lnkAcl;
	
	@FindBy(xpath="//a[contains(text(),'Service Provisioning')]")
	private WebElement lnkServProv;
	
	@FindBy(xpath="//a[contains(text(),'Operations Administrator')]")
	private WebElement lnkOpAdmin;
	
	@FindBy(xpath="//a[contains(text(),'Audit Trail')]")
	private WebElement lnkAuditTrail;
	
	@FindBy(xpath="//a[contains(text(),'Message Broadcast')]")
	private WebElement lnkMsgBroadcast;
	
	public BasePage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickLnkLogout(){
		
		lnkLogout.click();
		
	}
	
	public void clickLnkAcl(){
		
		lnkAcl.click();
		
		String windowHandle1;
		WebDriver popup1;
		
		Set windowIterator = driver.getWindowHandles();
		for(Iterator iter = windowIterator.iterator();iter.hasNext();) {
			 
			 System.out.println("In for loop");
			 windowHandle1 = (String)iter.next(); 
			 popup1 = driver.switchTo().window(windowHandle1);
			 if (popup1.getTitle().contains("Transera ACL Administration")) {
				 
				 break;
			  }
		}
		
	}
	
	public void clickLnkServProv(){
		
		lnkServProv.click();
		
		String windowHandle2;
		WebDriver popup2;
		
		Set windowIterator = driver.getWindowHandles();
		for(Iterator iter = windowIterator.iterator();iter.hasNext();) {
			 
			 System.out.println("In for loop");
			 windowHandle2 = (String)iter.next(); 
			 popup2 = driver.switchTo().window(windowHandle2);
			 if (popup2.getTitle().contains("Provisioning")) {
				 
				 break;
			  }
		}
		
	}
	
	public void clickLnkOpAdmin(){
		
		lnkOpAdmin.click();
		
		String windowHandle3;
		WebDriver popup3;
		
		Set windowIterator = driver.getWindowHandles();
		for(Iterator iter = windowIterator.iterator();iter.hasNext();) {
			 
			 System.out.println("In for loop");
			 windowHandle3 = (String)iter.next(); 
			 popup3 = driver.switchTo().window(windowHandle3);
			 if(popup3.getTitle().contains("Operations Dashboard Administrators")){
				 
				 break;
			 }
		}
		
	}
	
	public void clickLnkAuditTrail(){
		
		lnkAuditTrail.click();
		
	}
	public void clickLnkMsgBroadcast(){
		
		lnkMsgBroadcast.click();
		
	}
	

}
