package com.practice.cucumber.stepdefinations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginToControlHub {
	@FindBy(name = "email")
	WebElement userEmailId;

	@FindBy(css = "button.md-button.md-button--blue[type='submit']")
	WebElement submitButton;

	@FindBy(css = "input#IDToken2[name='IDToken2']")
	WebElement pcode;

	@FindBy(css = "button#Button1[name='Login.Submit']")
	WebElement loginButton;
	
	@FindBy(css = "button#trialAddButton.md-button.md-button--mint.right.ng-scope")
	WebElement startTrialButton;

	WebDriver driver;
	WebDriverWait driverWait;
	
	@Given("^Open firefox browser and start application$")
	public void open_firefox_browser_and_start_application() throws Throwable {
		
		String baseDir = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", baseDir+"/libs/geckodriver");
		
		driver = new FirefoxDriver();
		driverWait = new WebDriverWait(driver, 15);
		PageFactory.initElements(driver, this);

		driver.manage().window().maximize();
		driver.get("http://gmail.com");

	}

	@When("^enter admin user to login page$")
	public void enter_admin_user_to_login_page() throws Throwable {
		
		driverWait.until(ExpectedConditions.visibilityOf(userEmailId)).clear();
		userEmailId.sendKeys("applle@mail.com");
		driverWait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		driverWait.until(ExpectedConditions.visibilityOf(pcode)).clear();
		pcode.sendKeys("orange");
		driverWait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

	}

	@Then("^admin in landing page$")
	public void admin_in_landing_page() throws Throwable {
		driverWait.until(ExpectedConditions.elementToBeClickable(startTrialButton));
	}
}
