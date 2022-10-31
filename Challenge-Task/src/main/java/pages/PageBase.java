package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
	//create constructor
	public PageBase(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//method to send values 
	public static void sendvalues(WebElement txt, String value) {
		txt.sendKeys(value);
	}
	//method to click on search button
	public static void click(WebElement button) {
		button.click();
	}

}
