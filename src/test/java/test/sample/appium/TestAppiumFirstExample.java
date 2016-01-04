package test.sample.appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAppiumFirstExample {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", "Android");

		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android");

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome"); //Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		capabilities.setCapability(CapabilityType.VERSION, "5.0.1");
		capabilities.setCapability(CapabilityType.PLATFORM, "Windows");

		//capabilities.setCapability("app-package", "APP PACKAGE"); //Replace with your app's package
		//capabilities.setCapability("app-activity", "APP PACKAGE.ANDROID ACTIVITY"); //Replace with app's Activity
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	@Test
	public void Cal(){
		final String searchKey = "TestNG";
		System.out.println("Search " + searchKey + " in google");
		driver.navigate().to("http://www.google.com");
		WebElement element = driver.findElement(By.name("q"));
		System.out.println("Enter " + searchKey);
		element.sendKeys(searchKey);
		System.out.println("submit");
		element.submit();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase()
						.startsWith(searchKey.toLowerCase());
			}
		});
		System.out.println("Got " + searchKey + " results");
	//	driver.findElement(By.name("Weather")).click();
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}

}