package TestCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Utilities.Xls_Reader;

public class TC_002_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData")
	public void LoginDDT(String user, String pwd) throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setUserPassword(pwd);
		logger.info("password provided");
		lp.clickOnLogin();
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickOnLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			
		}
	}
	
	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/TestData/LoginData.xlsx";
		
		int RowCount=Xls_Reader.getRowCount(path, "Sheet1");
		int ColumnCount =Xls_Reader.getCellCount(path, "Sheet1", 1);
		String logindata[][]=new String[RowCount][ColumnCount];
		for(int i=1; i<=RowCount; i++)
		{
			for(int j=0; j<ColumnCount; j++)
			{
				logindata[i-1][j]=Xls_Reader.getCellData(path,"Sheet1", i,j);//1 0
			}
		}
		return logindata;
	}
}
