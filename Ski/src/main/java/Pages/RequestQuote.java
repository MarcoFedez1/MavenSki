package Pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RequestQuote {
	WebDriver driver;
	
	public RequestQuote (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	
	@FindBy (css = "#main > h1:nth-child(1)")
	private WebElement title;
	
	@FindBy (id = "firstName")
	private WebElement fname;
	
	@FindBy (id = "lastName")
	private WebElement lname;
	
	@FindBy (id = "email")
	private WebElement email;
	
	@FindBy (id = "phoneNumber")
	private WebElement phoneNumber;
	
	@FindBy (css = ".radio")
	private List<WebElement> contactby;
	
	@FindBy (id = "startDateRfq")
	private WebElement startDate;
	
	@FindBy (id = "endDateRfq")
	private WebElement endDate;
	
	@FindBy (xpath = "//div[@class='datepicker-days']")
	private WebElement calendar;
	
	@FindBy (id = "FlexibleYes")
	private WebElement FlexibleYes;
	
	@FindBy (id = "FlexibleNo")
	private WebElement FlexibleNo;
	
	@FindBy (id = "bookedYes")
	private WebElement bookedYes;	
	
	@FindBy (id = "bookedNo")
	private WebElement bookedNo;
	
	@FindBy (id = "s2id_autogen1")
	private WebElement Destinations;	
	
	@FindBy (id = "adults")
	private WebElement Adults;
	
	@FindBy (id = "children")
	private WebElement Kids;
	
	@FindBy (id = "seniors")
	private WebElement Seniors;
	
	@FindBy (css = "#rfqForm > div:nth-child(3) > fieldset > div > div.col-sm-6 > div.col-sm-12")
	private WebElement chilAge;
	
	@FindBy (id = "star_rating")
	private WebElement starRating;
	
	@FindBy (id = "numberOfBedrooms")
	private WebElement Bedrooms;
	
	@FindBy (id = "unitType")
	private WebElement unitType;
	
	@FindBy (id = "locationType")
	private WebElement proximity;	
	
	@FindBy (css = ".checkbox")
	private List<WebElement> Items;
	
	@FindBy (id = "searchDepartures")
	private WebElement Airport;
	
	@FindBy (id = "comments")
	private WebElement comments;
	
	@FindBy (css = "button.btn:nth-child(3)")
	private WebElement submitBnt;
	
	@FindBy (css = "#rfqForm > div.form-actions.top20 > div > div.col-sm-3 > div > div > label.checkbox > input")
	private WebElement SignUpNews;
	
	@FindBy (xpath = "//div[@data-ng-show='includeLiftTickets']")
	private WebElement LiftTickets;
	
	@FindBy (xpath = "//div[@data-ng-show='includeRentalEquipment']")
	private WebElement RentalEquipment;
	
	@FindBy (id = "waitspinner")
	private WebElement waitspinner;
	
	public void setRequestQuote(String first, String last, String e, String phone) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(fname));
		fname.clear();
		lname.clear();
		email.clear();
		phoneNumber.clear();
		fname.sendKeys(first);
		lname.sendKeys(last);
		email.sendKeys(e);
		phoneNumber.sendKeys(phone);
		contactby.get(0).click();
	}
	
	public String getTitle() {
		return title.getAttribute("innerText");
	}
	
	public void setStartEndDates() throws InterruptedException {
		//date format
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd-yyyy", Locale.ENGLISH);	
		//get actual date
		Date d = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		//add one month
		calendar.add(Calendar.WEEK_OF_YEAR, 5);
		startDate.sendKeys(sdf.format(calendar.getTime()));
		selectDates(sdf.format(calendar.getTime()));
		//add one week
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		endDate.sendKeys(sdf.format(calendar.getTime()));
		selectDates(sdf.format(calendar.getTime()));
	}
	
	
	public String getMonthName() {
		return calendar.findElement(By.cssSelector(".datepicker-switch")).getAttribute("innerText");
	}
	
	public void setDestinations(String d1, String d2, String d3) {
		Destinations.sendKeys(d1 + Keys.ENTER + d2 + Keys.ENTER + d3 + Keys.ENTER);
	}
	
	public void FexiblesDates() {
		FlexibleYes.click();
	}
	
	public void NoFexiblesDates() {
		FlexibleNo.click();
	}
	
	public void clickBookedYes() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", bookedYes);
	}
	
	public void clickBookedNo() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", bookedNo);
	}
	
	public void setComments() {
		comments.sendKeys("Marco Test");
	}
	
	public void clickSubmit() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", submitBnt);
	}
	
	public void clickSignUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", SignUpNews);
	}
	
	public void selectDates(String date) throws InterruptedException {
		String [] Date = date.split("-");
		String startDay = Date[1];
		String startMonth = Date[0];
		String year = Date[2];
		String monthYear = startMonth + " " + year;
		monthYear = monthYear.toUpperCase();
		
		boolean b = true;
		while(b) {
			Thread.sleep(1000);
			String monthName = getMonthName();
			if (monthName.equals(monthYear)) {
				List<WebElement> Row = calendar.findElements(By.cssSelector("tbody > tr"));
				for (int l = 1; l < Row.size(); l++) {	
					List<WebElement> Col = Row.get(l).findElements(By.tagName("td"));
					for(int k = 0; k < Col.size(); k++) {
						String c = Col.get(k).getAttribute("class");
						if(c.equals("day")) {
							String n = Col.get(k).getAttribute("innerText");
							if(n.equals(startDay)) {
								Col.get(k).click();	
								b = false;
								break;
							}
						}
					}
					if(!b) {
						break;
					}
				}
			}else 
				calendar.findElement(By.className("next")).click();
			}
		}
	
	//values 0 to 9
	public void setTravelers(int Adult, int Kid, int Senior) {
		Select adult = new Select(Adults);
		adult.selectByValue(String.valueOf(Adult));
		Select kid = new Select(Kids);
		kid.selectByValue(String.valueOf(Kid));
		Select senior = new Select(Seniors);
		senior.selectByValue(String.valueOf(Senior));
		setChildAges();
	}
	
	public void setChildAges() {
		List<WebElement> childs = chilAge.findElements(By.tagName("select"));
		for (int i = 0; i < childs.size(); i ++) {
			Select kidsAges = new Select(childs.get(i));
			int age = (int) Math.round((Math.random() * ((12 - 1) + 1)) + 1);
			kidsAges.selectByValue(String.valueOf(age));
		}
	}
	
	public void setStarRating(int stars) {
		Select star = new Select(starRating);
		star.selectByValue(String.valueOf(stars - 1));
	}
	
	public void setNumBedrooms(int Beds) {
		Select Bedroom = new Select(Bedrooms);
		Bedroom.selectByIndex(Beds + 1);
	}
	
	public void setRoomType(int index) {
		Select Type = new Select(unitType);
		Type.selectByIndex(index);
	}
	
	public void setProximity(int index) {
		Select prox = new Select(proximity);
		prox.selectByIndex(index);
	}
	
	public void clickIncludeAllItems() {
		for(int i = 0;i < Items.size();i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			js.executeScript("arguments[0].click()", Items.get(i));
		}
	}
	
	public void setDepartureAirport() {
		if(Airport.isDisplayed()) {
			Airport.sendKeys("TX" + Keys.ENTER);
		}
	}
	
	public void setRentalEquipment() {
		if(RentalEquipment.isDisplayed()) {
			List<WebElement> travelers = RentalEquipment.findElements(By.tagName("select"));
			for (int i = 0; i < travelers.size(); i ++) {
				Select travels = new Select(travelers.get(i));
				travels.selectByIndex(1);
			}
		}
	}
	
	public void setLiftTickets() {
		if(LiftTickets.isDisplayed()) {
			List<WebElement> travelers = LiftTickets.findElements(By.tagName("select"));
			for (int i = 0; i < travelers.size(); i ++) {
				Select travels = new Select(travelers.get(i));
				travels.selectByIndex(3);
			}
		}
	}
	
	public void InvisibilityofWaitSpinner() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOf(waitspinner));
		Thread.sleep(3000);
	}
	
}
