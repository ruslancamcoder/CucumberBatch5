package Utilities;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelTest {
  public static void main(String[]args){
//        File file=new File("src/test/resources/Data/TestCases.xlsx");
//
//        try {
//            FileInputStream input=new FileInputStream(file);
//            Workbook workbook=new XSSFWorkbook(input);
//            Sheet excelSheet=workbook.getSheet("Sheet1");
//            String value=excelSheet.getRow(0).getCell(0).toString();
//            System.out.println(value);
//            Row row=excelSheet.getRow(1);
//
//            for(int i=0;i<5;i++){
//                System.out.println();
//                System.out.println(row.getCell(i).toString());
//            }
//            Row row1=excelSheet.getRow(1);
//            Cell cell1=row1.createCell(6);
//            cell1.setCellType(CellType.STRING);
//            cell1.setCellValue("PASS");
//
//            FileOutputStream fileOutputStream=new FileOutputStream("src/test/resources/Data/TestCases.xlsx");
//            workbook.write(fileOutputStream);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

      ExcelUtils.openExcelFile("TestCases","Sheet1");
      String expected=ExcelUtils.getValue(1,4);
      System.out.println(expected);

      ExcelUtils.setValue(1,1,"TEC-2011");

     List<String> values= ExcelUtils.getRowValues(0);
      System.out.println(values);
    }

}
