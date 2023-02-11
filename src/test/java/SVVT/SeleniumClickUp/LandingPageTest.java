package SVVT.SeleniumClickUp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class LandingPageTest {
	private static WebDriver wd;
    private static String baseURL;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		wd = (WebDriver) new ChromeDriver(options);
		baseURL = "https://clickup.com/";
		wd.get(baseURL);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Thread.sleep(3000);
		wd.quit();
	}

	@Test
	void getStartedEmailValidationTest() {
		
		//send invalid email input
		WebElement emailInput = wd.findElement(By.xpath("/html/body/div[4]/div[3]/div[1]/div[1]/input"));
		emailInput.sendKeys("invalid email input");
		
		// click the submit button
		wd.findElement(By.xpath("/html/body/div[4]/div[3]/div[1]/div[1]/button")).click();
		
		// check if the input field has error class
		boolean errorShowed = Arrays.asList(emailInput.getAttribute("class").split(" ")).contains("errored");
		assertTrue(errorShowed);
		
		// check if the correct error message is displayed
		WebElement errorMessageDiv = wd.findElement(By.xpath("/html/body/div[4]/div[3]/div[1]/div[1]/div"));
		String errormessage = errorMessageDiv.getText();

		assertEquals(errormessage, "Please enter a valid email address");
	}
	
	
	@Test
	void signUpButtonColorChangeOnScrollTest() throws InterruptedException {
		WebElement signupButton = wd.findElement(By.xpath("/html/body/div[4]/div[2]/div/header/div/nav/div/div/button"));
		
		//check button color white at load
		String color = signupButton.getCssValue("background-color");
		assertEquals(color, "rgba(255, 255, 255, 1)");
		
		// check button color purple after scrolling
		JavascriptExecutor jse = (JavascriptExecutor) wd;
		jse.executeScript("window.scrollBy(0, 200)");
		Thread.sleep(500); // wait for the color change
		String newcolor = signupButton.getCssValue("background-color");
		assertEquals(newcolor, "rgba(123, 104, 238, 1)");

	}
}

