package com.testcases;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SampleSeleniumGrid {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions opt = new ChromeOptions();

		String hubUrl = "http://3.109.221.199:4444/wd/hub";

		WebDriver driver = null;

		try {

			driver = new RemoteWebDriver(new URL(hubUrl), opt);

		} catch (Exception e) {
			System.out.println("could not connect to selenium grid " + e.getMessage());

		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

		driver.get("https://freelance-learn-automation.vercel.app/login");

		WebElement username = driver.findElement(By.name("email1"));

		username.sendKeys("admin@email.com");

		WebElement password = driver.findElement(By.id("password1"));

		password.sendKeys("admin@123");

		driver.findElement(By.className("submit-btn")).click();
		
		Thread.sleep(60000);

		driver.quit();
	}

}
