package tutorialProjectPackage.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tutorialProjectAbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy =By.cssSelector(".mb-3");
	By addToCart =By.cssSelector(".card-body button:last-of-type");
	By toastMessageLoader= By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		elementtoappear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String pdtname) {
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equals(pdtname)).findFirst().orElse(null);
		return prod;
		}
	
	public void addProductToCart(String pdtname) throws InterruptedException {
		WebElement prod = getProductByName(pdtname);
		prod.findElement(addToCart).click();
		elementtoappear(toastMessageLoader);
		elementtodisappear(spinner);
	}
	
	
	
	
	
	
}
