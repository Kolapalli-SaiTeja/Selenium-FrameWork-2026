package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.helper.CustomWaits;
import com.helper.Utility;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

	}

	// locators

	private By email = By.id("email1");

	private By password = By.xpath("//input[@id='password1']");

	private By loginButton = By.xpath("//button[normalize-space()='Sign in']");

	private By newUser = By.linkText("New user? Signup");

	private By footerText = By.xpath("//div[@class = 'left-div']");

	private By Header = By.xpath("//h2[normalize-space()='Sign In']");

	private By footerLinks = By.xpath("//div[@class='footer-div']//a[@href]");

	public void clickOnRegistrationLink(String user) {

		driver.findElement(newUser).click();

	}

	public DashboardPage loginToApplication(String username, String pass) {

		CustomWaits.ElementToBeClickable(driver, email).sendKeys(username);

		CustomWaits.ElementToBeClickable(driver, password).sendKeys(pass);

		Utility.clickElement(driver, loginButton);
	

		DashboardPage dp = new DashboardPage(driver);

		return dp;

	}

	public String getFooterText() {

		return driver.findElement(footerText).getText();

	}

	public String headerText() {

		return driver.findElement(Header).getText();

	}

	public List<String> footerLinks() {

		List<WebElement> ele = driver.findElements(footerLinks);

		List<String> list = new ArrayList<String>();

		for (WebElement wb : ele) {

			list.add(wb.getAttribute("href"));

		}

		return list;

	}

}
