package SVVT.SeleniumClickUp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class LandingPageTest {
	private static WebDriver wd;
    private static String baseURL;
    public void print(String s) {
    	System.out.println(s);
    }

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/omersamibastug/Downloads/chromedriver_mac64/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		wd = new ChromeDriver(options);
		baseURL = "https://clickup.com/";
		String email = System.getProperty("EMAIL");
		System.out.println(email);		
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
		emailInput.sendKeys("asd");
		
		// click the submit button
		wd.findElement(By.xpath("/html/body/div[4]/div[3]/div[1]/div[1]/button")).click();
		
		// check if the input field has error class
		boolean errorShowed = Arrays.asList(emailInput.getAttribute("class").split(" ")).contains("errored");
		assertTrue(errorShowed);
		System.out.print(errorShowed);
		
		// check if the correct error message is displayed
		WebElement errorMessageDiv = wd.findElement(By.xpath("/html/body/div[4]/div[3]/div[1]/div[1]/div"));
		String errormessage = errorMessageDiv.getText();
		System.out.println(errormessage);

		assertEquals(errormessage, "Please enter a valid email address");
		
		System.out.println(emailInput.getAttribute("value"));
		assertTrue(true);
	}

}

