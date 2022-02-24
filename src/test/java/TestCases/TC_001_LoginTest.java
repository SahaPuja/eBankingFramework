package TestCases;



import java.io.IOException;



import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.LoginPage;

public class TC_001_LoginTest extends BaseClass {
	LoginPage objLP;
	@Test
	public void loginTest() throws IOException {
		driver.get(baseURL);
		logger.info("URL is launched");
		objLP=new LoginPage(driver);
		objLP.setUserName(UserName);
		logger.info("Entered UserName");
		objLP.setUserPassword(Password);
		logger.info("Entered Password");
		objLP.clickOnLogin();
		logger.info("Clicked on Login");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login Test Failed");
			
		}
	}

}
