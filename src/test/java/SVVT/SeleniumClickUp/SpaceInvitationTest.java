package SVVT.SeleniumClickUp;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

@TestMethodOrder(OrderAnnotation.class)
class SpaceInvitationTest {


	private static WebDriver wd;
    private static String baseURL;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		// open with profile logged into clickup to skip logging in
		options.addArguments("user-data-dir=C:\\Chrome_Profiles");
		wd = (WebDriver) new ChromeDriver(options);
		baseURL = "https://app.clickup.com/";
		wd.get(baseURL);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Thread.sleep(3000);
		wd.quit();
	}


	@Test
	@Order(1)
	void sendInviteTest() throws InterruptedException {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(6));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		
		WebElement menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/cu-simple-bar/div/div[4]/cu-user-settings-menu/div/div")));
		menuButton.click();
		
		WebElement peoplePageLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("People")));
		peoplePageLink.click();
		
		WebElement inviteInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/manager-settings/div/div/cu-team-settings/cu-team-users-settings/cu-team-users-settings-view/cu-team-settings/div/div[1]/div[2]/cu-invite-team-user/div/div[1]/input")));
		inviteInput.sendKeys(System.getenv("EMAIL2"));
		
		WebElement inviteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/manager-settings/div/div/cu-team-settings/cu-team-users-settings/cu-team-users-settings-view/cu-team-settings/div/div[1]/div[2]/cu-invite-team-user/div/button")));
		inviteButton.click();
		
		WebElement filterUsersInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/manager-settings/div/div/cu-team-settings/cu-team-users-settings/cu-team-users-settings-view/cu-team-settings/div/div[1]/div[2]/div/div/div/input")));
		filterUsersInput.sendKeys(System.getenv("EMAIL2"));
		
		//wait for filter
		Thread.sleep(2000);
		WebElement firstUserStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/manager-settings/div/div/cu-team-settings/cu-team-users-settings/cu-team-users-settings-view/cu-team-settings/div/div[2]/cdk-virtual-scroll-viewport/div[1]/div[1]/div/div[1]/div[2]/div")));
		assertEquals(firstUserStatus.getText(),"PENDING");
		
	}
	
	@Test
	@Order(2)
	void AcceptInvitationTest() throws InterruptedException {
		// wait for the invitation to be sent
		Thread.sleep(3000);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		// open with profile logged into clickup to skip logging in
		options.addArguments("user-data-dir=C:\\Chrome_Second_Profile");
		WebDriver secondUser = (WebDriver) new ChromeDriver(options);
		secondUser.get(baseURL);
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(secondUser);
		wait.withTimeout(Duration.ofSeconds(6));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		WebElement acceptInvite = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-join-team/div/div/button")));
		acceptInvite.click();
		WebElement newworkspaceName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/cu-simple-bar/div/div[3]/div[7]/cu2-project-list-bar/div/cu-search-list/div[3]/cu-lazy-list/div/cu2-project-list-bar-item/div[1]/div[3]/div[1]/a")));
		assertEquals(newworkspaceName.getText(), "Test space");
		secondUser.quit();
	}
}
