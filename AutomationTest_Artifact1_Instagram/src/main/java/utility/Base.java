package utility;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.com.utility.Utility;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.constant.*;
import org.apache.logging.log4j.core.util.FileUtils;
import org.com.utility.Excelreader;  
import org.com.properties.*;

//import com.sun.tools.sjavac.Log;  

import configapp.Appconfig1;     
import freemarker.log.Logger;           

public class Base {
	public static WebDriver driver;
	public static WebDriverWait wait; 

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
	 }
		 }               

   /*	@AfterMethod
   public void closeDriverInstance() {
	   driver.quit();
   } */
	
	
	
	
	

	

	/*	public static void implicitlywait() {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
*/
		public static void implicitlywait() {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
		
		
		
		//Method to maximize the browser window
		public static void windowMaximize() {
			log.info("Window is maximizing...");
			driver.manage().window().maximize();
			log.info("Window maximized.");		
		}

		//Method to launch application URL
		public static void sendURL(String url) {
			log.info("application url is being opened...");
			driver.navigate().to(url);
			log.info("application url is opened.");
		}
		
		
		//Method to close the browser
		public static void quitBrowser(){
			if(!Thread.currentThread().getStackTrace()[2].getMethodName().equalsIgnoreCase("afterSuite")) {
				return;
			}
		    try 
		    {
		        driver.close();
		        Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");

		    }
		    catch (Exception anException) 
		    {
		        anException.printStackTrace();
		    }
		}
		/*public static void quitBrowser(){
			
			driver.quit();
			log.info("quit the browser.");
		}*/
		
		//Method to perform click operation
		public static void click(String locator){
			try {
				if(!isElementVisible(locator)) {
				    return;
				}
				driver.findElement(By.xpath(locator)).click();
				log.info("Click operation done.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				log.info("Click operation failed.");
			}
		}
		
		//Method to perform Double click operation
		public static void doubleClick(String locator){
			try{
				if(!isElementVisible(locator)) {
				    return;
				}
				Actions action = new Actions(driver);
				WebElement element=driver.findElement(By.xpath(locator));
				action.doubleClick(element).build().perform();
			}catch(Exception e){
				log.info("Unable to perform doubleclick operation");
			}
		}
		
		//Method to perform sending value to a Textbox
		public static void sendValue(String locator, String testdata) {

			try {	
				if(!isElementVisible(locator)) {
				    return;
				}

				driver.findElement(By.xpath(locator)).clear();
				log.info("Cleared textbox data");
				driver.findElement(By.xpath(locator)).sendKeys(testdata);
				log.info("Data sent to textbox");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				log.info("Failed to send the data to textbox");
			}
		}

		//Method to perform mouse over operation
		public static void mouseOver(String locator) {
			try {
				if(!isElementVisible(locator)) {
				    return;
				}
				WebElement mouseOver = driver.findElement(By.xpath(locator));
				Actions actions = new Actions(driver);
				actions.moveToElement(mouseOver).build().perform();
				log.info("Mouse over is performed");
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				log.info("Failed to perform mouseover");
			}
		}
		
		//Method to checked or not
		public static boolean isChecked(String locator) throws Throwable {
			try {
				if (driver.findElement(By.xpath(locator)).isSelected()) {
					log.info("Checked...");
				}

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			return false;
		}
		
		//Method to check enabled or not
		public static void isEnabled(String locator) throws Throwable {

			try {
				if (driver.findElement(By.xpath(locator)).isEnabled()) {
					log.info("Enabled...");
				}

			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		//Method to get the title of the page
		public static String getTitle() throws Throwable {

			String text = driver.getTitle();
			log.info("Title of the page is:" + text);
			return text;
		}
		
		//Method to verify the title of the page
		public static boolean verifyTitle(String title) throws Throwable {

			if (driver.getTitle().equals(title)) {
				log.info("Page title is verified with " + title);			
				System.out.println("Page title is verified with " + title);
				return true;
			} else {
				log.info("Page of the title not matched with " + title);
				System.out.println("Page title is not matched with " + title);
				return false;     
			} 
		}
		
		//Method to capture the screenshot  
		public static String screenShot(String fileName) {  
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			log.info("Screenshot captured.");
			try { 
				String timeStamp = new SimpleDateFormat("MM-dd-yyyy HH.mm.ss").format(new Date());
				fileName=fileName+"_"+timeStamp; 
				System.out.println(fileName);     
				// Now copy the file to a location 
				FileUtils.copyFile(scrFile, new File(Constants.screenShotPath + fileName+".png"));
				System.out.println(Constants.screenShotPath + fileName +".png");
				return Constants.screenShotPath + fileName +".png";
			} catch (IOException e) {
				e.printStackTrace();
				log.info("Failed to capture Screenshot.");
			}
			return "Screen shot path not found";
		}
		
		//Method to switch to the frame by index
		public static void switchToFrameByIndex(int index) throws Throwable {
			try {
				
				driver.switchTo().frame(index);
				log.info("Switched to frame with index: " + index);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Failed to switch to frame with index: " + index);
			}
		}
		
		//Method to switch to the frame by id
		public static void switchToFrameById(String idValue) throws Throwable {
			try {
				driver.switchTo().frame(idValue);
				log.info("Switched to the frame with id: " + idValue);

			} catch (Exception e) {
				e.printStackTrace();
				log.info("Failed to switch to the frame with id: " + idValue);
			}
		}
		
		//Method to drag a webelement
		public static void draggable(String source, int x, int y) throws Throwable {
			
			try {

				WebElement dragitem = (driver.findElement(By.xpath(source)));

				new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();
				Thread.sleep(3000);
				log.info("Webelement " + source + "is " + "dragged from " + x + "to " + y);
				
			} catch (NoSuchElementException e) {
				log.info("Unable to drag the webelement.");
			}
		}
		
		
		//Method to drag and drop a webelement
		public static void dragAndDrop(String source, String target) throws Throwable {
			
			try {
				WebElement from = driver.findElement(By.xpath(source));
				WebElement to = driver.findElement(By.xpath(target));
				Actions actions = new Actions (driver);
				actions.dragAndDrop(from,to);
				Thread.sleep(800);
				actions.build().perform();
				log.info("Webelement is dragged and dropped.");
			} catch (Exception e) {
				log.info("Unable to perform drag and drop operation.");
			}
		}
		
		public static void keyPress(int keyNum)  {
			try {
				Robot robot = new Robot();
				robot.keyPress(keyNum);
				robot.keyRelease(keyNum);
			} catch (Exception e) {
				log.info("Unable to perform keyPress operation.");
			}
		}
		
		
		public static void keyPressOnly(int keyNum)  {
			try {
				Robot robot = new Robot();
				robot.keyPress(keyNum);
			} catch (Exception e) {
				log.info("Unable to perform keyPress operation.");
			}
		}
		
		public static void keyReleaseOnly(int keyNum)  {
			try {
				Robot robot = new Robot();
				robot.keyRelease(keyNum);
			} catch (Exception e) {
				log.info("Unable to perform keyPress operation.");
			}
		}
		
		
		//Method to perform right click operation
		public static void rightClick(String source) {
			
			try {
				WebElement elementToRightClick = driver.findElement(By.xpath(source));
				Actions clicker = new Actions(driver);
				clicker.contextClick(elementToRightClick).perform();
				log.info("Right click operation performed");
			} catch (Exception e) {
				log.info("Unable to perform right click operation");
			} 
		}
		
		//Method to delete all cookies
		public static void deleteAllCookies() {
			try{
				driver.manage().deleteAllCookies();
				log.info("All Cookies deleted.");
			}catch(Exception e){
				log.info("Unable to delete cookies.");
			}
		}
		
		/*//Method to send date
		public static void sendDate(String str) {
			 
			  //To locate table.
			  WebElement mytable = driver.findElement(By.xpath(str));
			  //To locate rows of table.
			  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
			  //To calculate no of rows In table.
			  int rows_count = rows_table.size();
			  
			  //Loop will execute till the last row of table.
			  for (int row=0; row<rows_count; row++){
			   //To locate columns(cells) of that specific row.
			   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			   //To calculate no of columns(cells) In that specific row.
			   int columns_count = Columns_row.size();
			   //System.out.println("Number of cells In Row "+row+" are "+columns_count);
			   
			   //Loop will execute till the last cell of that specific row.
			   for (int column=0; column<columns_count; column++){
			    //To retrieve text from that specific cell.
				   Columns_row.get(column).getText(); 
			    //String celtext = Columns_row.get(column).getText();
			    //System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
			   }
			   //System.out.println("--------------------------------------------------");
			  }  
		}
	*/	
		
		public static void sendDate(String locator,String date){
			try{
				if(!isElementVisible(locator)) {
				    return;
				}
				JavascriptExecutor js = (JavascriptExecutor)driver;
	       		js.executeScript("document.getElementById('"+locator+"').value='"+date+"'");
	       		log.info("Sending date performed");
			}catch(Exception e){
				log.info("Unable to perform sending date");
				e.printStackTrace();
			}
		}
		
		public static void sendDateForReadOnly(String locator,String date){
			try{
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("document.getElementById('"+locator+"').setAttribute('readonly', false)");
	       		js.executeScript("document.getElementById('"+locator+"').value='"+date+"'");
	       		log.info("Sending date performed");
			}catch(Exception e){
				log.info("Unable to perform sending date");
				e.printStackTrace();
			}
		}
		
		public static void sendDateBySendKeys(String locatorId, String xpath, String date) {
	        try {
	            ((JavascriptExecutor) driver)
	                    .executeScript("document.getElementById('" + locatorId + "').removeAttribute('readonly',0);");
	            // Enables the from date box
	            System.out.println(driver.findElement(By.xpath(xpath)));
	            WebElement dateField = driver.findElement(By.xpath(xpath));
	            dateField.clear();
	            dateField.sendKeys(date); // Enter date in required format
	        } catch (Exception e) {
	            log.info("Unable to perform sending date");
	            e.printStackTrace();
	        }
	    }
		
		//Method switch to window
		public static void switchToWindow(){
			try {
				String mainWindow=driver.getWindowHandle();
				Set<String> windows=driver.getWindowHandles();
				java.util.Iterator<String> iterator=windows.iterator();
				while (iterator.hasNext()) {
					String currentWindow=(String) iterator.next();
					if (!mainWindow.equalsIgnoreCase(currentWindow)) {
						driver.switchTo().window(currentWindow);
						log.info("Switched to window " + currentWindow);
					}
				}
				
			} catch (Exception e) {
				log.info("Unable to switch to window.");
			}
		}
		
		//Method to switch to alert and accept
		public static void switchToAlertAndAccept(){
			try{
		           Alert alert = driver.switchTo().alert();
		           log.info("Switched to alert.");
		           Thread.sleep(2000);
		           log.info("Alert window name"+driver.getTitle());
		           alert.accept();
		           log.info("Alert accepted");
		    }catch(Exception e){
		    	log.info("Unable to switch to the alert and perform accept.");
		    }
		}
		
		
		//Method to switch to alert and dismiss
		public static void switchToAlertAndDismiss(){
			try{
				Alert alert=driver.switchTo().alert();
				log.info("Switched to alert.");
				alert.dismiss();
				log.info("Alert dismissed.");
			}catch(Exception e){
				log.info("Unable to switch to alert and perform dismiss.");
			}
		}
		
		//to check alert present or not
		public static boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		}
		
		//Method to perform refresh of the page
		public static void refresh(){
			try{
				driver.navigate().refresh();
				log.info("Performed refresh.");
			}catch(Exception e){
				log.info("Unable to refresh.");
			}
		}
		
		//Method to perform click operation with linktext
		public static void clickByLinkText(String locator){
			try{
				
				driver.findElement(By.linkText(locator)).click();
				log.info("Click operation performed by linktext");
			}catch(Exception e){
				log.info("Unable to perform click operation by linktext");
			}
		}
		
		
		//Method to perform click operation with partial linktext
		public static void clickByPartialLinkText(String locator){
			try{
				
				driver.findElement(By.partialLinkText(locator)).click();
				log.info("Click operation performed by partial linktext");
			}catch(Exception e){
				log.info("Unable to perform click operation by partial linktext");
			}
		}
		
		//Method to select value by index
		public static void selectByIndex(String locator,int index){
			try{
				
				Select select=new Select(driver.findElement(By.xpath(locator)));
				select.selectByIndex(index);
				log.info("Selected by index");
			}catch(Exception e){
				log.info("Unable to select by index");
			}
			
		}
		
		//Method to select value by value
			public static void selectByValue(String locator,String value){
				try{
					if(!isElementVisible(locator)) {
					    return;
					}
					Select select=new Select(driver.findElement(By.xpath(locator)));
					select.selectByValue(value);
					log.info("Performed selection by value");
				}catch(Exception e){
					log.info("Unable to select by value");
				}
				
			}
			
		//Method to select value by visible text
		public static void selectByVisibleText(String locator,String text){
			try{
				if(!isElementVisible(locator)) {
				    return;
				}
				Select select=new Select(driver.findElement(By.xpath(locator)));
				select.selectByVisibleText(text);
				log.info("performed selection by visible text");
			}catch(Exception e){
				log.info("Unable to select by visible text");
			}
			
		}
		
		public static void selectByVisibleTextdata(String locator,String testdata){
			try{
				if(!isElementVisible(locator)) {
				    return;
				}
				Select select=new Select(driver.findElement(By.xpath(locator)));
				select.selectByVisibleText(testdata);
				log.info("performed selection by visible text");
			}catch(Exception e){
				log.info("Unable to select by visible text");
			}
			
		}
		
		
		
		//Method to deselect value by visible text
		public static void deselectByVisibleText(String locator,String text){
			try{
				if(!isElementVisible(locator)) {
				    return;
				}
				Select select=new Select(driver.findElement(By.xpath(locator)));
				select.deselectByVisibleText(text);
				log.info("performed deselection by visible text");
			}catch(Exception e){
				log.info("Unable to deselect by visible text");
			}
			
		}
		
		//Returns the webelement
		public static WebElement getElementByXpath(String locator){
			
			try{
				return driver.findElement(By.xpath(locator));
			}catch(Exception e){
				log.info("Unable to identify webelement by xpath");
				return null;
			}
		}
		
		//Returns the webelement
		public static WebElement getElementById(String locator){
			try{
				return driver.findElement(By.id(locator));
			}catch(Exception e){
				log.info("Unable to identify webelement by id");
				return null;			
			}
		}
		
		
		
		
		public static void waitForElementToBeClickable(String locator){
			try{
				WebElement webElement=driver.findElement(By.xpath(locator));
			//	wait=new WebDriverWait(driver, 40);
				wait.until(ExpectedConditions.elementToBeClickable(webElement));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		/*public static void waitForPageToLoad(){	  
			(new WebDriverWait(driver, 40)).until(new ExpectedCondition<Boolean>() {
			      public Boolean apply(WebDriver d) {
			        return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
			      }
			    });
		}
		*/
		
		public static void waitForVisibilityOfWebElement(String locator){
			try{
				WebElement webElement=driver.findElement(By.xpath(locator));
			//	wait=new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(webElement));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		public static  boolean waitForInVisibilityOfWebElement(String locator){
			try{
				WebElement webElement=driver.findElement(By.xpath(locator));
		//		wait=new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.invisibilityOfElementLocated((By) webElement));
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		
		
		
		
		
		
		public static List<WebElement> getAllWebElements(String locator){
			try{
				
				return driver.findElements(By.tagName(locator));
			}catch(Exception e){
				return null;
			}
		}
		
		public static String getTextFromWebElement(String locator){
			try{
				return driver.findElement(By.xpath(locator)).getText();
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		public static void sendTime(String locator,String time){
			try{
				JavascriptExecutor js = (JavascriptExecutor)driver;
	       		js.executeScript("document.getElementById('"+locator+"').value='"+time+"'");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public static void sendFilePath(String locator,String path){
			try{
				JavascriptExecutor js = (JavascriptExecutor)driver;
	       		js.executeScript("document.getElementById('"+locator+"').value='"+path+"'");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//method to scroll down
		public static void scrollDown(int from,int to){
			try{
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollBy('"+from+"','"+to+"')", "");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			
		
		//method to get the selected value in select box
		public static String getSelectedValue(String locator){
			try{
				Select select = new Select(driver.findElement(By.xpath(locator)));
				String selectedValue = select.getFirstSelectedOption().getText();
		        return selectedValue;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		public static String getTextBoxValue(String locator){
			try{
				WebElement webElement = driver.findElement(By.xpath(locator));
				return webElement.getAttribute("value");
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		public static  boolean  isElementVisible(String locator) {
		    try {
		        if (driver.findElement(By.xpath(locator)).isDisplayed()) {
		            log.info("Element is Displayed");
		            return true;
		        }
		    }
		    catch(Exception e) {       
		        log.info("Element is Not Displayed");
		        return false;
		    }       
		    return false;
		}
		
		public static boolean isWindowExists(String URL, WebDriver driver) {
			boolean stepstatus = false;
			try {
				Set<String> allhandles = driver.getWindowHandles();
				for (String handle : allhandles) {
					driver.switchTo().window(handle);
					String CurrentURL = driver.getCurrentUrl();
					if (CurrentURL.contains(URL)) {
						stepstatus = true;
						break;
					}
				}
			} catch (Exception e) {
				stepstatus = false;
			}
			return stepstatus;
		}

		public static WebDriver SwitchtoWindow(String URL, WebDriver driver) {
			try {
				Set<String> allhandles = driver.getWindowHandles();
				for (String handle : allhandles) {
					driver.switchTo().window(handle);
					String CurrentURL = driver.getCurrentUrl();
					if (CurrentURL.contains(URL)) {
						break;
					}
				}
			} catch (Exception e) {

			}
			return driver;
		}

	
}
