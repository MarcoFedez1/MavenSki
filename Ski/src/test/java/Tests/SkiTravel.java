package Tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.homeSki;
import Pages.payment;
import Pages.Browser;
import Pages.HoteDetails;
import Pages.Lodging;
import Pages.checkout;


public class SkiTravel {
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
	
	
	@Test (priority = 0, description = "Verify that selecting destination, dates, and travelers is able to search travel")
	public void SelectTravelDates() throws InterruptedException {
		homeSki homePage = new homeSki(driver);
		homePage.closeNowonSale();
		homePage.setDestination("Aspen");
		String dates = homePage.setStartEndDates();
		homePage.clickTravelers();
		homePage.setTravelers(3,0,1);
		homePage.clickSearch();
		Lodging l = new Lodging(driver);
		Assert.assertEquals(l.getTitle(), "Available Lodging " + dates);
	}
	
	@Test (priority = 2, description = "Begin checkout process")
	public void SelectHotelStepOne() {
		Lodging l = new Lodging(driver);
		l.SwitchWinTabs();
		String [] info = l.reviewHotel(0);
		HoteDetails hdPage = new HoteDetails(driver);
		String HotelName = hdPage.getHotelName();
 		Assert.assertEquals(HotelName, info[1]);
	}
	
	@Test (priority = 3, description = "Select room")
	public void SelectRoomStepTwo() throws InterruptedException {
		HoteDetails hdPage = new HoteDetails(driver);
		String infoDetails = hdPage.PriceDisplayed();
		String [] info1 = infoDetails.split("-");
		if (info1[0].equals("true")) {
			Price = hdPage.clickSelectRoom();
			Thread.sleep(500);
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.ski.com/book/checkout");
		}
	}
	
	@Test (priority = 4, description = "Verify correct amount")
	public void VerifyTotalPriceStepThree() throws InterruptedException {
		checkout checkPage = new checkout(driver);
		payment payPage = new payment(driver);
    	payPage.setBillingInformation("fedez.marco1@gmail.com", "Marco", "Fernandez", "504 N Jordan Ave", "Liberal", "KS", "US", "69701", "6325897485");
    	payPage.setPaymentMethod("4111111111111111", "235", "05", "2020");
    	payPage.clickReserveNow();
		Assert.assertEquals(checkPage.getTotalPrice(), Price);
	}
}
