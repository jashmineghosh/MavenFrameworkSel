package com.autoFramework.myCompany.myProjectFramework.testScripts.registration;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.autoFramework.myCompany.myProjectFramework.helper.assertion.AssertionHelper;
import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.config.ObjectReader;
import com.autoFramework.myCompany.myProjectFramework.helper.logger.LoggerHelper;
import com.autoFramework.myCompany.myProjectFramework.pageObject.LoginPage;
import com.autoFramework.myCompany.myProjectFramework.pageObject.MyAccountPage;
import com.autoFramework.myCompany.myProjectFramework.pageObject.RegistrationPage;
import com.autoFramework.myCompany.myProjectFramework.testBase.TestBase;

public class RegistrationTest extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(RegistrationTest.class);
	LoginPage loginPage;
	RegistrationPage register;
	MyAccountPage myAccountPage;
	
	@Test
	public void testRegistration(){
		// go to application
		driver.get(ObjectReader.reader.getURL());
//		getApplicationUrl(ObjectReader.reader.getURL());
		
		loginPage = new LoginPage(driver);
		loginPage.clickOnSignInLink();
		loginPage.enterRegistrationEmail();
		
		register = loginPage.clickOnCreateAnAccount();
		
		// enter registration data
		register.setMrRadioButton();
		register.setFirstName("firstName");
		register.setLastname("lastname");
		register.setPassword("password");
		register.setAddress("address");
		register.setDay("5");
		register.setMonth("June");
		register.setYear("2017");
		register.setYourAddressFirstName("yourAddressFirstName");
		register.setYourAddressLastName("yourAddressLastName");
		register.setYourAddressCompany("yourAddressCompany");
		register.setYourAddressCity("yourAddressCity");
		register.setYourAddressState("Alaska");
		register.setYourAddressPostCode("99501");
		register.setMobilePhone("9999999999");
		register.setAddressAlias("addressAlias");
		register.clickRegistration();
		
		myAccountPage = new MyAccountPage(driver);
		boolean status = myAccountPage.isYourAccountPageMessage();
		
		AssertionHelper.updateTestStatus(status);
	}
}
