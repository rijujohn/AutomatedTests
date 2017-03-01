package com.t2s.excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {
	
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	private XSSFWorkbook workbook = null;
	
	private XSSFSheet sheet = null;
	
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	public Xls_Reader(String path)
	{
		try{
		this.path = path;
	    fis = new FileInputStream(path);
	    workbook = new XSSFWorkbook(path);
	    sheet = workbook.getSheetAt(0);
	    fis.close();
		}
		catch (Exception Error)
		{
			
			Error.printStackTrace();
			
		}
	}
	
	//Returns row count of a sheet
	public int getRowCount(String sheetName)
	{
		int index = workbook.getSheetIndex(sheetName);
		if (index== -1)
			return 0;
		else{
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
		
	//Returns data from a sheet
		
	}
	
	
	public int getColumnCount(String sheetName)
	{
     //if(!isSheetExist(sheetName))
   // 	 return -1;
     
     sheet = workbook.getSheet(sheetName);
     row = sheet.getRow(0);
    	if (row == null)
    		 return -1;
		
		return row.getLastCellNum();
		
		
		}
		
	//@SuppressWarnings("deprecation")
	public String getCellData(String sheetName,int colNum,int rowNum){
		
		int index = workbook.getSheetIndex(sheetName);
		if (index== -1)
			return "";
		sheet = workbook.getSheetAt(index);
		
		String data = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		return data;
//		row = sheet.getRow(rowNum - 1);
//		if (row == null)
//			return "";
//		cell = row.getCell(colNum);
//		if (cell == null)
//			return "";
		
	//	if (cell.getCellType() == Cell.CELL_TYPE_STRING)
	//		return cell.getStringCellValue();
	//	else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA)
	//		String cellText = String.valueOf(cell.getNumericCellValue());
		
	}
	
	

}
