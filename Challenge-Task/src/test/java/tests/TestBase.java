package tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import data.ExcelReader;

public class TestBase extends ExtentReport{

	String path= "System.getProperty(\"user.dir\")+\"/src/test/java/data/Data.xlsx\"";
	public static WebDriver driver;
	public static  String getScreenshootPath(String path) throws IOException {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File DestFile=new File(path);
		//Copy file at destination
		FileUtils.copyFile(source, DestFile);
		return path;
	}	

	//read data from excel reader class
	@DataProvider(name="Data")
	public Object [][] getURL() throws IOException{
		ExcelReader ER=new ExcelReader();
		return ER.getExcelData();
	}

	@BeforeSuite
	@Parameters({"browser"})
	//to start the selected browser change the value of browser in testng.xml file
	public void startDriver(String browser) {
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));}

		else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));}


		else if(browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/drivers/msedgedriver.exe");
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));}
	}


	//get the url data from data.xlxs file

	@Test(dataProvider = "Data")
	public void navigteToGoogle(String URL) throws Exception {
		driver.navigate().to(URL);
		//export the screenshot to test log named "navigate to google"
		ExtentTest test = extent.createTest("Navigate To Google");
		test.pass("pass", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshootPath(System.getProperty("user.dir")+"/screenShoots/image.png")).build());
	}


	@AfterSuite
	public void closeDriver(){
		driver.quit();
	}

}
