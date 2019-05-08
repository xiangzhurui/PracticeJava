package com.xiangzhurui.util.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIExcelUtils {
    static String path;

    static {
        path = POIExcelUtils.class.getClass().getResource("").getPath();
//		path = path.substring(0,path.indexOf("WEB-INFO"));
    }

    public static void main(String[] args) {
        String path1 = path;
        Workbook wb = new XSSFWorkbook();
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(path1 + "/workbook.xlsx");
            wb.write(fileOut);
            wb.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static XSSFWorkbook createXSSFWorkbook() {
        Workbook wb = new XSSFWorkbook();

        return (XSSFWorkbook) wb;
    }

}
