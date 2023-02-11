package SVVT.SeleniumClickUp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class LoginPageTest {

	private static WebDriver wd;
    private static String baseURL;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		wd = (WebDriver) new ChromeDriver(options);
		baseURL = "https://app.clickup.com/login";
		wd.get(baseURL);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Thread.sleep(3000);
		wd.quit();
	}


	@Test
	@Order(1)
	void inputValidationTest() {
		WebElement loginFormSubmitButton = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/button"));
		
		loginFormSubmitButton.click();
		
		WebElement emailErrorMessage = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[1]/p/span[2]"));
		assertEquals(emailErrorMessage.getText(), "Email required!");
		
		WebElement passwordErrorMessage = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[2]/p/span[2]"));
		assertEquals(passwordErrorMessage.getText(), "Password required!");
		
		
		WebElement emailInput = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[1]/input"));
		WebElement passwordInput = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[2]/input"));
	
		
		emailInput.sendKeys("invalid email");
		passwordInput.sendKeys("short");
		
		loginFormSubmitButton.click();
		
		emailErrorMessage = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[1]/p/span[2]"));
		passwordErrorMessage = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[2]/p/span[2]"));		System.out.println(emailErrorMessage.getText());

		assertEquals(emailErrorMessage.getText(),"This email is invalid!");
		assertEquals(passwordErrorMessage.getText(),"Password must be 8 characters or longer!");
	}
	
	@Test
	@Order(2)
	void carryEmailToForgotPasswordTest() { 
		WebElement emailInput = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[1]/input"));
		emailInput.clear();
		emailInput.sendKeys("test@email.com");
		
		WebElement forgotPasswordButton = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[2]/div[2]/a"));
		forgotPasswordButton.click();
		
		WebElement newEmailInput = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-app-forgot-password/div/form/div[1]/input"));
		String emailCarried = newEmailInput.getAttribute("value");
		
		assertEquals(emailCarried, "test@email.com");
	}

}
