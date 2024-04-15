package tutorialProjectPackage.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tutorialProjectAbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> orderedPdts;

	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean VerifyOrderDisplay(String pdtname) {
		Boolean match = orderedPdts.stream().anyMatch(cartpdt->cartpdt.getText().equalsIgnoreCase(pdtname));
		return match;
	}
	

}
