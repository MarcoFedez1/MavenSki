package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class login {
	WebDriver driver;
	
	public login (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	
	@FindBy (css = "div.form-group:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
	private WebElement email;
	
	@FindBy (css = "div.form-group:nth-child(3) > div:nth-child(2) > input:nth-child(1)")
	private WebElement password;
	
	@FindBy (css = ".btn-primary")
	private WebElement signInBnt;
	
	@FindBy (css = "a.btn")
	private WebElement registerBnt;
	
	public void clickRegister() {
		registerBnt.click();
	}
	
    //SingInPAge ----------------------------------------------------------------------------------------------
    public void LoginIn(String email, String pass) 
    {
    	this.email.clear();
    	this.email.sendKeys(email);
    	this.password.clear();
    	this.password.sendKeys(pass);
    	signInBnt.click();
    }
}
