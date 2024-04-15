package tutorialProjectPackage.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import tutorialProjectAbstractComponents.AbstractComponents;

public class CheckoutScreen extends AbstractComponents {
	WebDriver driver;
	public CheckoutScreen(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectingCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath=" //body//app-root//button[2]")
	WebElement selectIndia;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(selectingCountry,countryName).build().perform();
		elementtoappear(By.cssSelector(".ta-results"));
		selectIndia.click();
		}
	public confirmationPage submitOrder() {
		submit.click();
		return new confirmationPage(driver);
		
		
	}
}


