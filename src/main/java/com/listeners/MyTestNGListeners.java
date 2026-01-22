package com.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.dataprovider.ConfigUtility;
import com.factory.BrowserFactory;
import com.helper.Utility;

public class MyTestNGListeners implements ITestListener {

	public void onTestSuccess(ITestResult result) {

		ChainTestListener.log("LOG:INFO-Test Passed " + result.getMethod().getMethodName());

		if (ConfigUtility.readProperty("screenShotOnSuccess").equalsIgnoreCase("true")) {

			String base64 = Utility.captureScreenShot(BrowserFactory.getDriver(), "base64");

			ChainTestListener.embed(base64, "image/png");

		}

	}



	public void onTestFailure(ITestResult result) {

		ChainTestListener.log("LOG:INFO - Test Failed " + result.getMethod().getMethodName());
		ChainTestListener.log("LOG:INFO - Exception " + result.getThrowable().getMessage());

		if (ConfigUtility.readProperty("screenShotOnFailure").equalsIgnoreCase("true")) {

			String base64 = Utility.captureScreenShot(BrowserFactory.getDriver(), "base64");

			ChainTestListener.embed(base64, "image/png");

		}

	}

	public void onTestSkipped(ITestResult result) {

		ChainTestListener.log("LOG:INFO-Test skipped " + result.getMethod().getMethodName());
		ChainTestListener.log("LOG:INFO-Exception" + result.getThrowable().getMessage());

		String base64 = Utility.captureScreenShot(BrowserFactory.getDriver(), "base64");

		ChainTestListener.embed(base64, "image/png");

	}

}
