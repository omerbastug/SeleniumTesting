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
class ClosingTaskTest {

    private static String baseURL = "https://app.clickup.com/";

    @BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	}
	@Test
	@Order(1)
	void ChangeStatusToReviewTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		// open with profile logged into clickup as second user with a test task
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
		Task1NameDiv.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-task-keeper/cu-manager-view-task/div[2]/div/div/aside/div[1]/div[1]/div[1]")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/cu-status-list/div/div[3]/div[5]")))
		.click();
		
		
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-task-keeper/cu-manager-view-task/div[2]/nav/button[2]")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/cu-views-bar-container/cu2-views-bar/span/div/cu-filter-block[2]/div/cu-filter-value-list-dropdown/div/div")))
		.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div/cu-search-list/cdk-virtual-scroll-viewport/div[1]/div/div[1]")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/div/cu-filter-value-list/cu-filter-value/div[1]/cu-status-filter/div/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div[1]/div[1]/input")))
		.sendKeys("in review");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div[2]/cu-status-filter-type-block/div[2]")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/div/button")))
		.click();
		String status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/div/cu-dashboard-table/div/div[2]/cu-list-view-divisions/cu-list-group/cu-task-list/cu-task-list-header/div[2]/div/div/div[1]/div/span")))
		.getText();
		System.out.println(status);
		assertEquals("IN REVIEW", status);
		String name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("		/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/div/cu-dashboard-table/div/div[2]/cu-list-view-divisions/cu-list-group/cu-task-list/div[1]/cu-task-row/div/div[2]/div/cu-editable/cu-task-row-main/a/div/div/div/span")))
				.getText();
		assertEquals("Test task", name);
		secondUser.quit();	
		}
	
	@Test
	@Order(2)
	void ReviewAndCloseTaskTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		// open with profile logged into clickup as user 1
		options.addArguments("user-data-dir=C:\\Chrome_Profiles");
		WebDriver wd = (WebDriver) new ChromeDriver(options);
		wd.get(baseURL);
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(6));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/cu-views-bar-container/cu2-views-bar/span/div/cu-filter-block[2]/div/cu-filter-value-list-dropdown/div/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div/cu-search-list/cdk-virtual-scroll-viewport/div[1]/div/div[1]")))
		.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/div/cu-filter-value-list/cu-filter-value/div[1]/cu-status-filter")))
		.click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/cu-views-bar-container/cu2-views-bar/span/div/cu-filter-block[1]/cu-search-filter/div/div[1]/input")))
//		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div[1]/div[1]/input")))
		.sendKeys("in review");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div[2]/cu-status-filter-type-block/div[2]")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/div/button")))
		.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/cu-views-bar-container/cu2-views-bar/span/div/cu-filter-block[1]/cu-search-filter/div/div[1]/input")))
		.sendKeys("test task");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/div/cu-dashboard-table/div/div[2]/cu-list-view-divisions/cu-list-group/cu-task-list/div[1]/cu-task-row/div/div[2]/div/cu-editable/cu-task-row-main/a/div/div/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-task-keeper/cu-manager-view-task/div[2]/div/div/aside/div[1]/div[1]/div[1]/div/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/cu-status-list/div/div[3]/div[9]")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-task-keeper/cu-manager-view-task/div[2]/nav/button[2]")))
		.click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/cu-views-bar-container/cu2-views-bar/span/div/cu-filter-block[2]/div/cu-filter-value-list-dropdown/div/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/div/cu-filter-value-list/cu-filter-value/div[1]/cu-status-filter/div/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div[2]/cu-status-filter-type-block[2]/div/div[1]/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div[3]/div")))
		.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div/div/button")))
		.click();
		String closedTaskName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/main/cu-dashboard/div/div/cu-dashboard-table/div/div[2]/cu-list-view-divisions/cu-list-group/cu-task-list/div[1]/cu-task-row/div/div[2]/div/cu-editable/cu-task-row-main/a/div/div/div/span")))
		.getText();
		assertEquals(closedTaskName, "Test task");
		wd.quit();
		
	}

}
