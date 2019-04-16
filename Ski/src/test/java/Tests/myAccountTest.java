package Tests;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.Browser;
import Pages.Register;
import Pages.homeSki;
import Pages.login;

public class myAccountTest {
	static WebDriver driver;
    static String Error;
    static String homeURL;
    
    @BeforeTest
    public void setup() throws MalformedURLException{
    	driver = Browser.LaunchBrowser("chrome");
    	homeURL = driver.getCurrentUrl();
    }
    
    @Test (priority = 0)
    public void GotoLoginPage() {
    	homeSki home = new homeSki(driver);
    	home.closeNowonSale();
    	home.clickLoginLink();
    	Assert.assertEquals(driver.getCurrentUrl(), "https://www.ski.com/account/login");
    }
    
    @Test (priority = 1)
    public void GotoRegisterPage() {
    	login logPage = new login(driver);
    	logPage.clickRegister();
    	Assert.assertEquals(driver.getCurrentUrl(), "https://www.ski.com/account/user/register");
    }
    
    @Test (priority = 2)
    public void UserRegister() throws InterruptedException {
    	Register regPage = new Register(driver);
    	regPage.CompletRegister("Marco", "Fernandez", "fedezmarco@gmail.com", "Markitos01", "Markitos01", "33112333");
    	Thread.sleep(500);   	
    	Assert.assertEquals(driver.getCurrentUrl(), "https://www.ski.com/account/user/register");
    }
    
    @Test (priority = 3)
    public void UserLogin() throws InterruptedException {
    	homeSki home = new homeSki(driver);
    	home.clickLoginLink();
    	login lgPage = new login(driver);
    	lgPage.LoginIn("fedezmarco@gmail.com", "Markitos01");
    	Thread.sleep(500);
    	Assert.assertEquals(driver.getCurrentUrl(), "https://www.ski.com/");
    }
    
    
}
