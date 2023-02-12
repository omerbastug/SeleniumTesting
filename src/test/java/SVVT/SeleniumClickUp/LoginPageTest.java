package SVVT.SeleniumClickUp;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

@TestMethodOrder(OrderAnnotation.class)
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
		passwordErrorMessage = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[2]/p/span[2]"));
		

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
	
	@Test
	@Order(3)
	void wrongEmailAndPasswordTest() throws InterruptedException { 
		// go back to login form from forgot password
		wd.navigate().back();
		WebElement emailInput = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[1]/input"));
		WebElement passwordInput = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[2]/input"));
	
		emailInput.clear();
		emailInput.sendKeys("incorrect@email.com");
		passwordInput.sendKeys("wrongpassword");
		
		WebElement loginFormSubmitButton = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/button"));
		loginFormSubmitButton.click();
		
		
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(3));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		// wait for error alert
		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-toast-lazy-wrapper[1]/cu-toast-new/div/div/p")));
		assertEquals(errorMessage.getText(), "No account for this email");
		
		WebElement dismissMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-toast-lazy-wrapper[1]/cu-toast-new/div/div/div/button/span")));
		dismissMessage.click();
	}
	
	@Test
	@Order(4)
	void loggingInWithCorrectCredentialsTest() { 
		// go back to login form from forgot password
		WebElement emailInput = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[1]/input"));
		WebElement passwordInput = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/div[2]/input"));
		
		emailInput.clear();
		emailInput.sendKeys(System.getenv("EMAIL"));
		passwordInput.clear();
		passwordInput.sendKeys(System.getenv("PASSWORD"));
		 
		WebElement loginFormSubmitButton = wd.findElement(By.xpath("/html/body/app-root/cu-login/div/div[2]/div[2]/div[1]/cu-login-form/div/form/button"));
		loginFormSubmitButton.click();
		
		
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(6));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		// wait for error alert
		WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/cu-simple-bar/div/div[4]/cu-user-settings-menu/div/div/cu-avatar")));
		profileIcon.click();
		WebElement settingsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My Settings")));
		settingsLink.click();
		WebElement emailInProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/manager-settings/div/div/manager-user-settings/div/div[1]/div[2]/form/div[2]/div/input")));
		String emailLoggedIn = emailInProfile.getAttribute("value");
		
		assertEquals(emailLoggedIn, System.getenv("EMAIL"));
		
	}

}
