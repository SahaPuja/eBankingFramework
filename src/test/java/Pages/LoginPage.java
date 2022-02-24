package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver) {
		
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy (name = "uid")
	WebElement UserId;
	
	@FindBy (name = "password")
	WebElement Pwd;
	
	@FindBy (name = "btnLogin")
	WebElement LoginBtn;
	
	@FindBy (xpath = "//a[contains(text(),'Log out')]")
	WebElement LogoutLink;
	
	public void setUserName(String userName)
	{
		UserId.sendKeys(userName);
	}
	public void setUserPassword(String Password)
	{
		Pwd.sendKeys(Password);
	}
	public void clickOnLogin()
	{
		LoginBtn.click();
	}
	public void clickOnLogout()
	{
		LogoutLink.click();
	}
}
