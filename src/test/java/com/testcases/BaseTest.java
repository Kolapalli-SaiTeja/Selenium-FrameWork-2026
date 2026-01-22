package com.testcases;

import org.testng.annotations.BeforeClass;

import com.base.Baseclass;
import com.dataprovider.ConfigUtility;
import com.pages.DashboardPage;
import com.pages.LoginPage;

public class BaseTest extends Baseclass{
	
	DashboardPage dashboard;
	
	
	@BeforeClass
	public void loginToApp() {

		LoginPage login = new LoginPage(driver);

		dashboard = login.loginToApplication(ConfigUtility.readProperty("username"),ConfigUtility.readProperty("password"));
			
}
	
	
	
	
	
	
}