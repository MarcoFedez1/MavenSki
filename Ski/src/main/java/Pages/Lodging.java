package Pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lodging {
	WebDriver driver;
	
	public Lodging (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	
	@FindBy (css = "section.results-listing")
	private List<WebElement> results;
	
	@FindBy (css = "#ContentArea > div > div.row > div > div > div > legend")
	private WebElement title;
	
	@FindBy (id = "tawkchat-message-preview-container")
	private WebElement tawkchat;
	
	@FindBy (css = "#tawkchat-message-preview-close > div")
	private WebElement closetawkchat;
	
	@FindBy (css = ".waitspinner > img:nth-child(1)")
	private WebElement waitspinner;
	
	@FindBy (css = "h5.top20 > b.ng-binding")
	private WebElement Nres;
	
	@FindBy (css = "ul.pagination-sm:nth-child(2)")
	private WebElement NextPage;
	
	@FindBy (css = "#exitpopupmodal > div > div > div.modal-header > button")
	private WebElement closePopUp;
	
	public int getNumberResults() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOf(waitspinner));
		return Integer.valueOf(Nres.getAttribute("innerText"));
	}
	
	
	public String getTitle() {
		return title.getAttribute("innerText");
	}
	
	public String clickSeeMyOptions(int i){		
			String Price = results.get(i).findElement(By.cssSelector("div > div.package-detail > div > div.price > div > div.ng-scope > span > span")).getAttribute("innerText");
			WebElement seeMyOptions = results.get(i).findElement(By.cssSelector("div.package-detail > div > a:nth-child(4)"));
			String HotelName = results.get(i).findElement(By.cssSelector("div > div.prop-info > div.padding > h3 > a")).getAttribute("innerText");
			String cadena = seeMyOptions.getAttribute("class");
			if(cadena.equals("btn btn-primary ng-binding")) { 
				String selectLinkOpeninNewTab = Keys.chord(Keys.COMMAND,Keys.RETURN); 
				seeMyOptions.sendKeys(selectLinkOpeninNewTab);
				SwitchWinTabs();
				return Price.replaceAll("US$", "") + "-" + HotelName+"-true";
			}else {
				WebElement requestQuote = results.get(i).findElement(By.cssSelector("div > div:nth-child(4) > div:nth-child(1) > a:nth-child(5)"));
				String selectLinkOpeninNewTab = Keys.chord(Keys.COMMAND,Keys.RETURN); 
				requestQuote.sendKeys(selectLinkOpeninNewTab);
				SwitchWinTabs();
				/*
				js.executeScript("arguments[0].click()", requestQuote);*/
				return "-" + HotelName + "-false";
			}
	}
	
	public String [] reviewHotel(int i) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOf(waitspinner));
		String PriceHotel = clickSeeMyOptions(i);
		String [] info = PriceHotel.split("-");
		info[0] = info[0].replace("US$", "");
		return info;
	}
	
	public void SwitchWinTabs() {
		// It will return the parent window name as a String
		String parent=driver.getWindowHandle();
	    // This will return the number of windows opened by Webdriver and will return Set of St//rings
	 	Set<String>s1=driver.getWindowHandles();
	 	// Now we will iterate using Iterator
	 	Iterator<String> I1= s1.iterator();
	 	while(I1.hasNext())
	 	{
	 		
	 		String child_window=I1.next();
	   		// Here we will compare if parent window is not equal to child window then we            will close
	 		if(!parent.equals(child_window))	  {
	 			driver.switchTo().window(child_window);
	 			driver.switchTo().window(child_window).getCurrentUrl();
	 		}
	 	}
	}
	
	public int [] numberTabs(int numRest) {
		int [] size = new int[] {0,0};
		size[0] = numRest % 10;
		if (size[0] > 0) {
			size[1] = (numRest/10) + 1;
		}else {
			size[1] = (numRest/10);
		}
		return size;
	}
	
	public void clickNextPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", NextPage);
	}
	
	public void clickClosePopUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", closePopUp);
	}
	
	
}
