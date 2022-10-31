package tests;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {
	static WebDriver driver;
	//create html report
	ExtentReports extent;
	@BeforeSuite
	public void setup() throws IOException {
		extent=new ExtentReports();
		ExtentSparkReporter spark= new ExtentSparkReporter("reportWithScreenshoot.html");
		spark.loadXMLConfig(new File("extentReport.xml"));
		extent.attachReporter(spark);
	}
	//It is mandatory to call the flush method to ensure information is written to the started reporters
	@AfterSuite
	public  void teardown() throws IOException {
		extent.flush();
		//launch the default browser on the desktop
		Desktop.getDesktop().browse(new File("reportWithScreenshoot.html").toURI());	
	}
}