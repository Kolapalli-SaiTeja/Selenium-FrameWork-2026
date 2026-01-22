package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dataprovider.DataProviders;
import com.listeners.RetryAnalyzer;
import com.pages.CartPage;
import com.pages.DashboardPage;

public class CartTest extends BaseTest {

	CartPage cp;

	@Test(dataProvider = "courses", dataProviderClass = DataProviders.class, priority = 2)

	public void addTocart(String... courses) {

		cp = dashboard.addToCart(courses);

	}

	@Test(retryAnalyzer = RetryAnalyzer.class, priority = 3, dependsOnMethods = "addTocart")
	public void priceOfCourses() {

		String textPrice = cp.totalPrice();

		System.out.println(textPrice);

		String numericPrice = textPrice.replaceAll("[^0-9]", "");  // here [^  ->> means NOT]
		
		int actualPrice = Integer.parseInt(numericPrice);


		System.out.println("The total price of the courses is >> " + actualPrice);

		Assert.assertTrue(actualPrice == 200);

	}

}
