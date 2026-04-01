package com.testcases;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.Baseclass;
import com.dataprovider.DataProviders;
import com.dataprovider.ExcelUtility;
import com.listeners.MyTestNGListeners;
import com.listeners.RetryAnalyzer;
import com.pages.DashboardPage;
import com.pages.LoginPage;

//@Listeners ({MyTestNGListeners.class})// alternate if u dnt want to use in testng.xml, not recommended
public class LoginTest extends Baseclass {

	LoginPage login;

	DashboardPage dashboard;

	@Test(dataProvider = "login", dataProviderClass = DataProviders.class, priority = 2, retryAnalyzer = RetryAnalyzer.class)

	public void validLogin(String email, String password) {

		login = new LoginPage(getDriver());

		dashboard = login.loginToApplication(email, password);

		String actualTest = dashboard.WelcomeText().getText();

		Assert.assertTrue(actualTest.contains("Welcome"), "login message didnt appear");

	}

	@Test(dependsOnMethods = "validLogin", priority = 1, description = "verify footer links")
	public void verifyFooterLinks() {

		List<String> text = login.footerLinks();

		System.out.println(text);

	}
	
	

	public void header() {

		String headerText = login.headerText();

		System.out.println(headerText);

	}

}
