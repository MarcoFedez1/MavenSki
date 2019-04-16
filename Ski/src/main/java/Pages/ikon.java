package Pages;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class ikon {
	WebDriver driver;
	SoftAssert sassert = new SoftAssert();
	
	public ikon(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

	@FindBy (xpath = "//a[@href='/ikon-passes']")
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
	
	public void clickBuyNow() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(BuyNow));
		BuyNow.click();
	}
	
	public String getPrice() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return TotalPrice.getAttribute("innerText");
	}
	
	public void resetAlltickets() throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
			numPass.selectByIndex(0);
			Thread.sleep(100);
			
		}
		DecimalFormat formato2 = new DecimalFormat("#.#");
		float TotalPrice = Float.valueOf(getPrice().replace("$", "").replace(" Total", "").replace(",", ""));
	}
	
	public void AllIkonPassPrice() throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String price = results.get(i).findElement(By.className("price-sml")).getAttribute("innerText");
				price = price.replace("$", "");
				price = price.replace("/ person", "");
				Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
				List<WebElement> opt = numPass.getOptions();
				for(int j = 1; j < opt.size(); j ++) {
					numPass.selectByIndex(j);
					Thread.sleep(300);
					String subtotal = results.get(i).findElement(By.className("money")).getAttribute("innerText");
					subtotal = subtotal.replace("$", "").replace(",", "");
					Thread.sleep(300);
					float TotalPrice = Float.valueOf(getPrice().replace("$", "").replace(" Total", "").replace(",", ""));
					float sub = Float.valueOf(subtotal);
					float iprice = Float.valueOf(price);
					DecimalFormat formato2 = new DecimalFormat("#.##");
					sassert.assertEquals(formato2.format(sub), formato2.format(iprice * j));
					sassert.assertEquals(formato2.format(TotalPrice), formato2.format(sub));
					numPass.selectByIndex(0);
				}
			}
		}
		sassert.assertAll();
	}
	
	public void AddAdultPass(int numberofTickets) throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String ages = results.get(i).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(i).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 23 - 99") && typePass.equals("IKON Season Pass. ")) {
					String price = results.get(i).findElement(By.className("price-sml")).getAttribute("innerText");
					price = price.replace("$", "").replace("/ person", "");
					Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
					numPass.selectByIndex(numberofTickets);
					Thread.sleep(100);
					String subtotal = results.get(i).findElement(By.className("money")).getAttribute("innerText");
					subtotal = subtotal.replace("$", "").replace(",", "");
					Thread.sleep(300);
					float TotalPrice = Float.valueOf(getPrice().replace("$", "").replace(" Total", "").replace(",", ""));
					float sub = Float.valueOf(subtotal);
					float iprice = Float.valueOf(price);	
					DecimalFormat formato2 = new DecimalFormat("#.##");
					sassert.assertEquals(formato2.format(sub), formato2.format(iprice * numberofTickets));
				}
				
			}
		}
		sassert.assertAll();
	}
	
	public void AddTeensPass(int numberofTickets) throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String ages = results.get(i).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(i).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 13 - 22") && typePass.equals("IKON Season Pass. ")) {
					String price = results.get(i).findElement(By.className("price-sml")).getAttribute("innerText");
					price = price.replace("$", "").replace("/ person", "");
					Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
					numPass.selectByIndex(numberofTickets);
					Thread.sleep(100);
					String subtotal = results.get(i).findElement(By.className("money")).getAttribute("innerText");
					subtotal = subtotal.replace("$", "").replace(",", "");
					float sub = Float.valueOf(subtotal);
					float iprice = Float.valueOf(price);
					DecimalFormat formato2 = new DecimalFormat("#.##");
					sassert.assertEquals(formato2.format(sub), formato2.format(iprice * numberofTickets));

				}
				
			}
		}
		sassert.assertAll();
	}
	
	public void AddChildPass(int numberofTickets) throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String ages = results.get(i).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(i).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 5 - 12") && typePass.equals("IKON Season Pass. ")) {
					String price = results.get(i).findElement(By.className("price-sml")).getAttribute("innerText");
					price = price.replace("$", "").replace("/ person", "");
					Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
					numPass.selectByIndex(numberofTickets);
					Thread.sleep(100);
					String subtotal = results.get(i).findElement(By.className("money")).getAttribute("innerText");
					subtotal = subtotal.replace("$", "").replace(",", "");
					float sub = Float.valueOf(subtotal);
					float iprice = Float.valueOf(price);
					DecimalFormat formato2 = new DecimalFormat("#.##");
					sassert.assertEquals(formato2.format(sub), formato2.format(iprice * numberofTickets));
				}
				
			}
		}
		sassert.assertAll();
	}
	
	public void AddToddlerPass(int numberofTickets) throws InterruptedException {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String ages = results.get(i).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(i).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 0 - 4") && typePass.equals("IKON Season Pass. ")) {
					String price = results.get(i).findElement(By.className("price-sml")).getAttribute("innerText");
					price = price.replace("$", "").replace("/ person", "");
					Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
					numPass.selectByIndex(numberofTickets);
					Thread.sleep(100);
					String subtotal = results.get(i).findElement(By.className("money")).getAttribute("innerText");
					subtotal = subtotal.replace("$", "").replace(",", "");
					float sub = Float.valueOf(subtotal);
					float iprice = Float.valueOf(price);
					DecimalFormat formato2 = new DecimalFormat("#.##");
					sassert.assertEquals(formato2.format(sub), formato2.format(iprice * numberofTickets));
				}
				
			}
		}
		sassert.assertAll();
	}
	
	public void AddChildTicketsPassAdult() throws InterruptedException {
		for (int j = 0; j < results.size(); j++) {
			if (results.get(j).isDisplayed()) {
				String ages = results.get(j).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(j).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 23 - 99") && typePass.equals("IKON Season Pass. ")) {
					List<WebElement> moreTickets = results.get(j).findElements(By.xpath("//div[@class='row listItem separator']"));
					for (int i = 0; i < moreTickets.size(); i ++) {
						float iPrice = Float.valueOf(getPrice().replace("$", "").replace(" Total", "").replace(",", ""));
						float passPrice = Float.valueOf(moreTickets.get(i).findElement(By.className("price-sml")).getAttribute("innerText").replace("US$", ""));
						JavascriptExecutor js = (JavascriptExecutor) driver;  
						js.executeScript("arguments[0].click()", moreTickets.get(i).findElement(By.tagName("button")));	
						Thread.sleep(1000);
						float totalPrice = Float.valueOf(getPrice().replace("$", "").replace(" Total", "").replace(",", ""));
						sassert.assertEquals(totalPrice, iPrice + passPrice);
					}
				}
				
			}
		}	
	}
	
	public void removeChildTicketsPassAdult() throws InterruptedException {
		for (int j = 0; j < results.size(); j++) {
			if (results.get(j).isDisplayed()) {
				String ages = results.get(j).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(j).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 23 - 99") && typePass.equals("IKON Season Pass. ")) {
					List<WebElement> moreTickets = results.get(j).findElements(By.xpath("//div[@class='row listItem separator']"));
					for (int i = 0; i < moreTickets.size(); i ++) {
						float iPrice = Float.valueOf(getPrice().replace("$", "").replace(" Total", "").replace(",", ""));
						float passPrice = Float.valueOf(moreTickets.get(i).findElement(By.className("price-sml")).getAttribute("innerText").replace("US$", ""));
						JavascriptExecutor js = (JavascriptExecutor) driver;  
						js.executeScript("arguments[0].click()", moreTickets.get(0).findElement(By.tagName("a")));	
						Thread.sleep(1000);
						float totalPrice = Float.valueOf(getPrice().replace("$", "").replace(" Total", "").replace(",", ""));
						sassert.assertEquals(totalPrice, iPrice + passPrice);
					}
				}
				
			}
		}	
	}
	
	public void clickLetBookIt() {
		LetsBookIt.click();
		
	}
	
	public void clickregularPassTab() {
		regularPassTab.click();
	}
	
	public void clickreNewPassTab() {
		reNewPassTab.click();
	}
	
	public void AddAdultRenewPass(int numberofTickets) throws InterruptedException {
		List<WebElement> results = renewPasses.findElements(By.className("results-listing"));
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).isDisplayed()) {
				String ages = results.get(i).findElement(By.tagName("small")).getAttribute("innerText");
				String typePass = results.get(i).findElement(By.className("cursor-pointer")).getAttribute("innerText");
				if(ages.equals("Ages 23 - 99") && typePass.contains("IKON BASE Season Pass: Renewal Rate. ")) {
					String price = results.get(i).findElement(By.className("price-sml")).getAttribute("innerText");
					price = price.replace("$", "").replace("/ person", "");
					Select numPass = new Select(results.get(i).findElement(By.tagName("select")));
					numPass.selectByIndex(numberofTickets);
					Thread.sleep(100);
					String subtotal = results.get(i).findElement(By.className("money")).getAttribute("innerText");
					subtotal = subtotal.replace("$", "").replace(",", "");
					float TotalPrice = Float.valueOf(getPrice().replace("$", "").replace(" Total", "").replace(",", ""));
					float sub = Float.valueOf(subtotal);
					float iprice = Float.valueOf(price);					
					DecimalFormat formato2 = new DecimalFormat("#.##");
					sassert.assertEquals(formato2.format(sub), formato2.format(iprice * numberofTickets));
					//sassert.assertEquals(formato2.format(TotalPrice), formato2.format(sub));
				}
				
			}
		}
		sassert.assertAll();
	}
}
