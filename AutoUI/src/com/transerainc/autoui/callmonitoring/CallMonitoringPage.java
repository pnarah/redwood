package com.transerainc.autoui.callmonitoring;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * ***********************
 * @author Prasanta Narah
 * ***********************
 */


public class CallMonitoringPage{
	WebDriver driver;
	
	@FindBy(xpath="//div[contains(@class,'welcome_msg')]")
	private WebElement welcomeMsg; 
	
	@FindBy(xpath="//div[@id='footer']/div[contains(text(),'Copyright')]")
	private WebElement copyRight; 
	
	@FindBy(xpath="//a[contains(text(),'Close Module')]")
	private WebElement closeModule; 	
	
	@FindBy(xpath="//a[contains(text(),'Help')]")
	private WebElement help; 
	
	
	@FindBy(xpath="//a[contains(text(),'Call Monitoring')]")
	private WebElement callMonitoringTab; 
	
	@FindBy(xpath="//a[contains(text(),'Schedule')]")
	private WebElement scheduleTab; 
	
	
	@FindBy(xpath="//select[@id='listVirtualTeam']")
	private WebElement listVirtualTeam;
	
	@FindBy(xpath="//select[@id='listSite']")
	private WebElement listSite;
	
	@FindBy(xpath="//select[@id='listTeam']")
	private WebElement listTeam;
	
	@FindBy(xpath="//select[@id='listAgent']")
	private WebElement listAgent;
	
	@FindBy(xpath="//input[@id='receivingPhNo']")
	private WebElement callBackNumber;
	
	@FindBy(xpath="//input[@id='blindMonitor']")
	private WebElement invisibleMode;
	
	
	@FindBy(xpath="//label[contains(text(),'Other')]")
	private WebElement otherFormat;
	
	@FindBy(xpath="//label[contains(text(),' U.S. Format')]")
	private WebElement usFormat;
	
	@FindBy(xpath="//input[@name='sublisten']")
	private WebElement monitorNextCall;
	
	@FindBy(xpath="//input[@name='submidcall']")
	private WebElement midCallMonitor;
	
	@FindBy(xpath="//input[@name='subcontmonitor']")
	private WebElement continuousMonitor;
	
	
	public CallMonitoringPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean welcomeMsgIsDisplayed(){
		return welcomeMsg.isDisplayed();
	}
	
	public boolean helpLinkIsDisplayed(){
		return help.isDisplayed();
	}
	
	public boolean closeModuleLinkIsDisplayed(){
		return closeModule.isDisplayed();
	}
	
	public boolean copyRightIsDisplayed(){
		return copyRight.isDisplayed();
	}
	
	public boolean validateCopyRightMessage(String expectedMsg){
		String actualMsg = copyRight.getText().trim();
		return actualMsg.equalsIgnoreCase(expectedMsg);
	}
	
	
	public boolean clickCallMonitoringTab(){
		try{
		callMonitoringTab.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("listVirtualTeam")));
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean clickScheduleTab(){
		try{
		scheduleTab.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("heading")));
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean selectVTeam(List<String> vTeamList){		
		try{
		Select vTeamSelect = new Select(listVirtualTeam);
		vTeamSelect.deselectAll();
		
        List<WebElement> lstOptions=listVirtualTeam.findElements(By.tagName("option"));
        listVirtualTeam.sendKeys(Keys.CONTROL);    
            
        for(int j=0 ; j < vTeamList.size() ; j++ ){    
        	    for(int i=0;i<lstOptions.size();i++){
        	    	if(vTeamList.get(j).equals(lstOptions.get(i).getText())){
        	    		lstOptions.get(i).click();
        	    		break;
        	    	}
        	    }   			
        }		
		return true;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean selectSite(List<String> siteList){		
		try{
		Select siteSelect = new Select(listSite);
		siteSelect.deselectAll();
		
        List<WebElement> lstOptions=listSite.findElements(By.tagName("option"));
        listSite.sendKeys(Keys.CONTROL);    
            
        for(int j=0 ; j < siteList.size() ; j++ ){    
        	if(j==0){
        		listSite.sendKeys(siteList.get(j));	       
        	}else{
        	    for(int i=0;i<lstOptions.size();i++){
        	    	if(siteList.get(j).equals(lstOptions.get(i).getText())){
        	    		lstOptions.get(i).click();
        	    		break;
        	    	}
        	    }   		
        	}
        }		
		return true;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	

	public boolean selectTeam(List<String> teamList){		
		try{
		Select teamSelect = new Select(listTeam);
		teamSelect.deselectAll();
		
        List<WebElement> lstOptions=listTeam.findElements(By.tagName("option"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(lstOptions));
        listTeam.sendKeys(Keys.CONTROL);    
            
        for(int j=0 ; j < teamList.size() ; j++ ){    
        	if(j==0){
        		listTeam.sendKeys(teamList.get(j));	       
        	}else{
        	    for(int i=0;i<lstOptions.size();i++){
        	    	if(teamList.get(j).equals(lstOptions.get(i).getText())){
        	    		lstOptions.get(i).click();
        	    		break;
        	    	}
        	    }   		
        	}
        }		
		return true;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean selectAgent(List<String> agentList){		
		try{
		Select agentSelect = new Select(listAgent);
		agentSelect.deselectAll();
		
        List<WebElement> lstOptions=listAgent.findElements(By.tagName("option"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(lstOptions));
        listAgent.sendKeys(Keys.CONTROL);    
            
        for(int j=0 ; j < agentList.size() ; j++ ){    
        	if(j==0){
        		listAgent.sendKeys(agentList.get(j));	       
        	}else{
        	    for(int i=0;i<lstOptions.size();i++){
        	    	if(agentList.get(j).equals(lstOptions.get(i).getText())){
        	    		lstOptions.get(i).click();
        	    		break;
        	    	}
        	    }   		
        	}
        }		
		return true;	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean setCallBackNumber(String number){
		try{
			callBackNumber.clear();
			callBackNumber.sendKeys(number);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}
	
	public boolean setInvisibleMode(){
		try{
			invisibleMode.clear();
			invisibleMode.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean setOtherFormat(){
		try{
			otherFormat.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setUSFormat(){
		try{
			usFormat.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean placeMonitorNextCall(){
		try{
			monitorNextCall.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public boolean placeMidCallMonitor(){
		try{
			midCallMonitor.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean placeContinuousMonitor(){
		try{
			continuousMonitor.click();
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//button[contains(text(),'Monitor')]")).click();;
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
