package com.base;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.dataprovider.ConfigUtility;
import com.factory.BrowserFactory;

public class Baseclass {
	
	protected WebDriver driver;

	@BeforeClass
	public void setup() {
		
		System.out.println("running before class");

		String browser = ConfigUtility.readProperty("browser");

		String appURL = ConfigUtility.readProperty("qaurl");

		driver = BrowserFactory.startBrowser(browser, appURL + "/login");

		
	}

	
	@AfterClass
	public void tearDown() {

		System.out.println("Running after class");
		
		if (driver != null) {
            driver.quit();
        }

	}

}
