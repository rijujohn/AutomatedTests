package com.t2s.excelReader;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
//import java.util.Map;

///http://half-wit4u.blogspot.in/2011/05/read-openoffice-spreadsheet-ods.html
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class ODSReader {
	//private Sheet sheet; 
	//sheet = SpreadSheet.createFromFile(file).getSheet(sheetName);
	public LinkedHashMap<String,String> readODS (File file,String sheetName)
      {
        Sheet sheet;
        LinkedHashMap<String,String> dataHolder =new LinkedHashMap<String,String>();  
        try {
             //Getting the 0th sheet for manipulation| pass sheet name as string
             sheet = SpreadSheet.createFromFile(file).getSheet(sheetName);
             //Get row count and column count
             int nColCount = sheet.getColumnCount();
             int nRowCount = sheet.getRowCount();
             String intSt = "";
             String cellValue;
             String headerCellValue;
             MutableCell cell = null;
             MutableCell headerCell = null;

             for( int nColIndex = 0;nColIndex < nColCount; nColIndex++)
             {
               //Iterating through each row
               for(int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++)
               {
                 cell = sheet.getCellAt(nColIndex, nRowIndex);
                 cellValue = cell.getValue().toString().trim();
                 headerCell = sheet.getCellAt(nColIndex, 0);
                 headerCellValue = headerCell.getValue().toString().trim();
               	 intSt = Integer.toString(nRowIndex) + headerCellValue ;

                if (nRowIndex != 0)
                 {
                 dataHolder.put(intSt,cellValue);
                 System.out.println("itd" + intSt +","+ cellValue);
                 }
               }    
               System.out.println("value-" + dataHolder.get("1CustomerName"));
              // return dataHolder;
              }
           //  return dataHolder;
            } catch (IOException e) {
              e.printStackTrace();
            }
        sheet = null;
        return dataHolder;
}
      
public int getRowCount(File file,String sheetName) throws IOException
{
	Sheet sheet;
	sheet = SpreadSheet.createFromFile(file).getSheet(sheetName);
    int nRowCount = sheet.getRowCount();
    sheet = null;
    return nRowCount;
}

  //private Sheet sheet; 
	//sheet = SpreadSheet.createFromFile(file).getSheet(sheetName);
	public LinkedHashMap<String,String> readODSREverse (File file,String sheetName)
      {
        Sheet sheet;
        LinkedHashMap<String,String> dataHolder =new LinkedHashMap<String,String>();  
        try {
             //Getting the 0th sheet for manipulation| pass sheet name as string
             sheet = SpreadSheet.createFromFile(file).getSheet(sheetName);
             //Get row count and column count
             int nColCount = sheet.getColumnCount();
             int nRowCount = sheet.getRowCount();
             String intSt = "";
             String cellValue;
             String headerCellValue;
             MutableCell cell = null;
             MutableCell headerCell = null;

             for( int nColIndex = nColCount-1;nColIndex >= 0; nColIndex--)
             {
               //Iterating through each row
               for(int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++)
               {
                 cell = sheet.getCellAt(nColIndex, nRowIndex);
                 cellValue = cell.getValue().toString().trim();
                 headerCell = sheet.getCellAt(nColIndex, 0);
                 headerCellValue = headerCell.getValue().toString().trim();
               	 intSt = Integer.toString(nRowIndex) +"__"+ headerCellValue ;

                if (nRowIndex != 0)
                 {
                 dataHolder.put(intSt,cellValue);
                 System.out.println("itd" + intSt +","+ cellValue);
                 }
               }    
               System.out.println("value-" + dataHolder.get("1CustomerName"));
              // return dataHolder;
              }
           //  return dataHolder;
            } catch (Exception e) {
             // e.printStackTrace();
              System.out.println("Message"+e.getMessage());
            }
        sheet = null;
        return dataHolder;
}

	public LinkedHashMap<String,String> readODSParse (File file,String sheetName,int intRow)
    {
      Sheet sheet;
      LinkedHashMap<String,String> dataHolder =new LinkedHashMap<String,String>();  
      try {
           //Getting the 0th sheet for manipulation| pass sheet name as string
           sheet = SpreadSheet.createFromFile(file).getSheet(sheetName);
           //Get row count and column count
           int nColCount = sheet.getColumnCount();
           int nRowCount = sheet.getRowCount();
           String intSt = "";
           String cellValue;
           String headerCellValue;
           MutableCell cell = null;
           MutableCell headerCell = null;

           for( int nColIndex = 0;nColIndex < nColCount; nColIndex++)
           {
             //Iterating through each row
             for(int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++)
             {
               cell = sheet.getCellAt(nColIndex, nRowIndex);
               cellValue = cell.getValue().toString().trim();
               headerCell = sheet.getCellAt(nColIndex, 0);
               headerCellValue = headerCell.getValue().toString().trim();
             	 intSt = Integer.toString(nRowIndex) +"__"+ headerCellValue ;

              if (nRowIndex == intRow)
               {
               dataHolder.put(intSt,cellValue);
               }
             // pRowIndex = 1;
             }    

            }
          } catch (IOException e) {
            e.printStackTrace();
          }
      sheet = null;
      return dataHolder;
}


}

