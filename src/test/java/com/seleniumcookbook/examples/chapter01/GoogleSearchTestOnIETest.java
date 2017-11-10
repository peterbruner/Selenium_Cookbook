/**
 * 
 */
package com.seleniumcookbook.examples.chapter01;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author pbruner
 *
 */
public class GoogleSearchTestOnIETest {
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
		
		// passing caps to InternetExplorerDriver() is deprecated
		// documentation says to use InternetExplorerDriver() and pass an InternetExplorerOptions object
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		
//		InternetExplorerOptions ieOptions;
//		InternetExplorerDriver(ieOptions);
//		InternetExplorerDriver.InternetExplorerDriver(ieOptions);
		
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
		// Launch Internet Explorer
		driver = new InternetExplorerDriver(caps);
		// Maximize the browser window
		driver.manage().window().maximize();
		// Navigate to Google
		driver.get("http://www.google.com");
	}
	
	@Test
	public void testGoogleSearch() {
		// Find the text input element by its name
		WebElement element = driver.findElement(By.name("q"));
		
		// Enter something to search for
		element.sendKeys("Selenium testing tools cookbook");
		
		// Now submit the form. WebDriver will find
		// the form for us from the element
		element.submit();
		
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
		// Close the browser
		driver.quit();
	}

}
