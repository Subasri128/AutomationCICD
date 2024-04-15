package tutorialProjectPackage.GlobalComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public  static ExtentReports getReportObject()
	{
		String  path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Cycle 1 Results");
		reporter.config().setDocumentTitle("Release Version 9.0");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Subasri");
		extent.createTest(path);
		return extent;
		}

}
