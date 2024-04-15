package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestComponents.BaseTestExecution;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tutorialProjectPackage.pageobjectmodel.CartPage;
import tutorialProjectPackage.pageobjectmodel.CheckoutScreen;
import tutorialProjectPackage.pageobjectmodel.LoginPage;
import tutorialProjectPackage.pageobjectmodel.ProductCatalogue;
import tutorialProjectPackage.pageobjectmodel.confirmationPage;

public class StepDefinition extends BaseTestExecution {
	
	public LoginPage loginPage;
	public ProductCatalogue productCatalogue;
	public confirmationPage confirmationPage;
	@Given("I landed on ECOMMERCE page")

	public void I_landed_on_ECOMMERCE_page() throws IOException
	{
		loginPage = browserlaunch();
	}
	// its a form of regular expression
	@Given("^Logged in with username (.+) and password (.+)$")
	
	public void Logged_in_with_username_and_password(String username,String password )
	{
		productCatalogue = loginPage.LoginApplication(username,password);
	}
	//When I add product <pdtname> to cart
	 @When("^I add product (.+) to cart$")
	 public void i_add_product_to_cart(String pdtname ) throws InterruptedException
	 {
		 List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(pdtname);
		
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void Checkout_and_submit_the_order(String pdtname)
	 {
		 CartPage cartPage = productCatalogue.goToCartPage();
			
			
			Boolean match =cartPage.VerifyProductDisplay(pdtname);
			Assert.assertTrue(match);
			CheckoutScreen checkOutScreen = cartPage.checkoutPage();
			checkOutScreen.selectCountry("India");
			confirmationPage = checkOutScreen.submitOrder();
	 }
	 
	 @Then ("{string} message is displayed on confirmation page")
	 public void  message_is_displayed_on_confirmation_page(String string)
	 {
		 String msgdisplay =confirmationPage.getDisplayMessage(); 
			Assert.assertTrue(msgdisplay.equalsIgnoreCase(string));
	 }
	 //tidy gherkin
	 
	 //Then "Incorrect email or password." message is visible
	 @Then ("{string} message is visible")
	 
		public void message_is_visible(String string1) throws Throwable
		{
		 Assert.assertEquals(string1, loginPage.geterrormessage());
		 driver.close();	 }
}
