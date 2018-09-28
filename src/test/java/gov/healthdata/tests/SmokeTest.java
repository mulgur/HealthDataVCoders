package gov.healthdata.tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import gov.healthdata.pages.HealthDataMainPage;
import gov.healthdata.pages.HealthDataSearchResultPage;

import gov.healthdata.utilities.BrowserUtils;
import gov.healthdata.utilities.ConfigurationReader;
import gov.healthdata.utilities.Driver;

/*
 * This class has 4 Smoke Tests
 */

public class SmokeTest {
	WebDriver driver;
	HealthDataMainPage mainPage;
	HealthDataSearchResultPage secondPage;

	String data1 = "Health";
	String data2="Sausage";
	String data3="halth";

	@BeforeMethod
	public void setUp() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(ConfigurationReader.getProperty("url"));
		mainPage = new HealthDataMainPage();
		secondPage = new HealthDataSearchResultPage();
	}
	
	@Test(priority = 1, description = "Smoke Test 1")
	public void ThreespaceSearch() {
		mainPage.searchField.sendKeys("   ");
		mainPage.searchButton.click();
		assertTrue(driver.getPageSource().contains("results "));
	}

	@Test(priority = 2, description = "Smoke Test 2")
	public void positiveSearch() {
		mainPage.searchField.sendKeys(data1);
		mainPage.searchButton.click();
		int count = Integer.parseInt(secondPage.searchResultNumber.getText().split(" ")[0]);
		assertTrue(count > 1, "Search result is not more than 1");
	}

	@Test(priority = 3, description = "Smoke Test 3")
	public void Title() {
		String expectedUrl = "HealthData.gov";
		assertTrue(driver.getCurrentUrl().toLowerCase().contains(expectedUrl.toLowerCase()));
		String expectedtitle = "HealthData.gov";
		assertEquals(expectedtitle, driver.getTitle());
	}
	
	@Test(priority = 4, description = "Smoke Test 4")
	public void SearchButton() {
		assertTrue(mainPage.searchField.isDisplayed());
	}
	
	@AfterMethod
	public void tearDown() {
		Driver.closeDriver();
	}
}
