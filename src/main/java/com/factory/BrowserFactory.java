package com.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.dataprovider.ConfigUtility;
import com.helper.CustomWaits;

public class BrowserFactory {

	 private static ThreadLocal<WebDriver> tl = new ThreadLocal<>();
	

	// getter method to get driver of current test
	public static WebDriver getDriver() {

		return tl.get();

	}

	public static WebDriver startBrowser(String browser, String URL) {
		
		 WebDriver driver;

		if (browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("google chrome")) {

			ChromeOptions opt = new ChromeOptions();

			if (ConfigUtility.readProperty("headless").equalsIgnoreCase("true")) {

				opt.addArguments("--headless=new");

				opt.addArguments("--window-size=1920,1080");

			}
			System.out.println("Running on Chrome");

			driver = new ChromeDriver(opt);

		} else if (browser.equalsIgnoreCase("edge")) {

			EdgeOptions opt = new EdgeOptions();

			if (ConfigUtility.readProperty("headless").equalsIgnoreCase("true")) {

				opt.addArguments("--headless=new");
				opt.addArguments("--window-size=1920,1080");

			}

			System.out.println("Running on Edge");

			driver = new EdgeDriver(opt);

		} else if (browser.equalsIgnoreCase("firefox")) {

			FirefoxOptions opt = new FirefoxOptions();

			if (ConfigUtility.readProperty("headless").equalsIgnoreCase("true")) {

				opt.addArguments("--headless=new");

				opt.addArguments("--window-size=1920,1080");

			}

			System.out.println("Running on Firefox");

			driver = new FirefoxDriver(opt);

		} else {

			System.out.println("sorry currently " + browser + " is not supported--- Starting on default chrome");

			driver = new ChromeDriver();

		}

		
		tl.set(driver); 
		
		driver.get(URL);

		String wait = ConfigUtility.readProperty("implicitWait");

		int time = Integer.parseInt(wait);

		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("pageloadTime"))));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

		return driver;
	}

}
