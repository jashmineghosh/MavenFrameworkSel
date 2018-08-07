package com.autoFramework.myCompany.myProjectFramework.testScripts.loginPage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.autoFramework.myCompany.myProjectFramework.helper.assertion.AssertionHelper;
import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.config.ObjectReader;
import com.autoFramework.myCompany.myProjectFramework.helper.logger.LoggerHelper;
import com.autoFramework.myCompany.myProjectFramework.pageObject.LoginPage;
import com.autoFramework.myCompany.myProjectFramework.testBase.TestBase;

public class LoginTest extends TestBase{
	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	
	@Test(description="Login test with valid credentials")
	public void testLoginToApplication(){
		driver.get(ObjectReader.reader.getURL());
//		getApplicationUrl(ObjectReader.reader.getURL());
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		
		boolean status = loginPage.verifySuccessLoginMsg();
		
		AssertionHelper.updateTestStatus(status);
	}
}