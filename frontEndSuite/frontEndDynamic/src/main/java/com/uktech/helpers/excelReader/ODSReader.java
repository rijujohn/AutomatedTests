package com.uktech.helpers.excelReader;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
//import java.util.Map;

import org.jopendocument.dom.OOUtils;
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
              //  System.out.println("itd" + intSt +","+ cellValue);
                 }
               }    
             //  System.out.println("value-" + dataHolder.get("1CustomerName"));

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


	public void setODSValue (File file,String sheetName,String setVal,int intCol) throws IOException
    {
      Sheet sheet;
           //Getting the 0th sheet for manipulation| pass sheet name as string
           sheet = SpreadSheet.createFromFile(file).getSheet(sheetName);
           int nRowCount = sheet.getRowCount();
           for(int i=1;i<nRowCount;i++)
           {
           sheet.setValueAt(setVal, intCol,i);
           }
           sheet.getSpreadSheet().saveAs(file);
           sheet = null;
      }
	 
	public void copySheetValue(File Fromfile,File Tofile,String FromsheetName) throws IOException
	{
		Sheet fromSheet;
		Sheet toSheetContact;
		Sheet toSheetOrder;
		fromSheet = SpreadSheet.createFromFile(Fromfile).getSheet(FromsheetName);
		toSheetContact = SpreadSheet.createFromFile(Tofile).getSheet("Contact");
		toSheetOrder = SpreadSheet.createFromFile(Tofile).getSheet("Order");
        //Get row count and column count
        int nColCountFrom = fromSheet.getColumnCount();
        int nRowCountFrom = fromSheet.getRowCount();
        int nColCountTo = toSheetContact.getColumnCount();
        int nRowCountTo = toSheetContact.getRowCount();
        String intSt = "";
        String cellValue;
        String headerCellValue;
        MutableCell cell = null;
        MutableCell headerCell = null;
        for( int nRowIndex = 1;nRowIndex < nRowCountFrom; nRowIndex++)
        {
            cell = fromSheet.getCellAt(0, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetContact.setValueAt(cellValue, 5,nRowIndex);
            
            cell = fromSheet.getCellAt(1, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetContact.setValueAt(cellValue, 6,nRowIndex);
            
            cell = fromSheet.getCellAt(2, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetContact.setValueAt(cellValue, 7,nRowIndex);
            cell = fromSheet.getCellAt(3, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetContact.setValueAt(cellValue, 8,nRowIndex);
            cell = fromSheet.getCellAt(4, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetContact.setValueAt(cellValue, 9,nRowIndex);
          
            cell = fromSheet.getCellAt(5, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetContact.setValueAt(cellValue, 10,nRowIndex);
            
            cell = fromSheet.getCellAt(6, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetContact.setValueAt(cellValue, 11,nRowIndex);
            
            cell = fromSheet.getCellAt(7, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetContact.setValueAt(cellValue, 11,nRowIndex);
 ////////////Order Sheet//////////
            cell = fromSheet.getCellAt(8, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            if(cellValue.equals("1")){cellValue = "Yes";}
            if(cellValue.equals("0")){cellValue = "No";}
            toSheetOrder.setValueAt(cellValue, 4,nRowIndex);
            
            cell = fromSheet.getCellAt(9, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            if(cellValue.equals("1")){cellValue = "Yes";}
            if(cellValue.equals("0")){cellValue = "No";}
            toSheetOrder.setValueAt(cellValue, 5,nRowIndex);
            
            cell = fromSheet.getCellAt(10, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetOrder.setValueAt(cellValue, 6,nRowIndex);
            
            cell = fromSheet.getCellAt(11, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            toSheetOrder.setValueAt(cellValue, 7,nRowIndex);
            
            cell = fromSheet.getCellAt(12, nRowIndex);
            cellValue = cell.getValue().toString().trim();
            if(cellValue.equals("1")){cellValue = "Yes";}
            if(cellValue.equals("0")){cellValue = "No";}
            toSheetOrder.setValueAt(cellValue, 8,nRowIndex);
            
            
            
        }
        
        toSheetContact.getSpreadSheet().saveAs(Tofile);
        toSheetOrder.getSpreadSheet().saveAs(Tofile);
        toSheetContact = null;
        fromSheet = null;
	}
	
	
	
}

