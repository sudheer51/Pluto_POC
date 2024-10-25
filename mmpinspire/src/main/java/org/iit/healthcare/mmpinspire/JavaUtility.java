package org.iit.healthcare.mmpinspire;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JavaUtility {
	
	public static String getFutureDate(int n,String format)
	{		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		 
		cal.add(Calendar.DATE, n);
		System.out.println(cal.getTime());
		//"MM/dd/YYYY"
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String futureDate = sdf.format(cal.getTime());
		System.out.println("Future Date :::" + futureDate);
		
		return futureDate;
	}
	public static String[][] readXLSX(String fileName) throws IOException
	//public static void main(String args[]) throws IOException
	{
		File f = new File(fileName);
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		String data[][] = new String[rows][cols];
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j< cols;j++)
			{
				XSSFCell cell = sheet.getRow(i).getCell(j);
				data[i][j]= cell.getStringCellValue();
				System.out.println("Values:::" + data[i][j]);
				
			}
		}	
		return data;
		
	}
}
