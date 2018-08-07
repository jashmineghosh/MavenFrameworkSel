package com.autoFramework.myCompany.myProjectFramework.helper.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer{
// why we need retry == null validation, check if this can be skipped, another way is to have annotation like @Test(retryAnalyzer = com.uiFramework.myCompany.myProjectFramework.helper.listener.Retry.class)
	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
		IRetryAnalyzer retry = arg0.getRetryAnalyzer();
		if(retry == null){
			arg0.setRetryAnalyzer(Retry.class);
		}
	}

	
}
