package test.mobile.score_qa_automation_challenge.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import test.mobile.score_qa_automation_challenge.base.DriverManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author gurchet.singh
 * @since 15 March 2023
 * @description : This class is a base for page objects, this class keep
 *              the current state of the driver and perform necessary actions
 *              upon page object elements
 */

public class BasePage {

	protected AppiumDriver driver;

	public BasePage() {
		driver = DriverManager.getAppiumDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void click(By by) {
		driver.findElement(by).click();
	}

	public void sendKeys(By by, String text) {
		driver.findElement(by).sendKeys(text);
	}

	public String extractText(By by) {
		return driver.findElement(by).getText();
	}

	public boolean isElementPresent(By by) {
		WebElement element;
		try {
			element = driver.findElement(by);
		}
		catch(NoSuchElementException exception){
			return false;
		}
		return element.isDisplayed();
	}

	public int getTotalMatchedNoOfElements(By by){
		return driver.findElements(by).size();
	}

	public String getText(By by){
		return driver.findElement(by).getText();
	}

	public void waitAndSoftClick(By by){
		new FluentWait<AppiumDriver>(driver)
				.withTimeout(Duration.ofSeconds(3))
				.pollingEvery(Duration.ofMillis(250))
		        .ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class)
				.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitFor(long time) {
		try {
			Thread.sleep(time);
		}
		catch (InterruptedException e){
			return;
		}
	}

}