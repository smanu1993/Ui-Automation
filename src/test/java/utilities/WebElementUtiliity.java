package utilities;

import java.awt.*;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseClass;

public class WebElementUtiliity {
	
	static WebDriver driver = BaseClass.driver;

	public static void clickOnElement(WebElement elementToClick) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		try {
			isElementLoaded(elementToClick).click();
		} catch(Exception e) {
			js.executeScript("argument[0].click", elementToClick);
		}
	}
	
	public static void enterTextInTB(WebElement testBoxToSet, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		try {
			isElementLoaded(testBoxToSet).clear();
			testBoxToSet.sendKeys(text);
		} catch(Exception e) {
			js.executeScript("argument[0].value='"+text+"';", testBoxToSet);
		}
	}

	public static void scrollPageToAnElement(WebElement scrollElement){
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("argument[0].scrollIntoView(true)",scrollElement);
	}

	public static void scrollPageUsingPixel(int x_axis, int y_axis){
		//js.executeScript("window.scrollBy(x,y)")
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		js.executeScript("window.scrollBy("+x_axis+","+y_axis+")");
	}

	public static void mouseHoverAnElement(WebElement element){
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	public static WebElement isElementLoaded(WebElement elementToLoaded) {
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.visibilityOf(elementToLoaded));
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(5))
				.ignoring(ElementNotInteractableException.class)
				.ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class)
				.ignoring(ElementClickInterceptedException.class)
				.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.visibilityOf(elementToLoaded));
		return elementToLoaded;
	}
}
