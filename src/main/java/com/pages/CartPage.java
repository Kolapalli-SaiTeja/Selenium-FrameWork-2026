package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.helper.CustomWaits;

public class CartPage {

	protected WebDriver driver;

	public CartPage(WebDriver driver) {

		this.driver = driver;

	}

	private By TotalPrice = By.xpath("//div[@class='top-container']/h3");

	private By EnrollButton = By.xpath("//div[@class='top-container']/button");

	
	
	public String totalPrice() {

		String text = CustomWaits.VisibilityOfElement(driver, TotalPrice).getText();

		

		return text;

	}

}
