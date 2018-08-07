

package com.autoFramework.myCompany.myProjectFramework.testBase;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.nio.file.Files;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
//import com.google.common.io.Files;
import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.BrowserType;
import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.ChromeBrowser;
import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.FirefoxBrowser;
import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.config.ObjectReader;
import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.config.PropertyReader;
import com.autoFramework.myCompany.myProjectFramework.helper.javaScript.JavaScriptHelper;
import com.autoFramework.myCompany.myProjectFramework.helper.logger.LoggerHelper;
import com.autoFramework.myCompany.myProjectFramework.helper.resource.ResourceHelper;
import com.autoFramework.myCompany.myProjectFramework.helper.wait.WaitHelper;
import com.autoFramework.myCompany.myProjectFramework.utils.ExtentManager;

public class TestBase {

	public static ExtentReports extent; //this will generate report in the specific location
	public static ExtentTest test; //this class is used to generate logs in report
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	
	public static File reportDirectory;
	public WebDriver driver;
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();  //STep1. to generate HTML report in the path, 
//		declare either in before test or before suite. either use the above statement or use next 2 lines
//		String location = ResourceHelper.getResourcePath("src/main/resources/reports/extent.html");
//		extent = ExtentManager.createInstance(location);
	}
	
	@BeforeTest
//	public void beforeTest() throws Exception {
//		
//		test = extent.createTest(getClass().getName());  //step2. write logs in report
//		setupDriver(ObjectReader.reader.getBrowserType());
//		reportDirectory = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
//	}
	public void beforeTest() throws Exception{
		ObjectReader.reader  = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
		setupDriver(ObjectReader.reader .getBrowserType());
		test = extent.createTest(getClass().getSimpleName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method methodname) {  
		
		test.log(Status.INFO, methodname.getName()+"....test started...");
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException //step3. describes the result of test
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(Status.FAIL,  result.getName());
			test.log(Status.FAIL,  result.getThrowable());
			String imagPath = captureScreen(result.getTestName(), driver);
			test.addScreenCaptureFromPath(imagPath);
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.PASS, result.getName()+" is pass");
			String imagPath = captureScreen(result.getTestName(), driver);
			test.addScreenCaptureFromPath(imagPath);
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, result.getThrowable());
		}
		test.info("**************"+result.getName()+"Finished***************");
		
		extent.flush();
		
	}
//	@AfterTest
//	public void afterTest()
//	{
//		extent.flush();
//		
//	}
	
public WebDriver getBrowserObject(BrowserType btype) throws Exception{
		
		try{
			switch(btype){
			case Chrome:
				// get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
				
				 
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefox.getFirefoxOptions();
				 return firefox.getFirefoxDriver(options);
				 
			default:
				throw new Exception("Driver not Found: "+btype.name());
			}
		}
		catch(Exception e){
			log.info(e.getMessage());
//			throw e;  //either throw or return null as below
		}
		return null;
		
}

public void setupDriver(BrowserType btype) throws Exception
{
	 driver = getBrowserObject(btype);
	log.info("Initialize Web driver: "+driver.hashCode());
	WaitHelper wait = new WaitHelper(driver);
	wait.setImplicitWait(ObjectReader.reader .getExplicitWait(), TimeUnit.SECONDS);
	wait.pageLoadTime(ObjectReader.reader .getPageLoadTime(), TimeUnit.SECONDS);
	 driver.manage().window().maximize();
			}


public String captureScreen(String fileName, WebDriver driver){
	if(driver == null){
		log.info("driver is null..");
//		return null;
	}
	if(fileName==""){
		fileName = "blank";
	}
	Reporter.log("captureScreen method called");
	File destFile = null;
//	Date date = new Date();
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try{
		destFile = new File(reportDirectory+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
		Files.copy(screFile.toPath(), destFile.toPath());
		Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return destFile.toString();
}

public static void logExtentReport(String s1){
	TestBase.test.log(Status.INFO, s1);
}

public void getNavigationScreen(WebDriver driver) {
	log.info("capturing ui navigation screen...");
	new JavaScriptHelper(driver).zoomInBy60Percentage();
	 String screen = captureScreen("", driver);
	 new JavaScriptHelper(driver).zoomInBy100Percentage();
	 try {
		test.addScreenCaptureFromPath(screen);
	} catch (IOException e) {
		e.printStackTrace();}
	}

public void getApplicationUrl(String url){
	driver.get(url);
	logExtentReport("navigating to ..."+url);
}
}	

