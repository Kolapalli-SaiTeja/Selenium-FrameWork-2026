package com.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.dataprovider.ConfigUtility;
import com.factory.BrowserFactory;
import com.pages.DashboardPage;
import com.pages.LoginPage;

public class Baseclass {


	
	public WebDriver getDriver() {

		return BrowserFactory.getDriver();
	}

	@Parameters("browser")

	@BeforeClass
	public void setup(String browser) {

		String appURL = ConfigUtility.readProperty("qaurl");
		
	//	String browser = ConfigUtility.readProperty("browser");

		BrowserFactory.startBrowser(browser, appURL + "/login");

	}

	@AfterClass(alwaysRun = true)
	
	public void tearDown() {
		
		getDriver().quit();
		
		BrowserFactory.unload();
	}

	protected DashboardPage doLogin(String email, String password) {
		
		LoginPage login = new LoginPage(getDriver());
		
		return login.loginToApplication(email, password);
	}

}
