package org.com.utility;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Excelreader2 {
    public static  void main(String args[]) throws IOException {
        
        //Create an object of File class to open xlsx file
        File file =    new File("C:/Users/Akshay/git/repository4/AutomationTest_Artifact1_Instagram/TestData/InstaTestData.xlsx");
        
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        
        //Creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(inputStream);
        
        //Creating a Sheet object using the sheet Name
        HSSFSheet sheet=wb.getSheet("STUDENT_DATA");
        
        //Create a row object to retrieve row at index 1
        HSSFRow row2=sheet.getRow(1);
        
        //Create a cell object to retreive cell at index 5
        HSSFCell cell=row2.getCell(5);
        
        //Get the address in a variable
        String address= cell.getStringCellValue();
        
        //Printing the address
        System.out.println("Address is :"+ address);
    }
}