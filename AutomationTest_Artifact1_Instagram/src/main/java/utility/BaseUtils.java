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
public class BaseUtils extends Base {  
	static Excelreader read;
	static Property props = null;
	public static ExtentReports extent;
	public static ExtentTest expense;

	public static void  testSetup() throws InvalidFormatException{
		try {
			read=new Excelreader(Constants.testDataPath);		
			props = new Property(Constants.userProperties);
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
		Excelreader.setTestCaseNameRowNo(1);
		
	//	expense=extent.startTest(testName + " with "+Excelreader.getCellData("Login", "UserName"));
Thread.sleep(4000);
		sendValue(Property.getProperty("userName"),Excelreader.getCellData("Login","UserName"));
        
	//	enterDetailsInField(Property.getProperty("username"));
	//	enterDetailsInField(driver.findElement(By.name("//*[@name='username'])")));
//		enterDetailsInField(Property.getProperty(""));
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}


