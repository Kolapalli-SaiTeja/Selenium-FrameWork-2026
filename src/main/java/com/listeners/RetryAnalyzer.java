package com.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.dataprovider.ConfigUtility;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;

	private int maxRetryCount = Integer.parseInt(ConfigUtility.readProperty("retry"));

	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {

			retryCount++;

			return true;

		}

		else

			return false;

	}

}
