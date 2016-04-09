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

public class SchedulePage {
	WebDriver driver;
	
	@FindBy(xpath="//a[contains(text(),'Call Monitoring')]")
	private WebElement callMonitoringTab; 
	
	@FindBy(xpath="//a[contains(text(),'Schedule')]")
	private WebElement scheduleTab; 
	
	@FindBy(xpath="//td/a/img[contains(@class,'IMG')]")
	private WebElement listView;
	
	@FindBy(xpath="//td/img[contains(@class,'IMG')]")
	private WebElement calenderView;
	
	@FindBy(xpath="//td[@class='heading' and normalize-space(.)='Monitoring Schedule - List View']")
	private WebElement listViewHeader;
	
	@FindBy(xpath="//td[@class='heading' and normalize-space(.)='Monitoring Schedule - Calendar View']")
	private WebElement calenderViewHeader;
	
	@FindBy(id="newStrategy")
	private WebElement addButton;
	
	@FindBy(xpath="//td[@class='heading' and normalize-space(.)='New Monitoring Schedule']")
	private WebElement addStrategyHeading;
	
	@FindBy(id="editStrategy")
	private WebElement editButton;
	
	@FindBy(id="deleteStrategy")
	private WebElement deleteButton;
	
	@FindBy(id="copyStrategy")
	private WebElement copyButton;
	
	@FindBy(id="activateStrategy")
	private WebElement activateButton;
	
	@FindBy(id="deactivateStrategy")
	private WebElement deactivateButton;
	
	@FindBy(name="name")
	private WebElement nameTextBox;
	
	@FindBy(id="startTime")
	private WebElement startTimeTextBox;
	
	@FindBy(id="endTime")
	private WebElement endTimeTextBox;
	
	@FindBy(name="daily")
	private WebElement dailyCheckBox;	
	@FindBy(name="monday")
	private WebElement monCheckBox;
	@FindBy(name="tuesday")
	private WebElement tueCheckBox;
	@FindBy(name="wednesday")
	private WebElement wedCheckBox;
	@FindBy(name="thursday")
	private WebElement thuCheckBox;
	@FindBy(name="friday")
	private WebElement friCheckBox;
	@FindBy(name="saturday")
	private WebElement satCheckBox;
	@FindBy(name="sunday")
	private WebElement sunCheckBox;
	
	
	@FindBy(id="is_active")
	private WebElement activeCheckBox;
	
	@FindBy(name="callbackNumber")
	private WebElement callBackNumberTextBox;	
	@FindBy(xpath="//label[contains(text(),'Other')]")
	private WebElement otherFormatRadioButton;	
	@FindBy(xpath="//label[contains(text(),' U.S. Format')]")
	private WebElement usFormatRadioButton;
	
	@FindBy(id="vteamIds")
	private WebElement vTeamListBox;
	@FindBy(id="siteIds")
	private WebElement siteListBox;
	@FindBy(id="teamIds")
	private WebElement teamListBox;
	@FindBy(id="agent")
	private WebElement agentListBox;
	
	
	
	@FindBy(xpath="//tbody/tr[9]/td[2]/img")
	private WebElement startDateCalenderImg;
	
	
	@FindBy(xpath="//tbody/tr[9]/td[4]/img")
	private WebElement endDateCalenderImg;
	
	@FindBy(xpath="//a[contains(@class,'ui-state-default')]")
	private WebElement dateList;
	
	@FindBy(className="ui-datepicker-month")
	private WebElement monthList;
	
	@FindBy(className="ui-datepicker-year")
	private WebElement yearList;
		
	@FindBy(name="saveStrategy")
	private WebElement saveButton;
	
	@FindBy(className="cancel_button_en")
	private WebElement cancelButton;
	
	
	@FindBy(className="success_msg")
	private WebElement successMessage;
	
	
	public SchedulePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
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
	
	public boolean clickListView(){
		try{
			listView.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(listViewHeader));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean clickCalenderView(){
		try{
			calenderView.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(calenderViewHeader));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean clickAddButton(){
		try{
			addButton.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(addStrategyHeading));
//			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(nameTextBox));		
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	

	
	
	public boolean setName(String strategyName){
		try{
			nameTextBox.clear();
			nameTextBox.sendKeys(strategyName);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setStartTime(String startTime){
		try{
			startTimeTextBox.clear();
			startTimeTextBox.sendKeys(startTime);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setEndTime(String endTime){
		try{
			endTimeTextBox.clear();
			endTimeTextBox.sendKeys(endTime);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public boolean setDaysOfWeek(String days){
		String[] daysArr = days.split(";");
		try{
			for(String day : daysArr){
				if(day.equalsIgnoreCase("daily")){
					dailyCheckBox.click();
				}				
				if(day.equalsIgnoreCase("mon")){
					monCheckBox.click();
				}
				if(day.equalsIgnoreCase("tue")){
					tueCheckBox.click();
				}
				if(day.equalsIgnoreCase("wed")){
					wedCheckBox.click();
				}
				if(day.equalsIgnoreCase("thu")){
					thuCheckBox.click();
				}
				if(day.equalsIgnoreCase("fri")){
					friCheckBox.click();
				}
				if(day.equalsIgnoreCase("sat")){
					satCheckBox.click();
				}
				if(day.equalsIgnoreCase("sun")){
					sunCheckBox.click();
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setCallBackNumber(String dn){
		try{
			callBackNumberTextBox.clear();
			callBackNumberTextBox.sendKeys(dn);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}	
	
	
	
	public boolean setStartDate(String dateformat){
		String[] dateArr = dateformat.split("/");
		String date=dateArr[0];
		String month=dateArr[1];
		String year=dateArr[2];
		try{
			startDateCalenderImg.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(yearList));
			selectOption(year,yearList);
			selectOption(month,monthList);
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(dateList));
			driver.findElement(By.xpath("//a[contains(@class,'ui-state-default') and .//text()='" +date+ "']")).click();	
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean setEndDate(String dateformat){
		String[] dateArr = dateformat.split("/");
		String date=dateArr[0];
		String month=dateArr[1];
		String year=dateArr[2];
		try{
			endDateCalenderImg.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(yearList));
			selectOption(year,yearList);		
			selectOption(month,monthList);		
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(dateList));
			driver.findElement(By.xpath("//a[contains(@class,'ui-state-default') and .//text()='" +date+ "']")).click();	
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean selectVTeam(List<String> vTeamList){		
		try{
		Select vTeamSelect = new Select(vTeamListBox);
		vTeamSelect.deselectAll();
		
        List<WebElement> lstOptions=vTeamListBox.findElements(By.tagName("option"));
        vTeamListBox.sendKeys(Keys.CONTROL);    
            
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
		Select siteSelect = new Select(siteListBox);
		siteSelect.deselectAll();
		
        List<WebElement> lstOptions=siteListBox.findElements(By.tagName("option"));
        siteListBox.sendKeys(Keys.CONTROL);    
            
        for(int j=0 ; j < siteList.size() ; j++ ){    
        	if(j==0){
        		siteListBox.sendKeys(siteList.get(j));	       
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
		Select teamSelect = new Select(teamListBox);
		teamSelect.deselectAll();
		
        List<WebElement> lstOptions=teamListBox.findElements(By.tagName("option"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(lstOptions));
		teamListBox.sendKeys(Keys.CONTROL);    
            
        for(int j=0 ; j < teamList.size() ; j++ ){    
        	if(j==0){
        		teamListBox.sendKeys(teamList.get(j));	       
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
		Select agentSelect = new Select(agentListBox);
		agentSelect.deselectAll();
		
        List<WebElement> lstOptions=agentListBox.findElements(By.tagName("option"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(lstOptions));
		agentListBox.sendKeys(Keys.CONTROL);    
            
        for(int j=0 ; j < agentList.size() ; j++ ){    
        	if(j==0){
        		agentListBox.sendKeys(agentList.get(j));	       
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

	
	public boolean clickSave(){
		try{
			saveButton.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean clickCancel(){
		try{
			cancelButton.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean validateServerResponce(String expected){
		try{
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(successMessage));
			String actual = successMessage.getText();
			if(actual.equalsIgnoreCase(expected)){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	private void selectOption(String option, WebElement listEle) {
		Select selEle = new Select(listEle);
		selEle.selectByVisibleText(option);		
	}
	
}
