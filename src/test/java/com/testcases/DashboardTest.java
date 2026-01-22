package com.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Baseclass;
import com.dataprovider.ConfigUtility;
import com.dataprovider.DataProviders;
import com.pages.DashboardPage;
import com.pages.LoginPage;

public class DashboardTest extends BaseTest {

	
	@Test(priority = 1)
	public void coursesList() {

		List<String> courses = dashboard.coursesPresent();

		System.out.println(courses);

	}

	@Test(dataProvider = "courses", dataProviderClass = DataProviders.class, priority = 2)
	
	public void addTocart(String... courses) {
		
		dashboard.addToCart(courses);
				
	}
	
	
	
	
	
	
	

}
