package com.xiangzhurui.core.ext.poi.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class Demo16 {

    public static void main(String[] args) throws InvalidFormatException {
        FileOutputStream out = null;
        OPCPackage pkg;
        // TODO Auto-generated method stub
        String path = Demo16.class.getResource("").getFile();
        XSSFWorkbook wbTemplate;
        try {
            pkg = OPCPackage.open(new File("/Users/xiangzhurui/Desktop/2.xlsx"));
            wbTemplate = new XSSFWorkbook(pkg);
            SXSSFWorkbook writeWB = new SXSSFWorkbook(wbTemplate, 5000, true, true);
            SXSSFSheet writeSheet = writeWB.getSheet("ProductMST");
            // Workbook writeWB = new SXSSFWorkbook(500);
            //org.apache.poi.ss.usermodel.Sheet writeSheet = writeWB.createSheet("1");
            log.info(String.valueOf(new Date()));
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            final long start = System.currentTimeMillis();
            log.info("{}", start);
            for (int i = 0; i < 500000; i++) {
                Row writeRow = writeSheet.createRow(i);
                for (int j = 32; j < 100; j++) {
                    Cell cell = writeRow.createCell(j);
                    //cell.setCellValue("Hello");
                    cell.setCellValue(j + "Hello" + i);
                    cell.setCellValue(j + "Hello");
                    CellStyle cellStyle = cell.getCellStyle();
                    cellStyle.setBorderBottom(BorderStyle.THIN);
                    cellStyle.setBorderTop(BorderStyle.THIN);
                    cellStyle.setBorderLeft(BorderStyle.THIN);
                    cellStyle.setBorderRight(BorderStyle.THIN);
                    cell.setCellStyle(cellStyle);
                }
            }
            log.info("{}", System.currentTimeMillis() - start);
            File file = new File("/Users/xiangzhurui/Desktop/6666666666666555.xlsx");
            out = new FileOutputStream(file);
            writeWB.write(out);
            out.flush();
            out.close();
            log.info("测试成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

