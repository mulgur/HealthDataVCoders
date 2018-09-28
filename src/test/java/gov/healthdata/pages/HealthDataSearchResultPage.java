package gov.healthdata.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.healthdata.utilities.Driver;
/* This class extends HealthDataMainPage
 * all the WebElements of main page are accessible from this class
 * Example: HealthDataSearchResultPage.searchField
 *     HealthDataSearchResultPage.searchButton will work on second page
 */

public class HealthDataSearchResultPage extends HealthDataMainPage {
	public HealthDataSearchResultPage() {
	PageFactory.initElements(Driver.getDriver(), this);
	}
	
	
	@FindBy (xpath="//div[@class='view-header']") //by Khaliunaa
	public WebElement searchResultNumber;
	
	@FindBy (xpath="(//h2[@class='pane-title ctools-collapsible-handle'])[3]") //by Khaliunaa
	public WebElement tags;
	
	@FindBy (xpath="//a[contains(text(),'medicaid')]") //Khaliunaa
	public WebElement medicaidLink;
	@FindBy(xpath="//a[@id='anch_20']")
	public WebElement homepage;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']")
	public WebElement characterCount;
	
	
	@FindBy(id="edit-query")
	public WebElement searchResultHealth;  // by KimyaNur
	
	@FindBy(xpath="//*[@id=\"facetapi-link\"]")
	public WebElement beforeresultFiltreHealthOfHealth;    // by KimyaNur
	
	@FindBy(id="facetapi-link")
	public WebElement healthClick;  // by KimyaNur
	
	@FindBy(xpath="//div[@class='view-header']")
	public WebElement resultFiltreHealthOfHealth;  // by KimyaNur
	
	@FindBy(xpath="//a[@id='facetapi-link--6']")
	public WebElement hospitalClick;  // by KimyaNur
	
	@FindBy(xpath="//div[@class='view-header']")
	public WebElement resultfiltrehospital;  // by KimyaNur
	
	
	@FindBy(id="facetapi-link--90")
	public WebElement dataSet; // by Merve
	
	@FindBy(id="facetapi-link--91")
	public WebElement resource; // by Merve
	
	@FindBy(id="facetapi-link--92")
	public WebElement blog; // by Merve
	
	@FindBy(xpath="//div[@class='view-header']")
	public WebElement result;  // by Merve
	
	@FindBy(xpath="//ul[@class='breadcrumb']/li[3]")
	public WebElement searchedText;  // by Marta
	
	
	
	
	
	
	
}
