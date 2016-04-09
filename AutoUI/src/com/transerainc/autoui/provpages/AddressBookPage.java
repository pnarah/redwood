package com.transerainc.autoui.provpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddressBookPage extends BasePageTenantProv{
	
	@FindBy(xpath="//input[starts-with(@onclick,'addAgentAddress')]")
	private WebElement addAddrBookButton;
	
	@FindBy(id="ext-comp-1001")
	private WebElement addrBookNameTxtBox;
	
	@FindBy(id="ext-comp-1002")
	private WebElement addrBookDescTxtBox;
	
	@FindBy(id="ext-gen103")
	private WebElement addEntryButton;
	
	@FindBy(id="ext-gen111")
	private WebElement deleteEntryButton;
	
	@FindBy(css="div.x-grid-col-1.x-grid-cell-inner > div.x-grid-cell-text")
	private WebElement nameGrid;
	
	@FindBy(id="ext-comp-1003")
	private WebElement nameTxtBox;
	
	@FindBy(css="div.x-grid-col-2.x-grid-cell-inner > div.x-grid-cell-text")
	private WebElement phNoGrid;
	
	@FindBy(id="ext-comp-1005")
	private WebElement phNoTxtBox; 
	
	@FindBy(xpath="//div[text()='snow']")
	private WebElement entryListGrid;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement addrBookSaveButton;
	
	@FindBy(xpath="//button[text()='Update']")
	private WebElement addrBookUpdateButton;
	
	@FindBy(id="ext-gen34")
	private WebElement addrBookCancelButton;
	
	@FindBy(xpath="//input[@class='edit_button']")
	private WebElement editAddrBookButton;
	
	@FindBy(name="bDelete")
	private WebElement deleteAddrBookButton;
	
	@FindBy(xpath="//span[contains(text(),'Added address details successfully')]")
	private WebElement addAddrBookMsgSuccess;
	
	@FindBy(xpath="//span[contains(text(),'Edited address details successfully')]")
	private WebElement editAddrBookMsgSuccess;
	
	@FindBy(xpath="//span[contains(text(),'This Address already exists ! Please enter a different name.')]")
	private WebElement duplicateAddrBookErrorMsg;
	
	@FindBy(xpath="//span[contains(text(),'Deleted address details successfully')]")
	private WebElement deleteAddrBookMsgSuccess;
	
	public AddressBookPage(WebDriver driver){
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickAddAddrBookButton(){
		
		addAddrBookButton.click();
	}
	
	public void clickEditAddrBookButton(){
		
		editAddrBookButton.click();
	}
	
	public void clickAddrBookRadioButton(String selectAddrBook){
		
		String xp ="//tr[td[a[text()='"+selectAddrBook+"']]]/td[1]/input";
		driver.findElement(By.xpath(xp)).click();
	}
	
	public void clickDeleteAddrBookButton(){
		
		deleteAddrBookButton.click();
	}
	
	public void enterAddrBookName(String addrBookName){
		
		addrBookNameTxtBox.clear();
		addrBookNameTxtBox.sendKeys(addrBookName);
	}
	
	public void enterAddrBookDesc(String addrBookDesc){
		
		addrBookDescTxtBox.clear();
		addrBookDescTxtBox.sendKeys(addrBookDesc);
	}
	
	public void clickAddEntryButton(){
		
		addEntryButton.click();
	}
	
	public void clickdeleteEntryButton(){
		
		deleteEntryButton.click();
		WebElement popup = driver.findElement(By.xpath("//div[text()='Confirm Delete']"));
		if(popup.isDisplayed()){
		
		System.out.println("popup is displayed");
		String msg = popup.getText();
		System.out.println(msg);
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		}
	}
	
	public void doubleClickNameGrid(){
		
		Actions action1 = new Actions(driver);
		action1.doubleClick(nameGrid).perform();
	}
	
	public void doubleClickPhNoGrid(){
		
		Actions action1 = new Actions(driver);
		action1.doubleClick(phNoGrid);
		action1.perform();
	}
	
	public void enterName(String entryName){
		
		nameTxtBox.clear();
		nameTxtBox.sendKeys(entryName);
	}
	
	public void enterAnotherName(String anotherEntryName){
		
		nameTxtBox.clear();
		nameTxtBox.sendKeys(anotherEntryName);
	}
	
	public void enterPhNo(String phNo){
		
		phNoTxtBox.clear();
		phNoTxtBox.sendKeys(phNo);
	}
	
	public void enterAnotherPhNo(String anotherPhNo){
		
		phNoTxtBox.clear();
		phNoTxtBox.sendKeys(anotherPhNo);
	}
	
	
	public void clickNameGrid(){
		
		entryListGrid.click();
	}
	
	public void clickAddrBookSaveButton(){
		
		addrBookSaveButton.click();
	}
	
	public void clickAddrBookUpdateButton(){
		
		addrBookUpdateButton.click();
	}
	
	public void clickAddrBookCancelButton(){
		
		addrBookCancelButton.click();
	}
	
	public boolean verifyAddAddrBookMsg(String expected){
		
		String actual = addAddrBookMsgSuccess.getText();
		 Assert.assertEquals(actual, expected);
		 return actual.equalsIgnoreCase(expected);
	}
	
	public void verifyEditAddrBookMsg(String expected){
		
		String actual = editAddrBookMsgSuccess.getText();
		Assert.assertEquals(actual, expected);
	}
	
	public void verifyDuplicateAddrBookErrorMsg(String expected){
		
		String actual = duplicateAddrBookErrorMsg.getText();
		Assert.assertEquals(actual, expected);
	}
	
	public void verifyDeleteAddrBookMsg(String expectedMsg){
		
		String actualMsg = deleteAddrBookMsgSuccess.getText();
		Assert.assertEquals(actualMsg, expectedMsg);
	}
}
