
package com.t2s.extent;
import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
        
	public class ExtentManager {
		
		private static ExtentReports extent;
		
		//static String reportPath = "C:\\Users\\dell\\workspace\\Extent_Reports\\Report.html";
		public static String reportPath = System.getProperty("user.dir") + "//src//test//java//com//t2s//screenshot//Report.html";
		
		public static ExtentReports  getInstances(){
			
			if (extent == null){
				
				extent = new ExtentReports(reportPath, true,DisplayOrder.OLDEST_FIRST);
				extent.loadConfig(new File(System.getProperty("user.dir")+"//src//test//java//com//t2s//extent//extent-config.xml"));
				
				extent.addSystemInfo("Selenium QA Version","3.0.1").addSystemInfo("Environment","QA");
			} 
			return extent;
			
			
		}}
	

