package com.t2s.testScripts;

import java.io.File;
import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import com.t2s.excelReader.ODSReader;
import com.t2s.prepare.PrepareEnvForTests;

public class zDATACreationCreateWithAPI {

	
	
	String inputExcel = System.getProperty("user.dir") + "/src/test/java/com/t2s/testData/Json_Input.ods";
	String jsonBodyPath = System.getProperty("user.dir") + "/src/test/java/com/t2s/testData/";
	String inputSheetName = "Creation_Input";
	String token;
	int inputRow;
	
	
	@Test
	public void changeData() throws Exception
	{   
		ODSReader objODSReader = new ODSReader();
		File inputFile = new File(inputExcel);
		LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
		mapData = objODSReader.readODS(inputFile,inputSheetName);
		inputRow = objODSReader.getRowCount(inputFile, inputSheetName);
		
		PrepareEnvForTests PrepEnv = new PrepareEnvForTests();
		

		//Json_Input.ods"
		for(int i = 1;i<inputRow;i++)
		{
		    String strTemplateName = mapData.get(i+"Template");	
		    String strToken = mapData.get(i+"Token");	
			String JSONBodyPath = jsonBodyPath + strTemplateName+".json";
			PrepEnv.prepare(strToken,JSONBodyPath);
		
		}
	
    }	
}
