package Pages;

import java.util.Iterator;
import java.util.List;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HoteDetails {
	WebDriver driver;
	public HoteDetails (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

	@FindBy (css = "#main > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)")
	private WebElement Price;
	
	@FindBy (css = "#ContentArea > h1")
	private WebElement title;
	
	@FindBy (css = ".waitspinner")
	private WebElement Spinner;
	
	@FindBy (css = "a.btn-primary")
	private WebElement SelectRoom;
	
	@FindBy (css = "#roomPricingList > div > div.ng-scope > div.bottom10")
	private List<WebElement> RoomsOptions;
	

	public String getHotelName() {
		return title.getAttribute("innerText");
	}
	public String PriceDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOf(Spinner));
		boolean a = false;
		try {
			Price.findElement(By.cssSelector("span.ng-binding"));
			a = true;
		} catch(org.openqa.selenium.NoSuchElementException e) {
			a = false;
		}	
		if(a) {
			String price = Price.findElement(By.cssSelector("span.ng-binding")).getAttribute("innerText");	
			price = price.replace("US$", "");
			price = price.replace("/night", "");
			return "true-" + price;
		}	else {
			return "false-";
		}
	}
	
	public void closeNewTabs() {
		  // It will return the parent window name as a String
		String parent=driver.getWindowHandle();
	    // This will return the number of windows opened by Webdriver and will return Set of St//rings
	 	Set<String>s1=driver.getWindowHandles();
	 	// Now we will iterate using Iterator
	 	Iterator<String> I1= s1.iterator();
	 	while(I1.hasNext())
	 	{
	 		String child_window=I1.next();
	   		if(!parent.equals(child_window))	  {
	 			driver.switchTo().window(parent).close();
	 			driver.switchTo().window(child_window);
	 		}
	 	}
	}
	
	public String clickSelectRoom() throws InterruptedException {
		WebElement button = RoomsOptions.get(0).findElement(By.cssSelector("a.btn"));
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		String BntText = button.getAttribute("innerText");
		switch (BntText) {
		case "SELECT":
			String total = RoomsOptions.get(0).findElement(By.cssSelector("span.price.ng-binding")).getAttribute("innerText");
			js.executeScript("arguments[0].click()", button);
			return total;
		case "REQUEST A BOOKING":
			total = RoomsOptions.get(0).findElement(By.cssSelector("span.price.ng-binding")).getAttribute("innerText");
			js.executeScript("arguments[0].click()", button);
			return total;
		case "PRICE IT!":
			js.executeScript("arguments[0].click()", button);
			Thread.sleep(1000);
			total = RoomsOptions.get(0).findElement(By.cssSelector("span.price.ng-binding")).getAttribute("innerText");
			js.executeScript("arguments[0].click()", button);
			return total;
		default:
			return "";
		}
		
		
		
	}
}
