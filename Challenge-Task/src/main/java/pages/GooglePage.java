package pages;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends PageBase {

	public GooglePage(WebDriver driver) {
		super(driver);
	}
	//Google Page
	@FindBy(name = "q")
	WebElement googleSearchInput;
	@FindBy(xpath = "//div[@class='CcAdNb']//span[@class='QCzoEc z1asCe MZy1Rb']")
	WebElement googleSearchBTN;

	//First Page
	//vodafone icon in the first page
	@FindBy(xpath = "//div[@jscontroller=\"M0hWhd\"]//img")
	public WebElement vodafoneImage;
	//	@FindBy(xpath ="//*[@id=\"rso\"]//h3")
	//	public WebElement vodafoneLink;
	
	//second page number in the first page 
	@FindBy(xpath="//a[@aria-label='Page 2']")
	public WebElement Page2;

	//check second page number appear to scroll down and navigate to third page
	@FindBy(id = "result-stats")
	public WebElement assertSecondPage;

	//list of search result in the second page 
	@FindBy(className = "g")
	public List<WebElement> secondPageListElements ;
	//third page number in the second page
	@FindBy(xpath = "//*[@id=\"botstuff\"]/div/div[2]/table/tbody/tr/td[4]/a")
	WebElement Page3;

	//list of search result in the third page 
	@FindBy(className = "g")
	public List<WebElement> thirdPageListElements ;

	//method to search in google
	public void Search(String searchInput) {
		sendvalues(googleSearchInput, searchInput);
		click(googleSearchBTN);
		googleSearchInput.sendKeys(Keys.ENTER);
	}

	//click on number 2 in pagination to navigate
	public void NavigateToSecondPage() {
		click(Page2);
	}

	//click on number 3 in pagination to navigate
	public void NavigateToThirdPage() {
		click(Page3);
	}

	//method that count search result
	public int countElement(List<WebElement> List , int count) {
		count=List.size();
		return count;
	}

	//method to compare numbers of elements in 2nd and 3rd page
	public void compare(int x , int y )
	{
		if(x==y)
		{
			System.out.println("number of elements in page 2 and 3 are equal");
		}
		else
			System.out.println("numbers of elements in page 2 and 3 are not equal");
	}

}