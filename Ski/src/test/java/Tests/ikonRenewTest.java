package Tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.Browser;
import Pages.homeSki;
import Pages.ikon;
import Pages.payment;
import Pages.travelers;

public class ikonRenewTest {
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
    	//driver.close();
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
    
    @Test (priority = 2, description = "Verify user is able to display renewal passes")
	public void ikonRenewalPasses() throws InterruptedException  {
    	ikon iPage = new ikon(driver);
    	iPage.clickreNewPassTab(); 	
	}
    
    @Test (priority = 3, description = "Verify that user is able to add renewal passes")
	public void AddAdultPasses() throws InterruptedException  {
    	ikon iPage = new ikon(driver);
    	iPage.AddAdultRenewPass(1);
	}
    
    @Test (priority = 4, description = "Verify that clicking on 'Lets Book It' travelers information page should be displayed")
	public void clickLetBook() throws InterruptedException   {
    	ikon iPage = new ikon(driver);
    	String price = iPage.getPrice();
    	iPage.clickLetBookIt();
    	travelers tPage = new travelers(driver);
    	Assert.assertEquals(price, tPage.getPrice());
    	Assert.assertEquals(driver.getCurrentUrl(), "https://epic.events.ski.com/travelers");
	}
    
    @Test (priority = 5, description = "Fill travelers information and click 'Continue'")
	public void setTravelers() throws InterruptedException   {
    	travelers tPage = new travelers(driver);
    	String [] names = {"Marco"};
    	String [] bdays = {"06/25/1985"};
    	String [] lastPass = {"2546322222"};
    	tPage.setReNewAdultTraveler(names, "Fernandez", bdays, lastPass);
    	String price = tPage.getPrice();
    	tPage.clickContinue();
    	Thread.sleep(500);
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
