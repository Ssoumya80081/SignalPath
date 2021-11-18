package com.signalpath.base.framework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumUtils {

	public WebDriver driver;
	public Actions action = null;

	public WebDriver launchBrowser() {

		String driverPath = System.getProperty("user.dir")+"/lib/";
		String appUrl = "https://todomvc.com/examples/angular2/";

		String browserType = System.getProperty("browserType");

		if (browserType.toLowerCase().trim().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
			driver = new ChromeDriver();
		}

		driver.get(appUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		action = new Actions(driver);

		return driver;

	}

	public By getByObject(LocatorTypes locator, String locatorValue) {
		By by = null;
		switch (locator) {
		case XPATH:
			by = By.xpath(locatorValue);
			break;
		case ID:
			by = By.id(locatorValue);
			break;
		case CLASS:
			by = By.className(locatorValue);
			break;
		case TAGNAME:
			by = By.tagName(locatorValue);
			break;
		case LINKTEXT:
			by = By.partialLinkText(locatorValue);
			break;
		case NAME:
			by = By.name(locatorValue);
			break;
		}
		return by;
	}
	
	public List<WebElement> getElements(LocatorTypes locator, String locatorValue) {
		return driver.findElements(getByObject(locator, locatorValue));
	}
	
	public WebElement getElement(LocatorTypes locator, String locatorValue) {
		List<WebElement> elements = getElements(locator, locatorValue);
		if (elements.size() > 0)
			return elements.get(0);
		else
			return null;
	}

	public void enterText(LocatorTypes locator, String locatorValue, String textToAdd) {
		WebElement ele = getElement(locator, locatorValue);
		ele.sendKeys(textToAdd);
	}
	
	public void clickOnElement(LocatorTypes locator, String locatorValue) {
		WebElement ele = getElement(locator, locatorValue);
		ele.click();
	}
	
	public void mouseOverElement(LocatorTypes locator, String locatorValue) {
		action.moveToElement(getElement(locator, locatorValue)).build().perform();
	}
	
	public void pressEnter(WebElement element) {
		if (element != null)
			element.sendKeys(Keys.ENTER);
		else
			action.sendKeys(Keys.ENTER);
	}
	
	public boolean isElementDisplayed(LocatorTypes locator, String locatorValue) {
		return getElement(locator, locatorValue).isDisplayed();
	}
	
	public String getAttributeValue(LocatorTypes locator, String locatorValue, String attribute) {
		return getElement(locator, locatorValue).getAttribute(attribute);
	}

	public void quitBrowser() {
		driver.quit();
	}
	
	public boolean verifyPageTitle(String expTitle) {
		String actualTitle = driver.getTitle();
		if (actualTitle.contains(expTitle))
			return true;
		else
			return false;
	}
}
