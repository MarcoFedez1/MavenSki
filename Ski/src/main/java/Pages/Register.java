package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Register {
	WebDriver driver;
	public Register (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	//Register Page -------------------------------------------------------------------------------------------
	
	@FindBy(css = "div.form-group:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
	private WebElement fname;
	
	@FindBy(css = "div.form-group:nth-child(3) > div:nth-child(2) > input:nth-child(1)")
	private WebElement lname;
	
	@FindBy(css = "div.form-group:nth-child(4) > div:nth-child(2) > input:nth-child(1)")
	private WebElement email;
	
	@FindBy(css = "div.form-group:nth-child(5) > div:nth-child(2) > input:nth-child(1)")
	private WebElement pass;
	
	@FindBy(css = ".ng-valid-match")
	public WebElement cpass;
	
	@FindBy(css = "div.form-group:nth-child(7) > div:nth-child(2) > input")
	public WebElement phone;
	
	@FindBy(css = "button.btn:nth-child(2)")
	public WebElement RegBnt;
	
	
	//Register Page -------------------------------------------------------------------------------------------
    //Registrar
    public void CompletRegister(String fname, String lname, String email, String pass, String cpass, String phone)
    {
    	this.fname.clear();
    	this.lname.clear();
    	this.email.clear();
    	this.pass.clear();
    	this.cpass.clear();
    	this.phone.clear();
    	this.fname.sendKeys(fname);
    	this.lname.sendKeys(lname);
    	this.email.sendKeys(email);
    	this.pass.sendKeys(pass);
    	this.cpass.sendKeys(cpass);
    	this.phone.sendKeys(phone);
    	RegBnt.click();
    }
    
    public void clearFname() {
    	fname.clear();
   
    }
    public void clearlname() {
    	lname.clear();
   
    }
    public void clearEmail() {
    	email.clear();
    }
    
    public void setFname(String Fname) {
    	fname.sendKeys(Fname);
   
    }
    public void setlname(String Lname) {
    	lname.sendKeys(Lname);
   
    }
    public void setEmail(String Email) {
    	email.sendKeys(Email);
    }
}
