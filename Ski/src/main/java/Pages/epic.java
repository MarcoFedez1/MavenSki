package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class epic {
	WebDriver driver;
	SoftAssert sassert = new SoftAssert();
	
	public epic(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

	@FindBy (xpath = "//a[@href='/epic-passes']")
	private WebElement BuyNow;
	
	@FindBy (xpath = "//div[@class='results-listing']")
	private List<WebElement> results;
	
	@FindBy (xpath = "//strong[@class='price money']")
	private WebElement TotalPrice;
	
	@FindBy (xpath = "//a[@href='#renewPasses']")
	private WebElement reNewPassTab;
	
	@FindBy (xpath = "//a[@href='#regularPasses']")
	private WebElement regularPassTab;
	
	@FindBy (xpath = "//a[@href='/travelers']")
	private WebElement  LetsBookIt;
	
	@FindBy (id = "renewPasses")
	private WebElement renewPasses;
	
	@FindBy (id = "regularPasses")
	private WebElement regularPasses;
	
	@FindBy (xpath = "//a[@href = '/ikon']")
	private WebElement ikon;
	
	@FindBy (xpath = "//a[@href = '/epic']")
	private WebElement epic;
	
	public void clickBuyNow() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(BuyNow));
		BuyNow.click();
	}
	
	public String getPrice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return TotalPrice.getAttribute("innerText");
	}
	
	public void resetAlltickets() throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
			numPass.selectByIndex(0);		
		}
	}
	
	public void AllepicPassPrice() throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String price = results.get(i).findElement(By.className("price-sml")).getAttribute("innerText");
				price = price.replace("US$", "");
				price = price.replace("/ person", "");
				Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
				List<WebElement> opt = numPass.getOptions();
				for(int j = 1; j < opt.size(); j ++) {
					numPass.selectByIndex(j);
				}
			}
		}
	}
	
	public float [] AddAdultPass(int numberofTickets) throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String ages = results.get(i).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(i).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 13 - 99") && typePass.equals("Adult Epic 7 Day Pass ")) {
					Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
					numPass.selectByIndex(numberofTickets);
					float[] prices = {};
					prices[0] = Float.valueOf(results.get(i).findElement(By.cssSelector(".price-sml")).getAttribute("innerText").replace("$", "").replace("/ person", ""));
					prices[1] = Float.valueOf(results.get(i).findElement(By.cssSelector(".money")).getAttribute("innerText").replace("$", "").replace("/ person", ""));
					return prices;
				}
				break;
			}
		}
		return null;
	}
	
	public float [] AddChildPass(int numberofTickets){
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String ages = results.get(i).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(i).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 5 - 12") && typePass.equals("Child Epic 7 Day Pass ")) {
					Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
					numPass.selectByIndex(numberofTickets);
					float[] prices = {};
					prices[0] = Float.valueOf(results.get(i).findElement(By.cssSelector(".price-sml")).getAttribute("innerText").replace("$", "").replace("/ person", ""));
					prices[1] = Float.valueOf(results.get(i).findElement(By.cssSelector(".money")).getAttribute("innerText").replace("$", "").replace("/ person", ""));
					return prices;
				}
				break;
			}
			
		}
		return null;
	}
	
	public void clickLetBookIt() {
		LetsBookIt.click();
	}
	
    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
    
    public void clickIkon() {
    	ikon.click();
    }
    
    public void clickEpic() {
    	epic.click();
    }
}
