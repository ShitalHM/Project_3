package com.framewok.utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Keyword {
	public static RemoteWebDriver driver;
	public static WebDriverWait wait;
	public static ChromeOptions opts=new ChromeOptions();
	public static Select b;
	/**
	 * This method will open Browser in maximized mode
	 * @param browseName
	 * <ul>
	 * <li>Chrome</li>
	 * <li>Firefox</li>
	 * <li>Internet Explorer</li>
	 * </ul>
	 */
	public static void openBrowser(String browseName) {		
		if(browseName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			opts.addArguments("--incongnito");
			
			driver=new ChromeDriver(opts);
		}else if(browseName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(browseName.equalsIgnoreCase("Internet Explorer")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}else {
			System.err.println("Invalid Browser Name");
		}
		System.out.println("Browser: "+browseName);
		driver.manage().window().maximize();
	}
	/**
	 * This method will launch URL 
	 */
	public static  void launchURL(String url) {
		driver.get(url);
	}
	/**
	 * This method will wait for specified time in seconds
	 * @param time in sec
	 */
	public static void waitFor(WebElement e, String action) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		if(action.equalsIgnoreCase("click")) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
		}else if(action.equalsIgnoreCase("enterText")) {
			wait.until(ExpectedConditions.visibilityOf(e));
		}
		else
			wait.until(ExpectedConditions.elementToBeSelected(e));
	}
	/**
	 * This method will click on WebElement
	 * @param locatorType  
	 * @param locatorValue
	 */
	public static void click(String locatorType, String locatorValue) {
		WebElement e= getWebElement(locatorType,locatorValue);
		waitFor(e,"click");
		b=new Select(e);
		//b
		e.click();
	}
	/**
	 * This method will click on WebElement to enter Text in Textbox
	 * @param locatorType:  
	 * @param locatorValue
	 * @param textToEnter
	 */
	public static void enterText(String locatorType, String locatorValue,String textToEnter) {
		WebElement e=getWebElement(locatorType,locatorValue);
		waitFor(e,"enterText");
		e.sendKeys(textToEnter);
	}
	/**
	 * This method will get webElement 
	 * @param action
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public static WebElement getWebElement(String locatorType,String locatorValue) {
		if(locatorType.equalsIgnoreCase("Xpath")) {
			return driver.findElement(By.xpath(locatorValue));
		}else if(locatorType.equalsIgnoreCase("CSS")) {
			return driver.findElement(By.cssSelector(locatorValue));
		}else if(locatorType.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(locatorValue));
		}else {
			System.out.println("Invalid Locator Type");
			return null;
		}	
	}
}
