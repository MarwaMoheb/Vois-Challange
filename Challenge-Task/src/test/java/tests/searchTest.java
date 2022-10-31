package tests;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.io.Files;

import pages.GooglePage;

public class searchTest extends TestBase{
	GooglePage googlePageOBJ;
	public int count2;
	public int count3;
	
	@BeforeTest
	//define object from google page
	public void intializeObjets() {
		googlePageOBJ = new GooglePage(driver);
	}

	@Test(priority = 1 )
	public void GoogleSearch() throws InterruptedException, IOException {
		//provide a log test with name "search"
		ExtentTest test = extent.createTest("search");

		//search with word "vodafone"
		googlePageOBJ.Search("Vodafone");
		//take screenshot in the report to each step passed
		test.info("search with vodafone", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshootPath(System.getProperty("user.dir")+"/screenShoots/vodafone.png")).build());

		//check that vodafone image  is displayed in the first search page  
		System.out.println(googlePageOBJ.vodafoneImage.isDisplayed());


		//scroll down in the first page 
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		test.info("scroll down" , MediaEntityBuilder.createScreenCaptureFromPath(getScreenshootPath(System.getProperty("user.dir")+"/screenShoots/scrollDown.png")).build());

		//test case that pages navigate to page number 2
		googlePageOBJ.NavigateToSecondPage();
		test.info("click on 2 page link" , MediaEntityBuilder.createScreenCaptureFromPath(getScreenshootPath(System.getProperty("user.dir")+"/screenShoots/page2.png")).build());
		//test case that pages navigate to page number 3
		googlePageOBJ.NavigateToThirdPage();
		test.info("click on 3rd page link" , MediaEntityBuilder.createScreenCaptureFromPath(getScreenshootPath(System.getProperty("user.dir")+"/screenShoots/page3.png")).build());
		//test case compare number of elements in page 2 and page 3
		googlePageOBJ.compare(count3, count2);

	}
	@AfterMethod
	//take screenshot if test case failed
	public void recordFailure(ITestResult result){

		if(ITestResult.FAILURE == result.getStatus())
		{
			TakesScreenshot camera = (TakesScreenshot)driver;
			File screenshot = camera.getScreenshotAs(OutputType.FILE);
			try {
				Files.move(screenshot, new File("resources/screenshots/test.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
}



