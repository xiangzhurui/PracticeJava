package com.xiangzhurui.core.ext.poi.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelExportHelper {
    public static void main(String[] args) throws IOException {
        Workbook outWorkBook = new SXSSFWorkbook();
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        for (int rownum = 0; rownum < 1000; rownum++) {
            Row row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }

        // Rows with rownum < 900 are flushed and not accessible
        for (int rownum = 0; rownum < 900; rownum++) {
            assert (sh.getRow(rownum) != null);
        }

        // ther last 100 rows are still in memory
        for (int rownum = 900; rownum < 1000; rownum++) {
            assert (sh.getRow(rownum) != null);
        }

        File file = new File(System.getProperty("user.dir") + "/temp/sxssf.xlsx");
        System.out.println(file);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.close();
        wb.dispose();
    }
}
