package com.transerainc.autoui.CDLandingPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */

public class CDLandingPage {
	WebDriver driver;
	
	@FindBy(xpath="//div/b[contains(text(),'Welcome')]")
	private WebElement welcomeMsg; 
	
	@FindBy(xpath="//div/center[contains(@class,'copyRight')]")
	private WebElement copyRight; 
	
	@FindBy(xpath="//a[contains(text(),'Sign Out')]")
	private WebElement logout; 
	
	@FindBy(xpath="//a[contains(text(),'Settings')]")
	private WebElement settings; 
	
	@FindBy(xpath="//a[contains(text(),'Alerts')]")
	private WebElement alerts; 
	
	@FindBy(xpath="//a[contains(text(),'Help')]")
	private WebElement help; 
	
	
	@FindBy(xpath="//a[contains(text(),'Real-Time Reports')]")
	private WebElement rtmc;
	

	@FindBy(xpath="//a[contains(text(),'Historical Reports')]")
	private WebElement hrs;
	
	@FindBy(xpath="//a[contains(text(),'Call Monitoring')]")
	private WebElement callMonitoring;
	
	@FindBy(xpath="//a[contains(text(),'Call Recording')]")
	private WebElement callRecording;
	
	
	@FindBy(xpath="//a[contains(text(),'Routing Strategy')]")
	private WebElement cds;
	
	
	@FindBy(xpath="//a[contains(text(),'Provisioning')]")
	private WebElement selfProvisioning;
	
	@FindBy(xpath="//a[contains(text(),'Password Reset')]")
	private WebElement passwordReset;
	
	@FindBy(xpath="//a[contains(text(),'Unlock Agent')]")
	private WebElement unlockAgent;
	
	@FindBy(xpath="//a[contains(text(),'Usage Metrics Report')]")
	private WebElement usageMetricsReport;
	
	@FindBy(xpath="//a[contains(text(),'Map View')]")
	private WebElement mapView;
	
	@FindBy(xpath="//a[contains(text(),'Web Callback Request Report')]")
	private WebElement webCallback;

	
	public CDLandingPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	// methods to check component  links are displayed
	public boolean helpLinkIsDisplayed(){
		return help.isDisplayed();
	}
	
	public boolean settingsLinkIsDisplayed(){
		return settings.isDisplayed();
	}
	
	public boolean alertsLinkIsDisplayed(){
		return alerts.isDisplayed();
	}
	
	public boolean logoutLinkIsDisplayed(){
		return logout.isDisplayed();
	}
	
	public boolean rtmcLinkIsDisplayed(){		
		return  rtmc.isDisplayed();
	}
	
	public boolean hrsLinkIsDisplayed(){
		return hrs.isDisplayed();
	}
	
	public boolean selfProLinkIsDisplayed(){
		return selfProvisioning.isDisplayed();
	}
	
	public boolean callMonitoringLinkIsDisplayed(){
		return callMonitoring.isDisplayed();
	}
	
	public boolean callRecordingLinkIsDisplayed(){
		return callRecording.isDisplayed();
	}
	
	
	public boolean routingStrategyLinkIsDisplayed(){
		return cds.isDisplayed();
	}
	
	public boolean passwordResetLinkIsDisplayed(){
		return passwordReset.isDisplayed();
	}
	
	public boolean unlockAgentLinkIsDisplayed(){
		return unlockAgent.isDisplayed();
	}
	
	public boolean usageMetricsReportLinkIsDisplayed(){
		return usageMetricsReport.isDisplayed();
	}
	
	public boolean mapViewLinkIsDisplayed(){
		return mapView.isDisplayed();
	}
	

	
	public boolean webCallBackLinkIsDisplayed(){
		return webCallback.isDisplayed();
	}
	
	public boolean welcomeMsgIsDisplayed(){
		return welcomeMsg.isDisplayed();
	}
	
	public boolean copyRightIsDisplayed(){
		return copyRight.isDisplayed();
	}
	
	public boolean validateCopyRightMessage(String expectedMsg){
		String actualMsg = copyRight.getText().trim();
		return actualMsg.equalsIgnoreCase(expectedMsg);
	}
	
}
