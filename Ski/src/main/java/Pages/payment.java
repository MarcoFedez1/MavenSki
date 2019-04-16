package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class payment {
	WebDriver driver;
	SoftAssert sassert = new SoftAssert();
	
	public payment(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[1]/div/input")
	private WebElement email;
	
	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[2]/div/input")
	private WebElement fname;

	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[3]/div/input")
	private WebElement lname;
	
	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[4]/div/input")
	private WebElement address1;
	
	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[6]/div/input")
	private WebElement city;
	
	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[7]/div/select")
	private WebElement state;
	
	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[8]/div/select")
	private WebElement country;

	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[9]/div/input")
	private WebElement zip;
	
	@FindBy (xpath = "//*[@id='submitForm']/fieldset[1]/div[10]/div/input")
	private WebElement phone;
	
	@FindBy (xpath = "//*[@id='creditCard']/div[1]/div/div/input")
	private WebElement cardNumber;
	
	@FindBy (xpath = "//*[@id='creditCard']/div[2]/div/input")
	private WebElement cvn;

	@FindBy (xpath = "//*[@id='creditCard']/div[3]/div/div/div/select[1]")
	private WebElement month;
	
	@FindBy (xpath = "//*[@id='creditCard']/div[3]/div/div/div/select[2]")
	private WebElement year;
	
	@FindBy (xpath = "//strong[@class='price money']")
	private WebElement total;
	
	@FindBy (css = ".btn-success")
	private WebElement ReserveNow;

	public String getPrice() {
		return total.getAttribute("innerText");
	}
	
	public void clickReserveNow() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", ReserveNow);
	}
	
	public void setBillingInformation(String Email, String Fname, String Lname, String Address, String City, String State, String Country, String zipcode, String Phone) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(email));
		email.clear();
		email.sendKeys(Email);
		fname.clear();
		fname.sendKeys(Fname);
		lname.clear();
		lname.sendKeys(Lname);
		address1.clear();
		address1.sendKeys(Address);
		city.clear();
		city.sendKeys(City);
		Select s = new Select(state);
		s.selectByValue(State);
		Select c = new Select(country);
		c.selectByValue(Country);
		zip.clear();
		zip.sendKeys(zipcode);
		phone.clear();
		phone.sendKeys(Phone);
	}
	
	public void setPaymentMethod(String Card, String CVN, String Month, String Year) {
		cardNumber.clear();
		cardNumber.sendKeys(Card);
		cvn.clear();
		cvn.sendKeys(CVN);
		Select m = new Select(month);
		m.selectByValue(Month);
		Select y = new Select(year);
		y.selectByValue(Year);
	}
}
