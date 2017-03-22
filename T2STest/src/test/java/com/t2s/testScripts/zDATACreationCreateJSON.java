package com.t2s.testScripts;

import java.io.File;




//import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.t2s.excelReader.JSONCreator;
import com.t2s.excelReader.ODSReader;
import com.t2s.prepare.PrepareEnvForTests;

public class zDATACreationCreateJSON {
	
   
	String inputExcel = System.getProperty("user.dir") + "/src/test/java/com/t2s/testData/Json_Input.ods";
	String outputJSONPath = System.getProperty("user.dir") + "/src/test/java/com/t2s/testData/";
	//String JSON_BODY_PATH = "src//main//resources//Test2.json";
	
	File inputFile = new File(inputExcel);
	String inputSheetName = "Input";

    JSONCreator objJSON = new JSONCreator();
   // String inputExcel = "/home//dev//Documents//MyOwn//Json.ods";

	@Test(priority = 1)
	public void createJSONFile() throws Exception
	{   
		ODSReader objODSReader = new ODSReader();
		int inputRow = objODSReader.getRowCount(inputFile, inputSheetName);
		//Json_Input.ods"
		for(int i = 1;i<=inputRow;i++)
		{	
		String outputJSON = outputJSONPath+"Test_"+i+".json";
		objJSON.createJSON(inputExcel ,inputSheetName,i, outputJSON);
		}
	
    }

	
	
	
}		
		
 	       

