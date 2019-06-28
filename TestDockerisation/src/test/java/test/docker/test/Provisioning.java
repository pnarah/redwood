package test.docker.test;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;



public class Provisioning{
	
	private static final Logger logger = LogManager.getLogger(Provisioning.class.getName());
	
	public WebDriverWait driverWait = null;
	static WebDriver driver = null;
	private String baseDir=null;
	
	SoftAssert softAssert = new SoftAssert();	
	

	@BeforeSuite
	public void geckoConf() {
		System.setProperty("baseDir", System.getProperty("user.dir"));
		baseDir = System.getProperty("baseDir");
		System.setProperty("webdriver.gecko.driver", baseDir + "/lib/geckodriver");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, baseDir + "/log/firefoxlogs.txt");
	}
	
	
	@Test
	public void login() {
		try {
			Boolean headless = true;
			
			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.setHeadless(headless);

	        DesiredCapabilities dc = DesiredCapabilities.firefox();
	        dc.merge(fOptions);

	        
		    
			logger.info("Initiating gacko firefox driver with Proxy setting at ");
			driver = new RemoteWebDriver(new URL("http://172.17.0.3:4444/wd/hub"), dc);
			driverWait = new WebDriverWait(driver, 20);
			logger.info("getting google.com");
			driver.get("http://google.com");
			logger.info("maximizing window.....");
			driver.manage().window().maximize();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			softAssert.assertTrue(false, "Login to PORTAL failed!");
		}
	}
	
}
