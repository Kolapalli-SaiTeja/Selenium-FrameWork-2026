package com.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Baseclass;
import com.dataprovider.ConfigUtility;
import com.dataprovider.DataProviders;
import com.listeners.RetryAnalyzer;
import com.pages.CartPage;
import com.pages.DashboardPage;
import com.pages.LoginPage;

public class CartTest extends Baseclass {
	
	

	@Test(dataProvider = "courses", dataProviderClass = DataProviders.class, retryAnalyzer = RetryAnalyzer.class)
	public void verifyCartPrice(String... courses) {

		LoginPage login = new LoginPage(getDriver());
		
		DashboardPage dashboard = login.loginToApplication(ConfigUtility.readProperty("username"),ConfigUtility.readProperty("password"));

		// Add courses to cart
		CartPage cart = dashboard.addToCart(courses);

		Assert.assertNotNull(cart, "Cart page not loaded");

		// Get total price
		String textPrice = cart.totalPrice();
		String numericPrice = textPrice.replaceAll("[^0-9]", "");
		int actualPrice = Integer.parseInt(numericPrice);

		System.out.println("Total price = " + actualPrice);

		// Assertion
		Assert.assertEquals(actualPrice, 2000, "Total price mismatch");
	}

}
