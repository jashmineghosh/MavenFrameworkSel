package com.autoFramework.myCompany.myProjectFramework.helper.listener;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.autoFramework.myCompany.myProjectFramework.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentListener implements ITestListener {

	public static ExtentReports extent; 
	public static ExtentTest test; 
	
	public void onTestStart(ITestResult result) {

		
		test.log(Status.INFO, result.getName()+"....this test started...");
		Reporter.log(result.getName() + "this test started in emailable-report");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, result.getName()+" is pass");
		Reporter.log(result.getMethod().getMethodName()+" Test Passed..");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
			
		test.log(Status.FAIL, result.getThrowable());
			Reporter.log(result.getMethod().getMethodName()+" Test Failed.."+result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.SKIP,  result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test Skipped.."+result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extent = ExtentManager.getInstance();
//		test = extent.createTest(getClass().getName()); 
		test = extent.createTest(context.getName());
		Reporter.log(context.getName() + "test started");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
