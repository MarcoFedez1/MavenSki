package Tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.Browser;
import Pages.epic;
import Pages.homeSki;
import Pages.payment;
import Pages.travelers;

public class epicTest {
	static WebDriver driver;
    static String Error;
    static String URL;
    static String homeURL;
    static String Price;
    SoftAssert softAssertion = new SoftAssert();
    @BeforeTest
    public void setup() throws MalformedURLException{
    	driver = Browser.LaunchBrowser("chrome");
    	homeURL = driver.getCurrentUrl();
    }
    
    @AfterTest
    public void after() {
    	driver.close();
    }
    
    @Test (priority = 0, description = "Verify that clicking epic image on PopUp from main page it goes to Epic page")
	public void ikonPage() {
    	homeSki homePage = new homeSki(driver);
    	homePage.clickEpicLink();
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/epic");
	}
    
    @Test (priority = 1, description = "Verify clicking 'Buy Now' Epic passes page is displayed")
	public void ikonPasses()  {
    	epic ePage = new epic(driver);
    	ePage.clickBuyNow();
    	ePage.waitForLoad(driver);
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/epic-passes");
	}
    
    @Test (priority = 2, description = "Verify user is able to add pass for Adults, and verify prices")
	public void epicAdultPasses() throws InterruptedException  {
    	int numAdult = 1;
    	epic ePage = new epic(driver);
    	float [] prices = ePage.AddAdultPass(numAdult);
    	Assert.assertEquals(prices[1], prices[0]*numAdult);
	}
    
    
    @Test (priority = 3, description = "Verify user is able to add pass for Child, and verify prices")
	public void ikonChildPasses() throws InterruptedException  {
    	int numChild = 2;
    	epic ePage = new epic(driver);
    	float [] prices = ePage.AddChildPass(numChild);
    	Assert.assertEquals(prices[1], prices[0]*numChild);
	}
    
    @Test (priority = 4, description = "Verify that ckicking on 'Lets Book It' travelers information page should be displayed")
	public void clickLetBook() throws InterruptedException   {
    	epic ePage = new epic(driver);
    	String price = ePage.getPrice();
    	ePage.clickLetBookIt();
    	travelers tPage = new travelers(driver);
    	Assert.assertEquals(price, tPage.getPrice());
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/travelers");
	}
    
    @Test (priority = 5, description = "Fill travelers information and click 'Continue'")
	public void setTravelers() throws InterruptedException   {
    	travelers tPage = new travelers(driver);
    	String [] names = {"Marco",  "Pablo", "Jocelyn", "Victor"};
    	String [] bdays = {"06/25/1985", "02/14/2012", "08/07/2008", "10/14/2017" };
    	tPage.setAdultTraveler(names, "Fernandez", bdays);
    	String price = tPage.getPrice();
    	tPage.clickContinue();
    	payment payPage = new payment(driver);
    	Assert.assertEquals(payPage.getPrice(), price);
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/payment");
	}
    
    @Test (priority = 6, description = "Fill billing and payment information and click 'Reserve Now'")
	public void BillingInformation() throws InterruptedException   {
    	payment payPage = new payment(driver);
    	payPage.setBillingInformation("fedez.marco1@gmail.com", "Marco", "Fernandez", "504 N Jordan Ave", "Liberal", "KS", "US", "69701", "6325897485");
    	payPage.setPaymentMethod("4111111111111111", "235", "05", "2020");
    	payPage.clickReserveNow();
    }
}
