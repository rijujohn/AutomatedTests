package com.uktech.test.frontEnd_Dynamic;

import com.uktech.helpers.excelReader.JSONCreator;
import com.uktech.helpers.excelReader.ODSReader;
import com.uktech.test.prepare.PrepareEnvForTests;

import java.io.File;




//import org.json.simple.JSONObject;
import org.testng.annotations.Test;

//import com.t2s.excelReader.JSONCreator;
//import com.t2s.excelReader.ODSReader;
//import com.t2s.prepare.PrepareEnvForTests;

public class zDATACreationCreateJSON {
	
   
	String inputExcel = System.getProperty("user.dir") + "/src/test/resources/data/inputSheet/Json_Input.ods";
	String mainDataExcel = System.getProperty("user.dir") + "/src/test/resources/data/inputSheet/Input.ods";
	String outputJSONPath = System.getProperty("user.dir") + "/src/test/resources/data/json/";

	File inputFile = new File(inputExcel);
	File mainDataFile = new File(mainDataExcel);
	String inputSheetName = "Input";

    JSONCreator objJSON = new JSONCreator();
	@Test(priority = 1)
	public void createJSONFile() throws Exception
	{   
		ODSReader objODSReader = new ODSReader();
		objODSReader.copySheetValue(inputFile, mainDataFile, "Input");
		int inputRow = objODSReader.getRowCount(inputFile, inputSheetName);
		//Json_Input.ods"
		for(int i = 1;i<=inputRow;i++)
		{	
		String outputJSON = outputJSONPath+"Test_"+i+".json";
		objJSON.createJSON(inputExcel ,inputSheetName,i, outputJSON);
		}
    }
}		
		
 	       

