package tutorialProjectPackage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import TestComponents.BaseTestExecution;
import TestComponents.Retry1;
import tutorialProjectPackage.pageobjectmodel.CartPage;
import tutorialProjectPackage.pageobjectmodel.CheckoutScreen;
import tutorialProjectPackage.pageobjectmodel.ProductCatalogue;
import tutorialProjectPackage.pageobjectmodel.confirmationPage;

public class ErrorValidations extends BaseTestExecution {
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry1.class)
	public void errorValidations() throws IOException, InterruptedException
	{
		 loginPage.LoginApplication("studen011@gmail.com", "Studentstudent@01");
		Assert.assertEquals("Incorrect email or password.", loginPage.geterrormessage());
		//Assert.assertEquals("Incorrect email  password.", loginPage.geterrormessage()); 
	}
	@Test
	public void Pdterrorvalidation() throws IOException, InterruptedException
	{
		
		// TODO Auto-generated method stub
		String pdtname  = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = loginPage.LoginApplication("student02@gmail.com", "Student@02");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(pdtname);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match =cartPage.VerifyProductDisplay("ADIDAS ORIGINAL1");
		Assert.assertFalse(match);
		
		}
	

}
