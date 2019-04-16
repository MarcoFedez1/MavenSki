package Tests;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.Browser;
import Pages.RequestQuote;
import Pages.gmail;
import Pages.homeSki;

public class RequestQuoteTest {
	static WebDriver driver;
    static String Error;
    static String URL;
    static String homeURL;
    @BeforeTest
    public void setup() throws MalformedURLException{
    	driver = Browser.LaunchBrowser("chrome");
    	homeURL = driver.getCurrentUrl();
    }
    
    @Test (priority = 0, description = "Verify that clicking on 'Quote' from main menu Request a Quote Page is displayed")
	public void clickQoute() throws InterruptedException {
		homeSki homePage = new homeSki(driver);
		homePage.closeNowonSale();
		Thread.sleep(500);
		homePage.clickQuote();
		Assert.assertEquals(driver.getCurrentUrl(), homeURL + "quote/hn");
	}
    
    @Test (priority = 1, description = "Fill all information fields")
	public void fillRequestQuote() throws InterruptedException {
    	RequestQuote rqPage = new RequestQuote(driver);
    	rqPage.setRequestQuote("Marco", "Fernandez", "fedez.marco1@gmail.com", "3126589632");
    	rqPage.setStartEndDates();
    	rqPage.FexiblesDates();
    	rqPage.setDestinations("Aspen", "Okemo", "Valle Nevado");
    	rqPage.setTravelers(2, 1, 1);
    	//Star Rating (1 - 5)
    	rqPage.setStarRating(5);
    	//Number of bedrooms (0 - 5)
    	rqPage.setNumBedrooms(2);
    	/*Proximity: index = 1 - Hotel,   index = 2 - Condo,   index = 3 - Townhouse,  
		 			 index = 4 - Home,    index = 5 - Condo-Hotel */
    	rqPage.setRoomType(2);
    	/*Proximity: index = 1 - Slopeside or Ski-in/Ski-out, index = 2 - Less than 250 yards from lifts,   index = 3 - 250-500 yards from lifts,   
    	  			 index = 4 - On the shuttle route,        index = 5 - Walking distance from lifts,      index = 6 - Rental car recommended  */
    	rqPage.setProximity(2);
    	rqPage.clickIncludeAllItems();
    	rqPage.setDepartureAirport();
    	rqPage.setLiftTickets();
    	rqPage.setRentalEquipment();
    	rqPage.clickBookedNo();
    	rqPage.setComments();    	
	}

    @Test (priority = 2, description = "Submit a Quote")
	public void submitQuote() throws InterruptedException {
    	RequestQuote rqPage = new RequestQuote(driver);
    	rqPage.clickSignUp();
    	rqPage.clickSubmit();
    	rqPage.InvisibilityofWaitSpinner();
    	Assert.assertEquals(driver.getCurrentUrl(), "https://www.ski.com/quote/thankyou");
	}
    
    @Test (priority = 3, description = "Verify email")
	public void verifyEmail() throws InterruptedException {
    	driver.get("https://mail.google.com/mail");
    	gmail gmailPage = new gmail(driver);
    	gmailPage.gmailSingIn("fedez.marco1", "Lenovo@1");
    	String EmailSender = gmailPage.verifyEmail();
    	Assert.assertTrue(EmailSender.contains("@ski.com"));
	}
}
