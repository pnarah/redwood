package com.transerainc.autoui.provpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class BasePageTenantProv extends BasePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//a[starts-with(text(),'Tenant Prov')]")
	private WebElement tenantProvLink;
	
	@FindBy(xpath="//span[text()='Transera Tenants']")
	private WebElement transeraTenantsLink;
	
	@FindBy(xpath="//span[text()='Tenant DID XML']")
	private WebElement tenantDIDXMLLink;
	
	@FindBy(xpath="//span[text()='Tenant Config XML']")
	private WebElement tenantConfigXMLLink;
	
	@FindBy(xpath="//span[text()='CAD Variables']")
	private WebElement cadVariablesLink;
	
	@FindBy(xpath="//span[text()='Agent Profiles']")
	private WebElement agentProfilesLink;
	
	@FindBy(xpath="//span[text()='Address Book']")
	private WebElement addressBookLink;
	
	@FindBy(xpath="//span[text()='Threshold Rules']")
	private WebElement thresholdRulesLink;
	
	@FindBy(xpath="//span[text()='Business Metrics']")
	private WebElement businessMetricsLink;
	
	@FindBy(xpath="//span[text()='Work Type']")
	private WebElement workTypeLink;
	
	@FindBy(xpath="//span[text()='DN to Entry Point Mapping']")
	private WebElement dnToEpMappingLink;
	
	@FindBy(xpath="//span[text()='EP Queue Group']")
	private WebElement epQueueGroupLink;
	
	@FindBy(xpath="//span[text()='Password Policy']")
	private WebElement passwordPolicyLink;
	
	@FindBy(xpath="//span[contains(text(),'Multimedia Extensions')]")
	private WebElement multiMediaExtensionLink;
	
	@FindBy(xpath="//span[text()='Multimedia Profiles']")
	private WebElement multiMediaProfLink;
	
	@FindBy(xpath="//span[text()='Outdial ANI']")
	private WebElement outdialAniLink;
	
	@FindBy(xpath="//span[text()='Sites']")
	private WebElement sites;
	
	@FindBy(xpath="//span[text()='Agents']")
	private WebElement agentLink; 
	
	@FindBy(xpath="//span[text()='Agent Bulk Delete']")
	private WebElement agentBulkDeleteLink;
	
	@FindBy(xpath="//span[text()='Sites']/../../..//following-sibling::li//span[text()='Agent Profiles']")
	private WebElement siteSpecificAgentProfileLink;
	
	@FindBy(xpath="//span[text()='Sites']/../../..//following-sibling::li//span[text()='Address Book']")
	private WebElement siteSpecificAddressBookLink;
	
	@FindBy(xpath="//span[text()='Teams']")
	private WebElement teamLink;
	
	@FindBy(xpath="//span[text()='Inbound Entry Points']")
	private WebElement inboundEntryPointsLink;
	
	@FindBy(xpath="//span[text()='Inbound Queues']")
	private WebElement inboundQueuesLink;
	
	@FindBy(xpath="//span[text()='Outdial Entry Points']")
	private WebElement outdialEntryPointsLink;
	
	@FindBy(xpath="//span[text()='Outdial Queues']")
	private WebElement outdialQueuesLink;
	
	@FindBy(xpath="//span[text()='Skills  Assignment']")
	private WebElement skillsAssignmentLink;
	
	@FindBy(xpath="//span[text()='Skill Definitions']")
	private WebElement skillDefinitionsLink;
	
	@FindBy(xpath="//span[contains(text(),'Skill Profiles')]")
	private WebElement skillProfilesLink;
	
	@FindBy(xpath="//a[starts-with(text(),'Close Module')]")
	private WebElement closeLink;
	
	
	public BasePageTenantProv(WebDriver driver) {
			
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	public void switchMainFrame(){
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainFrame");
	}
	
	public void clickTenantProv(){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			tenantProvLink.click();
	}
	
	
	public void doubleClickTenantLink(){
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(transeraTenantsLink).perform();
			driver.switchTo().defaultContent();
			
	}
	
	public void doubleClickTenantName(String tenantName){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			int i=0;
			String xp = "";
			for(i=1;i<100;i++) {
				 xp = "//html/body/div/ul/li/ul/li["+i+"]/div/a/span";
					if(tenantName.equalsIgnoreCase(driver.findElement(By.xpath(xp)).getText()))
					break;
			}
			
			Actions action1 = new Actions(driver);
			action1.doubleClick(driver.findElement(By.xpath(xp))).perform();
			driver.switchTo().defaultContent();
	}
	
	public void clickTenantDIDXMLLink(){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			tenantDIDXMLLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickTenantConfigXMLLink(){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			tenantConfigXMLLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickCadVariablesLink(){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			cadVariablesLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickAgentProfilesLink(){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			agentProfilesLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickAddressBookLink(){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			addressBookLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickThresholdRulesLink(){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			thresholdRulesLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickBusinessMetricsLink(){
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			businessMetricsLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickWorkTypeLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			workTypeLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickDnToEpMappingLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			dnToEpMappingLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickEpQueueGroupLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			epQueueGroupLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickPasswordPolicyLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			passwordPolicyLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickMultiMediaExtensionLink(){
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			multiMediaExtensionLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickMultiMediaProfLink(){
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			multiMediaProfLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickOutdialAniLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			outdialAniLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickSite(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
		    action1.doubleClick(sites).perform();;
		    driver.switchTo().defaultContent();
	}
	
	public void doubleClickSiteName(String siteName){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(driver.findElement(By.linkText(siteName))).perform();
			driver.switchTo().defaultContent();
	}
	
	public void clickAgentLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			agentLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickAgentBulkDeleteLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			agentBulkDeleteLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickSiteSpecificAgentProfileLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			siteSpecificAgentProfileLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickSiteSpecificAddressBookLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			siteSpecificAddressBookLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickTeam(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 =new Actions(driver);
			action1.doubleClick(teamLink).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickTeamName(String teamName){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(driver.findElement(By.linkText(teamName))).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickInboundEntryPointLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 =new Actions(driver);
			action1.doubleClick(inboundEntryPointsLink).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickInboundEntryPointName(String inEpName){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(driver.findElement(By.linkText(inEpName))).perform();
			driver.switchTo().defaultContent();
			
//			int i=0;
//			String xp = "/html/body/div/ul/li/ul/li[1]/ul/li[13]/ul/li["+i+"]/div/a/span";
//			for(i=1;i<100;i++) {
//				xp = "";
//				if(inEpName.trim().equalsIgnoreCase(driver.findElement(By.xpath(xp)).getText().trim()))
//				break;
//		}
//			
//		
//			Actions action1 = new Actions(driver);
//			action1.doubleClick(driver.findElement(By.xpath(xp))).perform();
//			driver.switchTo().defaultContent();
	}
	
	public void doubleClickInboundQueueLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(inboundQueuesLink).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickInboundQueueName(String inQueueName){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(driver.findElement(By.linkText(inQueueName))).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickOutdialEntryPointLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(outdialEntryPointsLink).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickOutdialEntrypointName(String outEpName){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(driver.findElement(By.linkText(outEpName))).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickOutdialQueueLink(){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(outdialQueuesLink).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickOutdialQueueName(String outQueueName){
		
			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(driver.findElement(By.linkText(outQueueName))).perform();
			driver.switchTo().defaultContent();
	}
	
	public void doubleClickSkillAssignment(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			Actions action1 = new Actions(driver);
			action1.doubleClick(skillsAssignmentLink).perform();
			driver.switchTo().defaultContent();
	}
	
	public void clickSkillDefinitionsLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			skillDefinitionsLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void clickSkillProfilesLink(){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("leftFrame1");
			skillProfilesLink.click();
			driver.switchTo().defaultContent();
	}
	
	public void handlePopUp(String expected){
		
			String actual = driver.switchTo().alert().getText().trim();
			driver.switchTo().alert().accept();
			Assert.assertEquals(actual, expected);
	}
	
	public void closeProvPage(){
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topFrame");
			closeLink.click();
	}
}
            
		    
    
