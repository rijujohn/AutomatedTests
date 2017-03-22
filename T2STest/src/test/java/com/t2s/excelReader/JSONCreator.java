package com.t2s.excelReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

import com.t2s.excelReader.ODSReader;

public class JSONCreator {
//	///home//dev//Documents//MyOwn//Json.ods
//	"//home//dev//Documents//MyOwn//Test1.json"
	public void createJSON(String inputExcel,String inputSheetName,int inputRow,String outputJSON) throws IOException
	
	{
	File file = new File(inputExcel);
	LinkedHashMap<String, String> mapDataJSON = new LinkedHashMap<String, String>();
	String regexDecimal = "(\\+|-)?([0-9]+(\\.[0-9]+))";
	String regexInt = "(\\+|-)?[0-9]+";	
	int intCounter = 0;

	ODSReader objODSReader = new ODSReader();
    StringBuilder stJSONString = new StringBuilder();
    stJSONString.append("{");
	mapDataJSON = objODSReader.readODSParse(file,inputSheetName,inputRow);
	Set<String> keys = mapDataJSON.keySet();
	
    for(String keyItem:keys)
    {
     intCounter++;	
     String stMainKey[] = keyItem.split("__");
     String stKeyValue = mapDataJSON.get(keyItem);
     if(stKeyValue.matches(regexDecimal) || stKeyValue.matches(regexInt))
     	{
    	 stJSONString.append("\""+stMainKey[1]+"\""+": "+stKeyValue.trim());
     	}
     else
     	{
    	 stJSONString.append("\""+stMainKey[1]+"\""+": "+"\""+stKeyValue.trim()+"\"");
     	}
     if (intCounter != keys.size())
     {
    	 stJSONString.append(",");
     }
     
     
     
     stJSONString.append(System.getProperty("line.separator"));
    }
    stJSONString.append("}");
    try 
	    {
	    	FileOutputStream oStream = new FileOutputStream(new File(outputJSON));
	    	oStream.write(stJSONString.toString().getBytes());
	    	oStream.close();
	    	
	    } 
    catch (IOException e) 
	    {
	    	System.out.println("Error -" + e.getMessage());
	    }
	       
    }
}



