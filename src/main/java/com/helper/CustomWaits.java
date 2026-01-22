package com.helper;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dataprovider.ConfigUtility;

public class CustomWaits {

	protected static WebDriverWait wait;
	
	
	public static void highLightElement(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		
		Utility.waitForSeconds(1);
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
		

	}


	public static WebElement ElementToBeClickable(WebDriver driver, By locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));

		if (ConfigUtility.readProperty("highlight").equalsIgnoreCase("Yes")) {

			highLightElement(driver, ele);
		}

		return ele;
	}

	public static WebElement VisibilityOfElement(WebDriver driver, By locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		if (ConfigUtility.readProperty("highlight").equalsIgnoreCase("Yes")) {

			highLightElement(driver, ele);
		}

		return ele;
	}

	// invisibility of element
	public Boolean InvisibilityOfElement(WebDriver driver, By locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		boolean ele = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

		return ele;

	}
}
