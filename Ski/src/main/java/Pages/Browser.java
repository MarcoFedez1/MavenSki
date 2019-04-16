package Pages;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser {
	private static String prod = "https://www.ski.com/";
	public static String appUrl = prod;
	//Method to create driver 
	public static WebDriver LaunchBrowser(String browserName) throws MalformedURLException {
        WebDriver driver= null;
		
        switch(browserName) {	        
		case "firefox": 
			//gecko driver necessary to run Eclipse3
		   	System.setProperty("webdriver.gecko.driver", "C:\\Users\\Panfilo\\Prueba-ws\\Automa39dg\\geckodriver.exe");
			//Set up private FF windows
		   	FirefoxOptions opts = new FirefoxOptions();
			opts.addArguments("-private");			
			//Create FF Driver
			driver = new FirefoxDriver(opts);
			break;
		
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "/Users/mfernandez/Desktop/chromedriver");
			//set up incognito chrome windows
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			//create Chrome driver
			driver = new ChromeDriver(options);
			break;
		}
		driver.get(appUrl);
		driver.manage().window().maximize();
		return driver;
		
	}
}
