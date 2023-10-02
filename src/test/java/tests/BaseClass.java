package tests;

import com.aventstack.extentreports.ExtentReports;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import utilities.ExtentManager;
import utilities.ReadConfig;

import java.util.concurrent.TimeUnit;

public class BaseClass {
	ReadConfig readConfig = new ReadConfig();
	public String baseUrl = readConfig.getBaseUrl();
	public String userName = readConfig.getUserName();
	public String password = readConfig.getPassword();
	public String chromePath = readConfig.getChromePath();
	public String passedTCSSPath = readConfig.getPassedTCSSPath();
	public String failedTCSSPath = readConfig.getFailedTCSSPath();

	public static ExtentReports ext ;


	public static WebDriver driver;
	public static Logger logger ;
	
	@Parameters("browser")
	@BeforeSuite
	public void setup(@Optional("chrome")String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webDriver.chrome.driver", chromePath);
			driver = new ChromeDriver();
		}
		ext = ExtentManager.createExtentReports();
		logger = Logger.getLogger("seleniumAutomation");
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@BeforeTest
	public void launchBrouser() throws InterruptedException {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.MILLISECONDS);
		driver.manage().window().fullscreen();
		Thread.sleep(3000);
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		ext.flush();

	}

	@BeforeMethod
	public void maximizeWindow(ITestResult result){
			driver.manage().window().maximize();
	}
	

}
