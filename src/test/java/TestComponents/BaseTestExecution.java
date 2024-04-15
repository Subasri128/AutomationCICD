package TestComponents;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import tutorialProjectPackage.pageobjectmodel.LoginPage;

public class BaseTestExecution {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initilazingalltestcases() throws IOException {

		Properties props = new Properties();
		FileInputStream inputStream = new FileInputStream("C:\\Users\\a819538\\eclipse-workspace\\"
				+ "tutorialProjectArtifact\\src\\main\\java\\tutorialProjectPackage\\GlobalComponents\\GlobalData.properties");
		props.load(inputStream);
		//by using java ternary operator if browser is given in cmd it will run that broswer or else it will run in default broswer specified in code.
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser") :props.getProperty("browser");
		//String browsername = props.getProperty("browser");

		if (browsername.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless")) {
			options.addArguments("headless");
			}
			
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440,900));//full screen

		} else if (browsername.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getjsonDatatoMap(String filepath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage browserlaunch() throws IOException {

		driver = initilazingalltestcases();
		loginPage = new LoginPage(driver);
		loginPage.URL();
		return loginPage;

	}

	public String getScreenshot(String testcaseName , WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";

	}

	@AfterMethod(alwaysRun = true)
	public void closebrowser() {
		driver.close();

	}
}
