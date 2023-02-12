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

class CreatingWorkspaceTest {
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
	void test() throws InterruptedException {
		
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(wd);
		wait.withTimeout(Duration.ofSeconds(6));
		wait.pollingEvery(Duration.ofMillis(200));
		wait.ignoring(NoSuchElementException.class);
		
		WebElement createWorkspace = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/cu-simple-bar/div/div[3]/div[7]/cu2-project-list-bar/div/cu-search-list/div[3]/div/div")));
		createWorkspace.click();
		// wait for the page elements load
		WebElement workspaceName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/div[3]/div[2]/input")));
		workspaceName.sendKeys("Test space");
		
		// next button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/button")))
		.click();
		Thread.sleep(200);

		// confirm avatar, next
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/button")))
		.click();
		
		Thread.sleep(200);
		// confirm public, next
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/button")))
		.click();

		Thread.sleep(200);
		// select SCRUM framework
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/div[3]/div[1]/div[2]/cu-status-template[6]/div/div/cu-editable/div[1]")))
		.click();
		
		Thread.sleep(200);
		// next
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/button")))
		.click();

		Thread.sleep(200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/button")))
		.click();
		Thread.sleep(200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/button")))
		.click();
		Thread.sleep(200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-modal-keeper/cu-modal/div/div[2]/div[2]/div[2]/cu-create-project-modal/div/div/button")))
		.click();
		
		// wait for UI to adjust
		Thread.sleep(2000);
		WebElement newSpace = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cu-app-shell/cu-manager/div[1]/div/div[2]/cu-simple-bar/div/div[3]/div[7]/cu2-project-list-bar/div/cu-search-list/div[3]/cu-lazy-list/div/cu2-project-list-bar-item[1]/div[1]/div[3]/div[1]/a")));
		assertEquals(newSpace.getText(), "Test space");
	}

}
