package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkout {
	WebDriver driver;
	
	public checkout (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	
	@FindBy (css = "div.col-sm-4.ng-scope > div > span")
	private WebElement TotalPrice;
	
	@FindBy (id = "savetrip")
	private WebElement savetrip;
	
	@FindBy (css = "#savePlanLoginLabel > div.form-group > div > input")
	private WebElement tripName;
	
	@FindBy (css = "span.pull-right > div:nth-child(2) > a:nth-child(1)")
	private WebElement removeTrip;
	
	public String getTotalPrice() {
		try {
			driver.findElement(By.cssSelector("div.col-sm-4.ng-scope > div > span"));
			return TotalPrice.getAttribute("innerText");
		}catch (org.openqa.selenium.NoSuchElementException e){
			return "";
		}
		
	}
	
	public void clickSaveTrip() {
		savetrip.click();
	}
	
	public void setNameTrip() {
		tripName.sendKeys("Aspen");
	}
	
	public void clickRemoveTrip() {
		removeTrip.click();
	}
	
}
