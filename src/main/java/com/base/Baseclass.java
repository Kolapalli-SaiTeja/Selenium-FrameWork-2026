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

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public WebDriver getDriver() {

		return BrowserFactory.getDriver();
	}

//	@Parameters("browser")

	@BeforeClass
	public void setup() {

		String appURL = ConfigUtility.readProperty("qaurl");
		
		String browser = ConfigUtility.readProperty("browser");

		WebDriver localDriver = BrowserFactory.startBrowser(browser, appURL + "/login");

		driver.set(localDriver);
	}

	@AfterClass(alwaysRun = true)
	
	public void tearDown() {
		
		getDriver().quit();
		
		driver.remove();
	}

	protected DashboardPage doLogin(String email, String password) {
		
		LoginPage login = new LoginPage(getDriver());
		
		return login.loginToApplication(email, password);
	}

}
