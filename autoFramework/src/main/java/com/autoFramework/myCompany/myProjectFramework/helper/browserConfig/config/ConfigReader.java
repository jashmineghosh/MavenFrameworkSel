package com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.config;

import com.autoFramework.myCompany.myProjectFramework.helper.browserConfig.BrowserType;

public interface ConfigReader {

	public int getImpliciteWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String  getURL();
	public String  getUserName();
	public String  getPassword();
}
