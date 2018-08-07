package com.autoFramework.myCompany.myProjectFramework.testScripts;

import org.testng.annotations.Test;

import com.autoFramework.myCompany.myProjectFramework.testBase.TestBase;

public class TestScreenshot1 extends TestBase{

	@Test
	public void testScreenshot()
	{
		driver.get("http://learn-automation.com/how-to-capture-screenshot-in-selenium-webdriver/");
		captureScreen("firstScreen", driver);
		
	}
}
