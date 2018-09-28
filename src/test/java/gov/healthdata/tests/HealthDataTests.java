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
 * This class will have all the TestNG TestCases.
 */
public class HealthDataTests {

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
		driver.manage().window().fullscreen();
		driver.get(ConfigurationReader.getProperty("url"));
		mainPage = new HealthDataMainPage();
		secondPage = new HealthDataSearchResultPage();
	}

	@Test(priority = 1, description = "T003 by Khaliunaa")
	public void positiveSearch() {
		mainPage.searchField.sendKeys(data1);
		mainPage.searchButton.click();
		int count = Integer.parseInt(secondPage.searchResultNumber.getText().split(" ")[0]);
		assertTrue(count > 1, "Search result is not more than 1");
	}

	@Test(priority = 2, description = "T013 by Khaliunaa")
	public void tagOptions() {
		mainPage.searchField.sendKeys(data1);
		mainPage.searchButton.click();

		assertFalse(secondPage.medicaidLink.isDisplayed(), "Tags options are displayed");
		secondPage.tags.click();
		assertTrue(secondPage.medicaidLink.isDisplayed(), "Tags options are not displayed");
		secondPage.tags.click();
		BrowserUtils.waitFor(2);
		assertFalse(secondPage.medicaidLink.isDisplayed(), "Tags options are displayed");
	}

	@Test(priority = 3, description = "T05 by Ahmet")
	public void EmptySearch() {
		mainPage.searchField.sendKeys("");
		mainPage.searchButton.click();
		assertEquals(driver.getTitle(), "HealthData.gov");
	}

	@Test(priority = 4, description = "T15 by Ahmet")
	public void ThreespaceSearch() {
		mainPage.searchField.sendKeys("   ");
		mainPage.searchButton.click();
		assertTrue(driver.getPageSource().contains("results "));
	}

	@Test(priority = 5, description = "T16 by Ahmet")
	public void Repeated() {
		mainPage.searchField.sendKeys(data1);
		mainPage.searchButton.click();
		String title1 = driver.getTitle();
		System.out.println(title1);

		secondPage.homepage.click();

		mainPage.searchField.sendKeys(data1);
		mainPage.searchButton.click();
		String title2 = driver.getTitle();
		System.out.println();
		assertEquals(title1, title2);

	}

	@Test(priority = 6, description = "TC001 by KimyaNur")
	public void Title() {
		String expectedUrl = "HealthData.gov";
		assertTrue(driver.getCurrentUrl().toLowerCase().contains(expectedUrl.toLowerCase()));
		String expectedtitle = "HealthData.gov";
		assertEquals(expectedtitle, driver.getTitle());
	}

	@Test(priority = 7, description = "TC011 by KimyaNur")
	public void Verify_the_result_match_filter_keyword_topic_result() {
		String expectedUrl = "https://healthdata.gov/";
		assertTrue(driver.getCurrentUrl().contains(expectedUrl));
		mainPage.searchField.sendKeys("health");
		mainPage.searchButton.click();

		String beforeresultFiltre = findNumber(secondPage.beforeresultFiltreHealthOfHealth.getText());
		secondPage.healthClick.click();
		String resultFiltre = findNumber(secondPage.resultFiltreHealthOfHealth.getText());
		assertTrue(beforeresultFiltre.contains(resultFiltre));

		String beforeResultofhospital = findNumber(secondPage.hospitalClick.getText());
		secondPage.hospitalClick.click();
		String Resultofhospital = findNumber(secondPage.resultfiltrehospital.getText());
		assertTrue(beforeResultofhospital.contains(Resultofhospital));

		assertTrue(Integer.parseInt(beforeresultFiltre) > Integer.parseInt(Resultofhospital));

	}
	
	@Test(priority = 8, description = "TC06 by Kubanychbek")
	public void Verifying_the_maximum_capacity_of_the_characters_in_the_search_box() {
		int count=0;
		for(int i=1; i<128; i++) {	
			mainPage.searchField.sendKeys("p");
			count++;	
		}	
		mainPage.searchButton.click();
		int expectedCount=(secondPage.characterCount.getText().length())-10;
		assertTrue(count==expectedCount);	
	}

	

	@Test(priority = 9, description = "TC002 by Merve")
	public void SearchButton() {
		assertTrue(mainPage.searchField.isDisplayed());
	}

	@Test(priority = 10, description = "TC012 by Merve")
	public void SearchItem() {
		mainPage.searchField.sendKeys("child care", Keys.ENTER);

		System.out.println(findNumber(secondPage.dataSet.getText()));
		int data = Integer.parseInt(findNumber(secondPage.dataSet.getText()));
		int resrc = Integer.parseInt(findNumber(secondPage.resource.getText()));
		int blg = Integer.parseInt(findNumber(secondPage.blog.getText()));
		int reslt = Integer.parseInt(findNumber(secondPage.result.getText()));
		assertEquals(reslt, (data + resrc + blg));

	}
	
	@Test(priority = 11, description = "TC004 by Marta")
	public void notRelatedSearchResult() {
		mainPage.searchField.sendKeys(data2);
		mainPage.searchButton.click();
		
		assertTrue(driver.getPageSource().contains("No results were found. Please try another keyword."));
	}
	@Test(priority = 12, description = "TC019 by Marta")
	public void typoCorrection() {
		mainPage.searchField.sendKeys(data3);
		mainPage.searchButton.click();
	
		assertTrue(secondPage.searchedText.getText().contains(data3));
	}
	@Test(priority = 13, description = "TC020 by Marta")
	public void incorrectGrammer() {
		mainPage.searchField.sendKeys(data3);
		mainPage.searchButton.click();
		assertTrue(driver.getPageSource().contains("No results were found. Please try another keyword."));
	}

	@AfterMethod
	public void tearDown() {
		Driver.closeDriver();
	}

	public static String findNumber(String s) { // by KimyaNur
		String num = "";
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i)))
				num += s.charAt(i);
		}
		return num;
	}

}
