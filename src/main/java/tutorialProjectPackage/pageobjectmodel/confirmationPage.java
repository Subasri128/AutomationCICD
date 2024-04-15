package tutorialProjectPackage.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tutorialProjectAbstractComponents.AbstractComponents;

public class confirmationPage extends AbstractComponents {
	 
	WebDriver driver;
	public confirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement displayMessage;

	public String getDisplayMessage() {
		return displayMessage.getText();
	}
}
