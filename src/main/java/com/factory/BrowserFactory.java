package com.factory;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.dataprovider.ConfigUtility;
import com.helper.CustomWaits;

public class BrowserFactory {
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void setDriver(WebDriver driver) {
		tlDriver.set(driver);
	}

	public static void unload() {
		tlDriver.remove();
	}

	public static WebDriver startBrowser(String browser, String URL) {

		WebDriver driver = null;

		boolean cloud = ConfigUtility.readProperty("cloud").equalsIgnoreCase("true");

		try {

			// ================= CLOUD EXECUTION =================
			if (cloud) {

				System.out.println("Running on Selenium Grid (Cloud Mode)");

				String hubIp = ConfigUtility.readProperty("hubip");
				
				String hubPort = ConfigUtility.readProperty("hubport");

				if (hubIp == null || hubPort == null) {
					
					throw new RuntimeException("Hub IP or Port missing in config.properties");
				}

				String hubUrl = "http://" + hubIp + ":" + hubPort;
				
				System.out.println("Hub URL: " + hubUrl);

				if (browser.equalsIgnoreCase("chrome")) 
				{

					ChromeOptions opt = new ChromeOptions();
					driver = new RemoteWebDriver(new URL(hubUrl), opt);

				} else if (browser.equalsIgnoreCase("firefox")) 
					
				{

					FirefoxOptions opt = new FirefoxOptions();
					driver = new RemoteWebDriver(new URL(hubUrl), opt);

				} else if (browser.equalsIgnoreCase("edge")) 
					
				{

					EdgeOptions opt = new EdgeOptions();
					driver = new RemoteWebDriver(new URL(hubUrl), opt);

				} else {

					ChromeOptions opt = new ChromeOptions();
					driver = new RemoteWebDriver(new URL(hubUrl), opt);
				}

			}

			// ================= LOCAL EXECUTION =================
			else {

				System.out.println("Running on Local Machine");

				if (browser.equalsIgnoreCase("chrome")) {

					ChromeOptions opt = new ChromeOptions();

					if (ConfigUtility.readProperty("headless").equalsIgnoreCase("true")) {
						opt.addArguments("--headless=new");
						opt.addArguments("--window-size=1920,1080");
					}

					driver = new ChromeDriver(opt);

				} else if (browser.equalsIgnoreCase("firefox")) {

					FirefoxOptions opt = new FirefoxOptions();

					if (ConfigUtility.readProperty("headless").equalsIgnoreCase("true")) {
						opt.addArguments("--headless");
					}

					driver = new FirefoxDriver(opt);

				} else if (browser.equalsIgnoreCase("edge")) {

					EdgeOptions opt = new EdgeOptions();

					if (ConfigUtility.readProperty("headless").equalsIgnoreCase("true")) {
						opt.addArguments("--headless=new");
					}

					driver = new EdgeDriver(opt);

				} else {

					System.out.println("Browser not supported. Starting Chrome by default.");
					driver = new ChromeDriver();
				}
			}

		} catch (Exception e) {
			System.out.println("Could not start browser: " + e.getMessage());
			throw new RuntimeException(e);
		}

		// =============== COMMON CONFIGURATION ===============
		setDriver(driver);

		driver.manage().window().maximize();

		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("pageloadTime"))));

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("implicitWait"))));

		driver.get(URL);

		return driver;
	}

}
