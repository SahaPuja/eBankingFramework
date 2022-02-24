package TestCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Utilities.ReadConfig;


public class BaseClass {
	ReadConfig objRC = new ReadConfig();
	public String baseURL = objRC.getApplicationURL();
	public String UserName = objRC.getUsername();
	public String Password = objRC.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
@Parameters("browser")
@BeforeClass
public void setUp(String br) {
	
	logger = Logger.getLogger("eBanking");
	PropertyConfigurator.configure("Log4j.properties");
	

	if(br.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver",objRC.getChromePath());
		driver=new ChromeDriver();
	}
	else if(br.equals("firefox"))
	{
		System.setProperty("webdriver.gecko.driver",objRC.getFirefoxPath());
		driver = new FirefoxDriver();
	}
	driver.get(baseURL);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
}

@AfterClass
public void tearDown() {
	driver.quit(); 
}

public void captureScreen(WebDriver driver, String tname) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
	FileUtils.copyFile(source, target);
	System.out.println("Screenshot taken");
}

public String randomestring()
{
	String generatedstring=RandomStringUtils.randomAlphabetic(8);
	return(generatedstring);
}

public static String randomeNum() {
	String generatedString2 = RandomStringUtils.randomNumeric(4);
	return (generatedString2);
}
}
