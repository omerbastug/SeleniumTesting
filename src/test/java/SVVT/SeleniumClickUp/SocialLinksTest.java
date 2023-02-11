package SVVT.SeleniumClickUp;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

class SocialLinksTest {
	private static WebDriver wd;
    private static String baseURL;
    private static String landingPageHandle;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		// open with profile logged into social platforms needed to avoid login popups
		options.addArguments("user-data-dir=C:\\Chrome_Profiles");
		wd = (WebDriver) new ChromeDriver(options);
		baseURL = "https://clickup.com/";
		wd.get(baseURL);
		landingPageHandle = wd.getWindowHandle();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Thread.sleep(3000);
		wd.quit();
	}

	@Test
	void linkedinHandleTest() {
		WebElement linkedinButton = wd.findElement(By.xpath("/html/body/div[12]/footer/div/div[2]/div/a[1]"));
		linkedinButton.click();
		
		
		for(String handle : wd.getWindowHandles()) {
			if(!handle.equals(landingPageHandle)) {
				wd.switchTo().window(handle);
				break;
			}
		}
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(3));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		// wait for the page elements load
		WebElement companyName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div[3]/div/div[2]/div/div[2]/main/div[1]/section/div/div[2]/div[1]/div[1]/div[2]/div/h1/span")));
		assertEquals("ClickUp", companyName.getText());
		
		wd.close();
		
		wd.switchTo().window(landingPageHandle);		
	}
	
	@Test
	void facebookHandleTest() {
		WebElement facebookButton = wd.findElement(By.xpath("/html/body/div[12]/footer/div/div[2]/div/a[2]"));
		
		facebookButton.click();
		
		for(String handle : wd.getWindowHandles()) {
			if(!handle.equals(landingPageHandle)) {
				wd.switchTo().window(handle);
				break;
			}
		}
		
		WebElement companyName = wd.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div/div[1]/div[1]/div[1]/div[2]/div/div/div/div[2]/div/div/div[1]/span/h1"));
		assertEquals("ClickUp", companyName.getText());
		
		wd.close();
		
		wd.switchTo().window(landingPageHandle);
	}
	
	@Test
	void instagramHandleTest() {
		WebElement instagramButton = wd.findElement(By.xpath("/html/body/div[12]/footer/div/div[2]/div/a[3]"));
		
		instagramButton.click();
		
		for(String handle : wd.getWindowHandles()) {
			if(!handle.equals(landingPageHandle)) {
				wd.switchTo().window(handle);
				break;
			}
		}
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(3));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		// wait for the page elements load
		WebElement companyName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div/div/div[1]/section/main/div/header/section/div[1]/h2")));
		assertEquals("clickup", companyName.getText());
		
		wd.close();
		
		wd.switchTo().window(landingPageHandle);
	}
	
	@Test
	void twitterHandleTest() {
		WebElement twitterButton = wd.findElement(By.xpath("/html/body/div[12]/footer/div/div[2]/div/a[4]"));
		
		twitterButton.click();
		
		for(String handle : wd.getWindowHandles()) {
			if(!handle.equals(landingPageHandle)) {
				wd.switchTo().window(handle);
				break;
			}
		}
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(3));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		// wait for the page elements load
		WebElement companyName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div[1]/div/div[3]/div/div/div/div/div[2]/div[1]/div/div[1]/div/div/span[1]/span")));
		assertEquals("ClickUp", companyName.getText());
		
		wd.close();
		
		wd.switchTo().window(landingPageHandle);
	}

}
