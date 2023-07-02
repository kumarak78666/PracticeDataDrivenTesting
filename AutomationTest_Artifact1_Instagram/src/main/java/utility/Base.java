package utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.com.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.ie.InternetExplorerDriver;

//import com.sun.tools.sjavac.Log;

import configapp.Appconfig1;
import freemarker.log.Logger;

public class Base {
	public static WebDriver driver;
	protected static Logger log = Logger.getLogger(Base.class.getName()); 
//	@BeforeMethod
/*   public static void initiateDriverInstance(String browserName) throws Exception {
	   if(Utility.fetchinvokedetails("browserName").toString().equalsIgnoreCase("chrome"))
	   {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", "D:\\maven automation programs\\AutomationTest_Artifact1_Instagram\\Drivers\\chromedriver.exe");   
	    driver=new ChromeDriver();
	    
	   }
	   else if(Utility.fetchinvokedetails("browserName").toString().equalsIgnoreCase("firefox"))
	   {
		   driver=new FirefoxDriver();
	   }
	   
	// driver.get(Utility.fetchinvokedetails("appURL").toString());  
	   Thread.sleep(4000); 
	   driver.quit(); 
	  
   } */
	public static void launchBrowser(String browser) {		
		 try{	
			if (browser.equalsIgnoreCase("IE")) {
				log.info("IE Browser is launching...");
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/src/drivers/IEDriverServer.exe");
				// create IE instance
				driver = new InternetExplorerDriver();
				log.info("IE Browser is launched.");
	//			implicitlywait();		
				
			} else if (browser.equalsIgnoreCase("Chrome")) {
				log.info("Chrome Browser is launching...");
			 	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
//				System.setProperty("webdriver.chrome.driver", "D:\\maven automation programs\\AutomationTest_Artifact1_Instagram\\Drivers\\chromedriver.exe");   

               driver = new ChromeDriver();
			}
		 }
	 catch(Exception e){
		 System.out.println("Failed to launch Browser.");
	 }}

   /*	@AfterMethod
   public void closeDriverInstance() {
	   driver.quit();
   } */
	
	public static void sendValue(String locator, String testdata) {
		try {
			if(!isElementVisible(locator)) {
				return;
			}
			driver.findElement(By.xpath(locator)).clear();
			driver.findElement(By.xpath(locator)).sendKeys(testdata);

		}
		catch(Exception e){
			log.info("unable to perform action");
		}
	}
	
	public static void click(String locator) {
		try {
			if(!isElementVisible(locator)) {
				return;
			}
			driver.findElement(By.xpath(locator)).click();
			log.info("click operation done");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			log.info("Click operation failed.");
		}
	}
	
	
	private static boolean isElementVisible(String locator) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Method to launch application URL
		public static void sendURL(String URL) {
	//		Log.info("application url is being opened...");
			driver.navigate().to(URL);
	//		Log.info("application url is opened.");
		}

	/*	public static void implicitlywait() {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
*/
	
	
}
