package utility;

import java.io.IOException;
import java.util.Properties;

//import org.apache.poi.hpsf.Property;  
import org.apache.poi.openxml4j.exceptions.InvalidFormatException; 
import org.com.utility.Excelreader;                      
import org.com.utility.Utility;                   
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement; 
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;      
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Log;
//import bsh.org.objectweb.asm.Constants;
import org.constant.*;
import org.com.utility.*;
import configapp.Appconfig1;
import org.com.properties.*;
public class BaseUtils extends Base {  
	static Excelreader read;
//	static Property prop = null;
	static Property props = null;
//	static Property userprops = null;
	public static ExtentReports extent;
	public static ExtentTest expense;

	public static void  testSetup() throws InvalidFormatException{
		try {
			read=new Excelreader(Constants.testDataPath);	
	//		prop = new Property(Constants.userProperties);
			props = new Property(Constants.userProperties,Property.USER_MODULE);
		//	userprops = new Property(Constants.userProperties,Property.USER_MODULE);

	//		extent=new ExtentReports(Constants.reportPath, ApplicationConfig.appendReports); //set the report path and whether to append the report to previous report or not
		//	extent.loadConfig(new File(Constants.reportConfigPath)); //Configuration of extent reports			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
@Test
public static void login() throws InterruptedException{
	
	
	try{
		/*if(!Thread.currentThread().getStackTrace()[2].getMethodName().equalsIgnoreCase("afterSuite")) {
			return;   
		}*/
		launchBrowser(Appconfig1.browserName);
 		log.info("chrome Browser is launched.");                         
	//	windowMaximize();
		sendURL(Appconfig1.appURL);      
		log.info("link opened");   
		Excelreader.setTestCaseNameRowNo(8);          
		
	//	expense=extent.startTest(testName + " with "+Excelreader.getCellData("Login", "UserName")); 
        Thread.sleep(4000);                               
        driver.findElement(By.name("username"));        
        log.info("clicked on element");
        sendValue(Property.getProperty("userName"), "test@ibt.com");
        log.info("clicked on propertyelement");  
	//	sendValue(Property.getProperty("userName"),Excelreader.getCellData("Login","UserName"));
		log.info("clicked on dwa"); 
	//	driver.findElement(By.name("//input[@name='username']"));
                                    
	//	enterDetailsInField(Property.getProperty("username"));
	//	enterDetailsInField(driver.findElement(By.name("//*[@name='username'])")));
//		enterDetailsInField(Property.getProperty(""));
	} catch (Exception e) { 
		e.printStackTrace();
	}
}

}


