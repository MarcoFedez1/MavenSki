package Tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.Browser;
import Pages.homeSki;
import Pages.ikon;
import Pages.payment;
import Pages.travelers;

public class ikonTest {
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
    
    @Test (priority = 0, description = "Verify that ckicking Ikon image on PopUp from main page goto Ikon page")
	public void ikonPage() {
    	homeSki homePage = new homeSki(driver);
    	homePage.closeNowonSale();
    	homePage.clickIkonLink();
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/ikon");

	}
    
    @Test (priority = 1, description = "Verify clicking 'Buy Now' ikon passes page is displayed")
	public void ikonPasses()  {
    	ikon iPage = new ikon(driver);
    	iPage.clickBuyNow();
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/ikon-passes");
	}
    
    @Test (priority = 2, description = "Verify user is able to add pass for Adults, and verify prices")
	public void ikonAdultPasses() throws InterruptedException  {
    	ikon iPage = new ikon(driver);
    	iPage.AddAdultPass(1);
	}
    
    @Test (priority = 3, description = "Verify user is able to add pass for Teens, and verify prices")
	public void ikonTeensPasses() throws InterruptedException  {
    	ikon iPage = new ikon(driver);
    	iPage.AddTeensPass(1);
	}
    
    @Test (priority = 4, description = "Verify user is able to add pass for Child, and verify prices")
	public void ikonChildPasses() throws InterruptedException  {
    	ikon iPage = new ikon(driver);
    	iPage.AddChildPass(3);
	}
    
    @Test (priority = 5, description = "Verify user is able to add pass for Toddler, and verify prices")
	public void ikonToddlerPasses() throws InterruptedException  {
    	ikon iPage = new ikon(driver);
    	iPage.AddToddlerPass(1);
	}

    @Test (priority = 6, description = "Verify that user is able to add/remove extra passes from unlocked section")
	public void addremoveExtraPasses() throws InterruptedException  {
    	ikon iPage = new ikon(driver);
    	iPage.AddAdultPass(1);
    	iPage.AddChildTicketsPassAdult();
    	Thread.sleep(500);
    	iPage.removeChildTicketsPassAdult();
    	iPage.resetAlltickets();
	}
    
    @Test (priority = 7, description = "Verify that clicking on 'Lets Book It' travelers information page should be displayed")
	public void clickLetBook() throws InterruptedException   {
    	ikon iPage = new ikon(driver);
    	iPage.AddAdultPass(1);
    	iPage.AddChildPass(1);
    	iPage.AddTeensPass(1);
    	iPage.AddToddlerPass(1);
    	Thread.sleep(300);
    	String price = iPage.getPrice();
    	iPage.clickLetBookIt();
    	travelers tPage = new travelers(driver);
    	Assert.assertEquals(price, tPage.getPrice());
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/travelers");
	}
    
    @Test (priority = 8, description = "Fill travelers information and click 'Continue'")
	public void setTravelers() throws InterruptedException   {
    	travelers tPage = new travelers(driver);
    	String [] names = {"Marco",  "Pablo", "Jocelyn", "Victor"};
    	String [] bdays = {"06/25/1985", "02/14/2012", "08/07/2002", "10/14/2017" };
    	tPage.setAdultTraveler(names, "Fernandez", bdays);
    	String price = tPage.getPrice();
    	tPage.clickContinue();
    	payment payPage = new payment(driver);
    	Assert.assertEquals(payPage.getPrice(), price);;
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/payment");
	}
    
    @Test (priority = 9, description = "Fill billing and payment information and click 'Reserve Now'")
	public void BillingInformation() throws InterruptedException   {
    	payment payPage = new payment(driver);
    	payPage.setBillingInformation("fedez.marco1@gmail.com", "Marco", "Fernandez", "504 N Jordan Ave", "Liberal", "KS", "US", "69701", "6325897485");
    	payPage.setPaymentMethod("4111111111111111", "235", "05", "2020");
    	payPage.clickReserveNow();
    }
}
