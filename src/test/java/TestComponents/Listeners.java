package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import tutorialProjectPackage.GlobalComponents.ExtentReportNG;

public class Listeners extends BaseTestExecution implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal();
	@Override
	public  void onTestStart(ITestResult result) {
	    // not implemented
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //Assigns one unique thread id
		
	  }

	  
	  public  void onTestSuccess(ITestResult result) {
	    // not implemented
		  
		  extentTest.get().log(Status.PASS, "Test Passes");
		 
	  }

	  public  void onTestFailure(ITestResult result) {
	    // not implemented
		  extentTest.get().fail(result.getThrowable());
		  
		  try {
			  driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		  }catch (Exception e1) {
			  e1.printStackTrace();
		  }
		  
		  String filePath = null;
		  try {
		  filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		  } catch(IOException e) {
			  e.printStackTrace();}
		  extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		  }
	  
	  public  void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	  
	  public  void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }



	  public  void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }



	  public  void onStart(ITestContext context) {
	    // not implemented
	  }


	  public  void onFinish(ITestContext context) {
	    // not implemented
		  extent.flush();
	  }

}
