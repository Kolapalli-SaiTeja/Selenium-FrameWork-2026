package com.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "login")

	public static Object[][] testData() {

		System.out.println("Loading excel data ");

		Object[][] arr = ExcelUtility.getData("Logincreds");

		System.out.println("Test data is getting loaded");
		return arr;

	}

	@DataProvider(name = "courses")
	public static Object[][] CoursetestData() {

		System.out.println("Loading excel data");

		Object[][] arr = ExcelUtility.getData("Courses");

		System.out.println("Test data is getting loaded");

		return arr;

	}

}
