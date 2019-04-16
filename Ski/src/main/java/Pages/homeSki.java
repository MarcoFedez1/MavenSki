package Pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




public class homeSki {
	WebDriver driver;
	
	public homeSki (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	
	@FindBy (css = "span.homeIcons:nth-child(3) > a:nth-child(1)")
	private WebElement loginLink;
	
	@FindBy (id = "searchDestinations")
	private WebElement searchDestination;
	
	@FindBy (id = "startDate")
	private WebElement startDate;
	
	@FindBy (id = "endDate")
	private WebElement endDate;
	
	@FindBy (css = "a.btn-default:nth-child(1)")
	private WebElement enterTraveler;
	
	@FindBy (css = "#enterTravelerInformation > div > div > div.modal-body > div:nth-child(1) > div:nth-child(1) > select")
	private WebElement Adults;
	
	@FindBy (id = "numberOfKids")
	private WebElement Kids;
	
	@FindBy (css = "#enterTravelerInformation > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > select:nth-child(2)")
	private WebElement Seniors;
	
	@FindBy (css = "button.btn-primary")
	private WebElement ContinueBnt;
	
	@FindBy (css = "#searchSubmit")
	private WebElement searchBnt;
	
	@FindBy (css = ".datepicker-days")
	private WebElement calendar;
	
	@FindBy (css = "#epicModal > div > div > div.modal-footer > button")
	private WebElement close;
	
	@FindBy (xpath = "//a[@href='/quote/hn']")
	private WebElement Quote;
	
	@FindBy (xpath = "//a[@href='/resorts']")
	private WebElement resorts;
	
	@FindBy (xpath = "//a[@href='https://epic.events.ski.com/ikon']")
	private WebElement ikonLink;
	
	@FindBy (xpath = "//a[@href='https://epic.events.ski.com/epic']")
	private WebElement epicLink;
	
	@FindBy (xpath = "//a[@href='/epic-vs-ikon']")
	private WebElement epicIkon;
	
	@FindBy (xpath = "//a[@href='https://epic.events.ski.com/epic-passes']")
	private WebElement epicPasses;
	
	public void clickEpicPasses() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", epicPasses);
	}
	
	public void clickEpicvsIkon() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", epicIkon);
	}
	public void clickLoginLink() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(loginLink));
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", loginLink);
	}
	public void setDestination(String Destination) {
		searchDestination.clear();
		searchDestination.sendKeys(Destination);
	}
	
	public String setStartEndDates() {
		//date format
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");	
		//get actual date
		Date d = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		//add one month
		calendar.add(Calendar.WEEK_OF_YEAR, 3);
		Actions action = new Actions(driver);
		Action mouseOverDate = action.moveToElement(startDate).build();
		mouseOverDate.perform();
		startDate.clear();
		startDate.sendKeys(sdf.format(calendar.getTime()));
		String date = sdf.format(calendar.getTime());
		//add one week
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		action = new Actions(driver);
		mouseOverDate = action.moveToElement(endDate).build();
		mouseOverDate.perform();
		endDate.clear();
		endDate.sendKeys(sdf.format(calendar.getTime()));
		date = date + " - " + sdf.format(calendar.getTime());
		return date;
	}
	
	public void closeNowonSale() {
		if(close.isDisplayed()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			js.executeScript("arguments[0].click()", close);
		}
	}
	
	public void clickTravelers() {
		Actions action = new Actions(driver);
		Action mouseOverTravelers = action.moveToElement(enterTraveler).build();
		mouseOverTravelers.perform();
		enterTraveler.click();
	}
	
	//values 0 to 9
	public void setTravelers(int Adult, int Kid, int Senior) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(Adults));
		Select adult = new Select(Adults);
		adult.selectByIndex(Adult);
		Select kid = new Select(Kids);
		kid.selectByIndex(Kid);
		Select senior = new Select(Seniors);
		senior.selectByIndex(Senior);
		ContinueBnt.click();
	}
	
	public void clickSearch() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", searchBnt);
	}	
	
	public void clickQuote() {
		Quote.click();
	}
	
	public void clickResorts() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", resorts);
	}
	
	public void clickIkonLink() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", ikonLink);
	}
	
	public void clickEpicLink() {
		epicLink.click();
	}
	
	public void clickIkonEpic() {
		Actions action = new Actions(driver);
		Action mouseOverTravelers = action.moveToElement(epicIkon).build();
		mouseOverTravelers.perform();
		epicIkon.click();
	}
	
	
}
