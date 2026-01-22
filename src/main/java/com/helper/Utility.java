package com.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dataprovider.ConfigUtility;

public class Utility {

	public static void captureScreenShot(WebDriver driver) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File dst = new File("Captured Sc" + System.getProperty("user.dir") + "/Screenshots/screenshot"
				+ Utility.getCurrentDateTime() + ".png");

		try {
			FileHandler.copy(src, dst);

		} catch (IOException e) {

			System.out.println("Failed to capture screenshot" + e.getMessage());
		}

	}

	public static String captureScreenShot(WebDriver driver, String Type) {

		String screenshot = null;

		if (Type.equalsIgnoreCase("base64")) {

			TakesScreenshot ts = (TakesScreenshot) driver;

			screenshot = ts.getScreenshotAs(OutputType.BASE64);

		}

		return screenshot;

	}
	
	

	public static String getCurrentDateTime() {

		Date currentDate = new Date();

		SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_SS");

		String date = currentDateFormat.format(currentDate);

		return date;

	}


	public static void typeWithJS(WebDriver driver, By locator, String textToType) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].value=arguments[1]", driver.findElement(locator), textToType);
	}

	
	public void typeOnElement(WebDriver driver, By locator, String Texttype) {
		

		CustomWaits.ElementToBeClickable(driver, locator).sendKeys(Texttype);

	}

	public static void clickElement(WebDriver driver, By locator) {

		try {
			CustomWaits.ElementToBeClickable(driver, locator).click();

		} catch (Exception e) {

			System.out.println("WebElement Click Failed - Trying With JS Click");

			clickUsingJS(driver, locator);

		}
	}

	public static void clickUsingJS(WebDriver driver, By locator) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click()", driver.findElement(locator));

	}

	public static void selectValueFromDropdown(WebElement element, String valueToSelect) {

		Select dropdown = new Select(element);

		dropdown.selectByVisibleText(valueToSelect);

		System.out.println("*********** Selected " + valueToSelect + " From Dropdown");

	}
	
	
	public static void waitForSeconds(int  seconds) {
		
		try {
			Thread.sleep(Duration.ofSeconds(seconds));
		} catch (InterruptedException e) {
			
			System.err.println(e.getMessage());
			
		}
		
	}
	

}
