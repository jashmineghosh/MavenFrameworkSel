package com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.BrowserType;
import com.autoFramework.myCompany.myProjectFramework.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader{

	Properties OR;
	FileInputStream file ;
	public PropertyReader() {
	String filePath = ResourceHelper.getResourcePath("src/main/resources/configfile/config.properties");
	try {
		 file = new FileInputStream(filePath);
		 OR = new Properties();
			OR.load(file);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public int getImpliciteWait() {
		
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));
		
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}
	public String getURL() {
		return OR.getProperty("applicationUrl");
	}
	public String getUserName() {
		return OR.getProperty("userName");
	}
	public String getPassword() {
		return OR.getProperty("password");
	}

	
}
