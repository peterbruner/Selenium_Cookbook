package com.seleniumcookbook.examples.chapter01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTestOnEdgeTest {

	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\pbruner\\Documents\\eclipse-workspace3\\Selenium_Cookbook\\SeleniumCookbook\\src\\test\\resources\\drivers\\MicrosoftWebDriver.exe ");
		
		EdgeOptions options = new EdgeOptions();
		options.setPageLoadStrategy("eager");
		
		// Launch a new Edge instance
		driver = new EdgeDriver(options);
		
		driver.get("http://www.google.com");
		
	}
	
	@Test
	public void testGoogleSearch() {
		// Find the text input element by its name
		WebElement element = driver.findElement(By.name("q"));
		
		// Clear the existing text value
		element.clear();
		
		// Enter something to search for
		element.sendKeys("Selenium testing tools cookbook");
		
		WebElement button = driver.findElement(By.name("btnG"));
		button.click();
		
		// Google's search is rendered dynamically with JavaScript.
		// Wait for the page to load, timeout after 10 seconds
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase()
						.startsWith("selenium testing tools cookbook");
			}
		});
		
		assertEquals("Selenium testing tools cookbook - Google Search", 
				driver.getTitle());
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
