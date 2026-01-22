	package com.pages;
	
	import java.util.ArrayList;
	import java.util.List;
	import java.util.NoSuchElementException;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	
	import com.helper.CustomWaits;
	import com.helper.Utility;
	
	public class DashboardPage {
	
		WebDriver driver;
	
		public DashboardPage(WebDriver driver) {
	
			this.driver = driver;
	
		}
	
		private By coursesList = By.xpath("//div[@class='home-container']//h2");
	
		private By cart = By.xpath("//button[@class='cartBtn']");
	
		private By manage = By.xpath("//div[@class='nav-menu-item-manage']");
	
		private By manageCourses = By.xpath("//a[normalize-space()='Manage Courses']");
	
		private By sideMenu = By.xpath("//img[@alt='menu']");
	
		private By logOut = By.xpath("//button[text()='Sign out']");
	
		private By Welcome = By.xpath("//h4[@class='welcomeMessage']");
	
		private By cartCount = By.xpath("//button[@class='cartBtn']/span");
	
		public By addCourseButton(String courseName) {
	
			return By.xpath("//h2[text()= '" + courseName + "']"
					+ "/parent::div[@class='course-content']/following-sibling::button");
	
		}
	
		public List<String> coursesPresent() {
	
			List<WebElement> wb = driver.findElements(coursesList);
	
			List<String> list = new ArrayList<String>();
	
			for (WebElement ele : wb) {
	
				list.add(ele.getText());
	
			}
	
			return list;
	
		}
	
		public WebElement WelcomeText() {
	
			return CustomWaits.VisibilityOfElement(driver, Welcome);
	
		}
	
		/*
		 * public void addToCart(String... courseNames) {
		 * 
		 * for (String courseName : courseNames) {
		 * 
		 * System.out.println("Adding course to cart >> " + courseName);
		 * 
		 * Utility.clickElement(driver, addCourseButton(courseName)); } }
		 */
	
		public int addedCartCount() {
	
			String count = driver.findElement(cartCount).getText();
	
			int counts = Integer.parseInt(count);
	
			return counts;
	
		}
	
		public CartPage addToCart(String... courseNames) {
	
			List<String> list = new ArrayList<String>();
	
			for (String courseName : courseNames) {
	
				list.add(courseName);
	
				Utility.clickElement(driver, addCourseButton(courseName));
	
			}
	
			String courses = String.join(",", list);
	
			System.out.println("Adding course to cart >> " + courses);
	
			if (list.size() == addedCartCount()) {
	
				Utility.clickElement(driver, cart);
	
			}
			
			
			CartPage page = new CartPage(driver);
			
			return page;
			
		}
	
		public void signOut() {
	
			driver.findElement(sideMenu).click();
	
			driver.findElement(logOut).click();
	
		}
	
	}
