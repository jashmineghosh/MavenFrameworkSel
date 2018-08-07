

package com.autoFramework.myCompany.myProjectFramework.testScripts;

import org.testng.annotations.Test;

import com.autoFramework.myCompany.myProjectFramework.helper.assertion.AssertionHelper;
import com.autoFramework.myCompany.myProjectFramework.testBase.TestBase;

public class Test1 extends TestBase{
	
	@Test
	public void testLogin(){
		AssertionHelper.markPass();
		
	}
	
	@Test
	public void testLogin1(){
		AssertionHelper.markFail();
	}
	
	@Test
	public void testLogin2(){
		AssertionHelper.markPass();
	}
	
	@Test
	public void testLogin3(){
		AssertionHelper.markFail();
	}
	
		
	
}

