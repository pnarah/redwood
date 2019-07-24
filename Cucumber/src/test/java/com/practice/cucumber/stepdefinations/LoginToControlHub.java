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

	@FindBy(xpath = "//ul/li[@title='Partner Admin, Partner Sales Admin, User']")
	WebElement partnerInfo;

	@FindBy(css = "button.md-button.ng-scope")
	WebElement saveForLaterBtn;

	@FindBy(css = "button.md-button.btn--user.dropdown-toggle")
	WebElement customerInfoBtn;

	@FindBy(xpath = "//li/a[@class='ng-scope' and contains(text(),'Sign Out')]")
	WebElement signOut;

	WebDriver driver;
	WebDriverWait driverWait;
	WebDriverWait driverWaitLong;

	@Given("^Open firefox browser and start application$")
	public void open_firefox_browser_and_start_application() throws Throwable {

		String baseDir = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", baseDir + "/libs/geckodriver");

		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, baseDir + "/log/firefoxlogs.txt");

		driver = new FirefoxDriver();
		driverWait = new WebDriverWait(driver, 15);
		driverWaitLong = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);

		driver.manage().window().maximize();
		driver.get("ddddddddd");

	}

	@When("^enter admin \"([^\"]*)\" with \"([^\"]*)\" to login page$")
	public void enter_admin_user_to_login_page(String user, String passcode) throws Throwable {

		driverWait.until(ExpectedConditions.visibilityOf(userEmailId)).clear();
		userEmailId.sendKeys(user);
		driverWait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		driverWait.until(ExpectedConditions.visibilityOf(pcode)).clear();
		pcode.sendKeys(passcode);
		driverWait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

	}

	@Then("^admin \"([^\"]*)\" in landing page$")
	public void admin_in_landing_page(String userType) throws Throwable {
		if (userType.equalsIgnoreCase("partner")) {
			driverWaitLong.until(ExpectedConditions.elementToBeClickable(startTrialButton));
		} else {
			driverWaitLong.until(ExpectedConditions.elementToBeClickable(saveForLaterBtn)).click();
		}
	}

	@Then("^logout \"([^\"]*)\" from control hub$")
	public void logout_from_control_hub(String userType) throws Throwable {
		if (userType.equalsIgnoreCase("partner")) {
			driverWait.until(ExpectedConditions.elementToBeClickable(partnerInfo)).click();
		} else {
			driverWait.until(ExpectedConditions.elementToBeClickable(customerInfoBtn)).click();
		}
		driverWait.until(ExpectedConditions.elementToBeClickable(signOut)).click();
		driverWait.until(ExpectedConditions.visibilityOf(userEmailId));
		driver.quit();
	}
}
