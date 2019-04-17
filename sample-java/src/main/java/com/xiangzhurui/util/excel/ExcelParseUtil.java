package com.xiangzhurui.util.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author xiangzhurui
 * @version 2017/12/25
 */
public class ExcelParseUtil {

    private Result isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {

            CellRangeAddress range = sheet.getMergedRegion(i);

            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return new Result(true, firstRow + 1, lastRow + 1, firstColumn + 1, lastColumn + 1);
                }
            }
        }
        return new Result(false, 0, 0, 0, 0);
    }

    class Result {
        public boolean merged;
        public int startRow;
        public int endRow;
        public int startCol;
        public int endCol;

        public Result(boolean merged, int startRow, int endRow, int startCol, int endCol) {
            this.merged = merged;
            this.startRow = startRow;
            this.endRow = endRow;
            this.startCol = startCol;
            this.endCol = endCol;
        }
    }
}
