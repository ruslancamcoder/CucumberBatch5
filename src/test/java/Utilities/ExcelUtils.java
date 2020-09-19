package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    //.openExcelFile(String fileName,String sheetName); ->returns ExcelSheet
    //.getValue(int rowNum,int cellNum)->return String;
    //.setValue(int rowNum,int celNum,String value)->void;(no value will be returned)
    //.getRowValues(int rawNum);->List<Strings>;

    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    private static Cell cell;
    private static String path;
    /**
     *
     *
     *
     */

    public static  Sheet openExcelFile(String fileName,String sheetName){
        path="src/test/resources/Data/"+fileName+".xlsx";

        File file=new File(path);

        try {
            FileInputStream input=new FileInputStream(file);
            workbook=new XSSFWorkbook(input);
            sheet=workbook.getSheet(sheetName);


        } catch (FileNotFoundException e) {
            System.out.println(fileName+" excel file does not exist");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return sheet;


    }

    /**
     * This method will return the value in provided rowNum and cellNum in String type
     * @param rowNum
     * @param cellNum
     * @return
     */
    public static String getValue(int rowNum,int cellNum){
        return sheet.getRow(rowNum).getCell(cellNum).toString();
    }

    /**
     * This method will set new value for provided rowNum and cellNum.
     * @param rowNum
     * @param cellNum
     * @param newValue
     */
    public static void setValue(int rowNum,int cellNum,String newValue){
        row=sheet.getRow(rowNum);
        cell=row.getCell(cellNum);

        if(cell==null){
            cell=row.createCell(cellNum);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(newValue);
        }else{
            cell.setCellValue(newValue);
        }

        ////////////////
        FileOutputStream outputStream=null;
        try {
            outputStream=new FileOutputStream(path);
            workbook.write(outputStream);

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * This method will store all values to list from provided rowNum.
     * @param rowNum
     * @return
     */
    public static List<String> getRowValues(int rowNum){
        List<String>values=new ArrayList<>();
        row=sheet.getRow(rowNum);

        for(int i=0;i<row.getLastCellNum();i++){
            values.add(row.getCell(i).toString());
        }
        return values;
    }



}
