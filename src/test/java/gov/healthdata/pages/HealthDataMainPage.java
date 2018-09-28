package gov.healthdata.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gov.healthdata.utilities.Driver;

public class HealthDataMainPage {
	public HealthDataMainPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id="edit-search")
	public WebElement searchField;
	
	@FindBy(id="edit-submit")
	public WebElement searchButton;
	
	
	
	
	

}
