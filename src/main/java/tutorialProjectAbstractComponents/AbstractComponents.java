package tutorialProjectAbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tutorialProjectPackage.pageobjectmodel.CartPage;
import tutorialProjectPackage.pageobjectmodel.OrderPage;

public class AbstractComponents {
	WebDriver driver; 
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderlist;
	
	
	
	
	public CartPage goToCartPage() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public  OrderPage goToOrderPage() {
		orderlist.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	//Holds all reusable class
	public void elementtoappear(By FindBy){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
}
	
	public void webElementtoappear(WebElement FindBy){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(FindBy));
}

	public void elementtodisappear(WebElement ele1) throws InterruptedException{
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
	//wait.until(ExpectedConditions.invisibilityOf(ele1));
}
}
