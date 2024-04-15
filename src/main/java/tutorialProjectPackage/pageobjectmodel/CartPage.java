package tutorialProjectPackage.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tutorialProjectAbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
WebDriver driver;
@FindBy(css=".cartSection h3")
private List<WebElement> cartpdts;

@FindBy(css=".totalRow button")
WebElement checkout;

	public CartPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean VerifyProductDisplay(String pdtname) {
		Boolean match = cartpdts.stream().anyMatch(cartpdt->cartpdt.getText().equalsIgnoreCase(pdtname));
		return match;
	}
	
	
	public CheckoutScreen checkoutPage() {
		checkout.click();
		return new CheckoutScreen(driver);
		
	}
}
