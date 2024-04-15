package tutorialProjectPackage.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tutorialProjectAbstractComponents.AbstractComponents;

//using inheritance

public class LoginPage extends AbstractComponents {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(driver,this);
	}
	
	//usage1
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	
	//usage2 PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement Submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	
	//By Action Class
	
	public ProductCatalogue LoginApplication(String email , String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		Submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	

	public void URL() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String geterrormessage() {
		webElementtoappear(errormessage);
		return errormessage.getText();
	}
	

}
