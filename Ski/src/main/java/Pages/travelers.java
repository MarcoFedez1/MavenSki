package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class travelers {
	WebDriver driver;
	SoftAssert sassert = new SoftAssert();
	
	public travelers(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

	@FindBy (xpath = "//strong[@class='price money']")
	private WebElement total;
	
	@FindBy (css = ".top30")
	private List<WebElement> travelers;
	
	@FindBy (css = ".btn-success")
	private WebElement  continueBnt;
	
	@FindBy (xpath = "//a[@href='#renewPasses']")
	private WebElement reNewPassTab;
	
	@FindBy (xpath = "//a[@href='#regularPasses']")
	private WebElement regularPassTab;
	

	
	public String getPrice() throws InterruptedException {
		Thread.sleep(1000);
		return total.getAttribute("innerText");
	}
	//bdays format MM-DD-YYYY
 	public void setAdultTraveler(String [] fnames, String lname, String [] bdays ) throws InterruptedException {
 		boolean a = false;
 		try {
 			driver.findElement(By.className("text-danger"));
 			a = true;
 		} catch (NoSuchElementException e) {
 			a = false;
 		}
 		if(a) {
 			driver.navigate().back();
 			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
 			epic ePage = new epic(driver);
 			ePage.clickLetBookIt();
 	 		WebDriverWait wait = new WebDriverWait(driver, 15);
 	 		wait.until(ExpectedConditions.visibilityOfAllElements(travelers));
 			for (int i = 0; i < travelers.size(); i++) {
 				travelers.get(i).findElement(By.cssSelector("fieldset > div:nth-child(3) > div > input")).sendKeys(fnames[i]);//first name
 				travelers.get(i).findElement(By.cssSelector("fieldset > div:nth-child(4) > div > input")).sendKeys(lname);//last name
 				Select month = new Select(travelers.get(i).findElement(By.cssSelector("fieldset:nth-child(1) > div:nth-child(5) > div:nth-child(2) > select:nth-child(1)")));//month
 				String [] date = bdays[i].split("/");
 				month.selectByValue(date[0].replace("0", ""));
 				travelers.get(i).findElement(By.cssSelector("fieldset > div:nth-child(5) > div:nth-child(3) > input:nth-child(1)")).sendKeys(date[1]);//day
 				travelers.get(i).findElement(By.cssSelector("fieldset > div:nth-child(5) > div:nth-child(4) > input:nth-child(1)")).sendKeys(date[2]);//year
 				Thread.sleep(1000);
 			}
 		}else {
 	 		WebDriverWait wait = new WebDriverWait(driver, 15);
 	 		wait.until(ExpectedConditions.visibilityOfAllElements(travelers));
 			for (int i = 0; i < travelers.size(); i++) {
 				travelers.get(i).findElement(By.cssSelector("fieldset > div:nth-child(3) > div > input")).sendKeys(fnames[i]);//first name
 				travelers.get(i).findElement(By.cssSelector("fieldset > div:nth-child(4) > div > input")).sendKeys(lname);//last name
 				Select month = new Select(travelers.get(i).findElement(By.cssSelector("fieldset:nth-child(1) > div:nth-child(5) > div:nth-child(2) > select:nth-child(1)")));//month
 				String [] date = bdays[i].split("/");
 				month.selectByValue(date[0].replace("0", ""));
 				travelers.get(i).findElement(By.cssSelector("fieldset > div:nth-child(5) > div:nth-child(3) > input:nth-child(1)")).sendKeys(date[1]);//day
 				travelers.get(i).findElement(By.cssSelector("fieldset > div:nth-child(5) > div:nth-child(4) > input:nth-child(1)")).sendKeys(date[2]);//year
 				Thread.sleep(1000);
 			}
 		}

	}
 	
	//bdays format MM-DD-YYYY
 	public void setReNewAdultTraveler(String [] fnames, String lname, String [] bdays, String [] lastPass ) {
 		WebDriverWait wait = new WebDriverWait(driver, 15);
 		wait.until(ExpectedConditions.visibilityOfAllElements(travelers));
		for (int i = 0; i < travelers.size(); i++) {
			travelers.get(i).findElement(By.xpath("//fieldset/div[2]/div/input")).sendKeys(fnames[i]);//first name
			travelers.get(i).findElement(By.xpath("//fieldset/div[3]/div/input")).sendKeys(lname);//last name
			Select month = new Select(travelers.get(i).findElement(By.xpath("//form/div[1]/fieldset/div[4]/div[1]/select")));//month
			String [] date = bdays[i].split("/");
			month.selectByValue(date[0].replace("0", ""));
			travelers.get(i).findElement(By.xpath("//fieldset/div[4]/div[2]/input")).sendKeys(date[1]);//day
			travelers.get(i).findElement(By.xpath("//fieldset/div[4]/div[3]/input")).sendKeys(date[2]);//year
			travelers.get(i).findElement(By.xpath("//fieldset/div[5]/div/input")).sendKeys(lastPass[i]);//last season passes
		}
	}
 	
	public void clickContinue() throws InterruptedException {
		continueBnt.click();
		Thread.sleep(1500);
	}
	
}
