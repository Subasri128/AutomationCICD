package tutorialProjectPackage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTestExecution;
import io.github.bonigarcia.wdm.WebDriverManager;
import tutorialProjectPackage.pageobjectmodel.CartPage;
import tutorialProjectPackage.pageobjectmodel.CheckoutScreen;
import tutorialProjectPackage.pageobjectmodel.LoginPage;
import tutorialProjectPackage.pageobjectmodel.OrderPage;
import tutorialProjectPackage.pageobjectmodel.ProductCatalogue;
import tutorialProjectPackage.pageobjectmodel.confirmationPage;

public class standaloneTest extends BaseTestExecution {
	String pdtname  = "ADIDAS ORIGINAL";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void standaloneTest(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue = loginPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("pdtname"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		Boolean match =cartPage.VerifyProductDisplay(input.get("pdtname"));
		Assert.assertTrue(match);
		CheckoutScreen checkOutScreen = cartPage.checkoutPage();
		checkOutScreen.selectCountry("India");
		confirmationPage confirmationPage = checkOutScreen.submitOrder();
		
		String msgdisplay =confirmationPage.getDisplayMessage(); 
		Assert.assertTrue(msgdisplay.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}
	
	@Test(dependsOnMethods= {"standaloneTest"})
	public void verifyOrder()
	{
		ProductCatalogue productCatalogue = loginPage.LoginApplication("student01@gmail.com", "Student@01");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(pdtname));
	}
	
	
	
	
	
	//Extent reports
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/**HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "student01@gmail.com");
		map.put("password", "Student@01");
		map.put("pdtname", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "student02@gmail.com");
		map1.put("password", "Student@02");
		map1.put("pdtname", "ZARA COAT 3");**/
		List<HashMap<String, String>> data =getjsonDatatoMap(System.getProperty("user.dir") + "\\src\\test\\java\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
