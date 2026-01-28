package com.listeners;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.dataprovider.ConfigUtility;
import com.factory.BrowserFactory;
import com.helper.Utility;

public class MyTestNGListeners implements ITestListener {
	
	
	
	private static final  String RUN_TS;
	
	private static final String REPORT_DIR;
	
	
	static {
		
		RUN_TS = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh_mm_ss_dd_MM_yyyy"));
		
		REPORT_DIR = "Reports/chaintest_" +RUN_TS;
		
		new File(REPORT_DIR).mkdir();
		
		
		System.setProperty("chaintest.generator.simple.output-file",REPORT_DIR + "/Index.html");
		
		System.setProperty("chaintest.generator.email.output-file",REPORT_DIR + "/Email.html");
		
	}


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
