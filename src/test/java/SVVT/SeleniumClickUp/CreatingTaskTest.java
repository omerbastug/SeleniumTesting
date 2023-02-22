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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

@TestMethodOrder(OrderAnnotation.class)
class CreatingTaskTest {

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
	void createAndAssignTaskToUserTest() {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(6));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		// click new task button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/cu-float-button/div/div[2]/div[1]")))
		.click();
		
		// Give a name to task
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/cu-create-task-draft/cu-create-task-draft-lazy/cu-draft-view/div[2]/div[1]/div[2]/div/cu-slash-command/textarea")))
		.sendKeys("Test task");
		
		// Assign user 2 to the task
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/cu-create-task-draft/cu-create-task-draft-lazy/cu-draft-view/div[2]/div[2]/div/div[1]/div[2]/cu-user-and-groups-list-dropdown/cu-user-list-dropdown/div/div/cu-user-group/cu-avatar-group/div/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/cu-user-list/div/div[2]/cdk-virtual-scroll-viewport/div[1]/cu-user-item[2]")))
		.click();
		
		// click create task button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/cu-create-task-draft/cu-create-task-draft-lazy/cu-draft-view/div[2]/div[3]/button/div[1]")))
		.click();
	}
	
	@Test
	@Order(2)
	void test() throws InterruptedException {
		// wait for the call to be made
		Thread.sleep(2000);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		// open with profile logged into clickup as second user
		options.addArguments("user-data-dir=C:\\Chrome_Second_Profile");
		WebDriver secondUser = (WebDriver) new ChromeDriver(options);
		secondUser.get(baseURL);
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(secondUser);
		wait.withTimeout(Duration.ofSeconds(6));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		// click button to list tasks assigned to the user
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/cu-views-bar-container/cu2-views-bar/span/div/cu-filter-assignees/button[1]/span[2]")))
		.click();
		
		WebElement Task1NameDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/div/cu-dashboard-table/div/div[2]/cu-list-view-divisions/cu-list-group/cu-task-list/div[2]/cu-task-row/div/div[2]/div/cu-editable/cu-task-row-main/a/div/div/div/span")));
		assertEquals(Task1NameDiv.getText(),"Test task");
		secondUser.quit();
	}

}
