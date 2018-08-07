

package com.autoFramework.myCompany.myProjectFramework.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.autoFramework.myCompany.myProjectFramework.helper.resource.ResourceHelper;

public class LoggerHelper {

	

		private static boolean root=false;
//		Log4j has three main components, which are the following:

//Logger  - Logger is a class in the org.apache.log4j.* package. We have to initialize one Logger object for each Java class. 
//		We use Logger’s methods to generate log statements. Log4j provides the factory method to get Logger objects,syntax to get Logger ->  static Logger logger = Logger.getLogger(CurrentClass.class.getName()).
//Appender
//Layout
//		
		public static Logger getLogger(Class cls){
			if(root){
				return Logger.getLogger(cls);
			}
//			PropertyConfigurator Allows the configuration of log4j from an external file,use configure method and pass properties file as argument
			PropertyConfigurator.configure(ResourceHelper.getResourcePath("src/main/resources/configfile/log4j.properties"));
			root = true;
			return Logger.getLogger(cls);
		}
		
		public static void main(String[] args) {
			Logger log = LoggerHelper.getLogger(LoggerHelper.class);
			log.info("logger is configured");
			log.info("logger is configured");
			log.info("logger is configured");
			
		}
	}


