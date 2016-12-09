package sample.poi.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUitls {
	static String path;
	static {
		path = POIExcelUtils.class.getResource("").getPath();
		path = path.substring(0, path.indexOf("classes"));
	}

	public static void main(String[] args) throws IOException {
		createXSSFWorkbook(XSSFWorkbook.class);
	}

	public static Workbook createXSSFWorkbook(Class<?> clazz) throws IOException {
		String path1 = path;

		Workbook wb = new XSSFWorkbook();
		Workbook wbxls = new HSSFWorkbook();
		FileOutputStream fileOut = null;
		FileOutputStream fileOut1 = null;
		FileOutputStream fileOut2 = null;
		try {
			fileOut = new FileOutputStream(path1 + "/workbook.xlsx");
			wb.createSheet("sss");
			wb.write(fileOut);
			wb.close();
			fileOut1 = new FileOutputStream(path1 + "/workbook111.xlsx");
			wbxls.createSheet("sss");
			wbxls.write(fileOut1);
			wbxls.close();
			try {
				fileOut1 = new FileOutputStream(path1 + "/workbooksss.xlsx");
				Workbook wb0 = (Workbook) clazz.newInstance();
				wb0.createSheet("2");
				wb0.write(fileOut2);
				wb0.close();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOut != null)
				fileOut.close();
			if (fileOut1 != null)
				fileOut1.close();
		}
		return null;
	}

	public boolean readWorkbook(Workbook wb) {
		Sheet sheet0 = wb.getSheetAt(0);
		Row row0 = sheet0.getRow(0);
		Iterator<Cell> iterator = row0.cellIterator();
		while (iterator.hasNext()) {
			Cell cell = iterator.next();
			Integer i = cell.getColumnIndex();
			String s = getCellContent(cell);
			log.debug(s);
			log.debug("{}", i);
			if (StringUtils.isBlank(s)) {
				log.info("文件模板错误，表头第[{}]列为空。", i);
				errorMsg.put("error", "文件模板错误，表头第[" + i + "]列为空。");
				return false;
			}
			switch (StringUtils.trim(s)) {
			case "姓名":
				title.put("name", i);
				break;
			case "性别":
				title.put("gender", i);
				break;
			case "生日":
				title.put("birthday", i);
				break;
			case "年龄":
				title.put("age", i);
				break;
			case "证件号码":
				title.put("idNo", i);
				break;
			case "电子邮箱":
				title.put("email", i);
				break;
			case "手机号码":
				title.put("mobilePhone", i);
				break;
			case "备注":
				title.put("remark", i);
				break;
			}
		}
		wb.close();
		return true;
	}
}
