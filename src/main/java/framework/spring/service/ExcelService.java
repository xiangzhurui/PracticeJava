package framework.spring.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: ExcelService
 * @Description:EXCEL读取
 * @author QSNP089
 * @date 2010-10-14 下午05:33:51
 * 
 */
@Service
@Scope("prototype")
public class ExcelService {

	@Autowired
	private PolicyService	policyService;
	private InputStream		inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	HSSFWorkbook	hssfWorkbook	= null;
	HSSFSheet		sheet			= null;
	FileInputStream	readFile;

	public ExcelService(String strPath) throws Exception {
		readFile = new FileInputStream(strPath);
		hssfWorkbook = new HSSFWorkbook(readFile);

	}

	public ExcelService() {

	}

	public void setHssfWorkbook(String strPath) throws FileNotFoundException, IOException {
		readFile = new FileInputStream(strPath);
		hssfWorkbook = new HSSFWorkbook(readFile);
	}

	public HSSFSheet getSheet(int sheetIndex) {
		return hssfWorkbook.getSheetAt(sheetIndex);
	}

	public HSSFSheet getSheet(String sheetName) {
		return hssfWorkbook.getSheet(sheetName);
	}

	public int getExcelRows() {
		return sheet.getPhysicalNumberOfRows();
	}

	public int getExcelRows(int sheetIndex) {
		return hssfWorkbook.getSheetAt(sheetIndex).getPhysicalNumberOfRows();
	}

	public HSSFRow getRow(int rowIndex) {
		return sheet.getRow(rowIndex);
	}

	public HSSFCell getCell(int rowIndex, int cellnum) {

		return getRow(rowIndex).getCell(cellnum);
	}

	/**
	 * @throws Exception
	 * 
	 * @Title: getValue @Description:从1开始计数. @param rowIndex @param
	 * cellnum @return String @throws
	 */
	public String getStringValue(int rowIndex, int cellnum) throws Exception {
		if (sheet == null)
			throw new Exception("未选中需要处理的sheet");
		Cell cell = getCell(rowIndex - 1, cellnum - 1);
		String returnValue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				returnValue = "" + cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				returnValue = "" + cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				returnValue = "";
				break;
			case Cell.CELL_TYPE_STRING:
				returnValue = cell.getStringCellValue();
				break;
			}
		}
		return returnValue;
	}

	public void closeInputStream() {
		try {
			readFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public double getDoubleValue(int rowIndex, int cellnum) throws Exception {
		if (sheet == null)
			throw new Exception("未选中需要处理的sheet");
		Cell cell = getCell(rowIndex - 1, cellnum - 1);
		return cell.getNumericCellValue();
	}

	public boolean getBooleanValue(int rowIndex, int cellnum) throws Exception {
		if (sheet == null)
			throw new Exception("未选中需要处理的sheet");
		Cell cell = getCell(rowIndex - 1, cellnum - 1);
		return cell.getBooleanCellValue();
	}

	public void showExcel() throws Exception {
		if (sheet == null)
			throw new Exception("未选中需要处理的sheet");
		HSSFRow row = null;
		int totalRowNum = sheet.getPhysicalNumberOfRows();
		int totalCellNum = 0;
		for (int i = 1; i <= totalRowNum; i++) {
			row = sheet.getRow(i - 1);
			totalCellNum = row.getPhysicalNumberOfCells();
			for (int j = 1; j <= totalCellNum; j++) {
				System.out.print(getStringValue(i, j) + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @Title: selectOperationSheet @Description: sheet从1开始计数 @param
	 * sheetIndex @return void @throws
	 */
	public void selectOperationSheet(int sheetIndex) {
		this.sheet = getSheet(sheetIndex - 1);

	}

	public void selectOperationSheet(String sheetName) {
		this.sheet = getSheet(sheetName);
	}

	public static void main(String[] args) throws Exception {
		ExcelService excelService = new ExcelService("D:\\TpComm.xls");
		excelService.selectOperationSheet(1);
		// excelService.selectOperationSheet("sheet1");
		excelService.showExcel();
		System.out.println(excelService.getStringValue(2, 1).replace(".0", ""));
	}

	/**
	 * excel开发
	 */
	public String[][] readFromExcel(String address) {
		// 创建workbook
		// 获取excel 模板路径 path
		StringBuffer name = new StringBuffer();
		name.append(request.getSession().getServletContext().getRealPath("/ ").trim()).append(address);
		String path = name.toString();
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 判断文件是否存在
		try {
			workbook = new HSSFWorkbook(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 读取excel数据
		// 获得指定的excel表
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 获取表格的总行数
		int rowCount = sheet.getLastRowNum() + 1; // 需要加1
		System.out.println("rowCount:" + rowCount);
		if (rowCount < 1) {
			System.out.println("-----------------------------------------错误");
		}
		;
		// 获取表头的列数
		int columnCount = sheet.getRow(0).getLastCellNum();
		// 获得表头行对象
		// HSSFRow titleRow = sheet.getRow(0);
		// 遍历
		String[][] column = new String[3][columnCount];
		for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) { // 遍历表头列
			String title = sheet.getRow(0).getCell(columnIndex).toString().trim(); // 表头标题
			String data = sheet.getRow(1).getCell(columnIndex).toString().trim();// 查询结果对应名称
			String sign = "";
			if (sheet.getRow(2).getCell(columnIndex) != null) {
				sign = sheet.getRow(2).getCell(columnIndex).toString();// 第三行对应标识
			}
			column[0][columnIndex] = title;
			column[1][columnIndex] = data;
			column[2][columnIndex] = sign;
		}

		return column;
	}

	@SuppressWarnings("null")
	public InputStream writeToExcel(String[][] column, List<Object[]> receiptList) {

		System.out.println("--------------------getExcel---------start");
		int rowNum = 0;
		// 样式
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		sheet.setDefaultColumnWidth(15);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		HSSFCellStyle styleRed = wb.createCellStyle();
		styleRed.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleRed.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleRed.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleRed.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleRed.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		styleRed.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		DecimalFormat fmtInteger = new DecimalFormat("#,##0.00");
		SimpleDateFormat fmDate = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("--------------getExcel-----------");

		// 写入标题
		int index = 0;
		HSSFRow row = sheet.createRow(rowNum++);
		HSSFCell cell = row.createCell(index);
		for (int i = 0; i < column[0].length; i++) {
			String title = column[0][i];
			cell = row.createCell(index++);
			cell.setCellValue(title);
			cell.setCellStyle(style);
		}

		// 获取module-description的list,存在HashMap中，之后写入excel从HashMap取值。
		HashMap<String, List> moduleList = new HashMap<String, List>();
		for (int i = 0; i < column[0].length; i++) {
			String s = column[2][i];
			if (s.contains("#")) {
				System.out.println(s);
				// ss为module值,listName为生成List的key值
				String ss = s.substring(1 + s.indexOf("#"));
				System.out.println(ss);
				List list = policyService.getInfoNameString(ss);
				String listName = ss + "List";
				System.out.println(listName);
				moduleList.put(listName, list);
			}

		}

		// 写入excel
		for (Object obj : receiptList) {
			HashMap<String, Object> ar = (HashMap<String, Object>) obj;
			index = 0;
			row = sheet.createRow(rowNum++);

			for (int i = 0; i < column[0].length; i++) {
				String data = column[1][i];
				String sign = column[2][i];
				cell = row.createCell(index++);
				cell.setCellStyle(style);

				if (sign == null | sign.equals("")) {
					if (ar.get(data) != null) {
						cell.setCellValue(((String) ar.get(data)).trim());
					}

				} else if (sign.equalsIgnoreCase("date")) {
					if (ar.get(data) != null) {
						String date = fmDate.format(ar.get(data));
						cell.setCellValue(date);
					} else {
						cell.setCellValue((String) ar.get(data));
					}
				} else if (sign.equalsIgnoreCase("num")) {

					if (ar.get(data) instanceof Double) {
						if (ar.get(data) != null) {
							String DoubleNumber = fmtInteger.format(ar.get(data));
							cell.setCellValue(DoubleNumber);
						} else {
							cell.setCellValue((String) ar.get(data));
						}
					} else if (ar.get(data) instanceof Float) {
						if (ar.get(data) != null) {
							String DoubleNumber = fmtInteger.format(((Float) ar.get(data)).doubleValue());
							cell.setCellValue(DoubleNumber);
						} else {
							cell.setCellValue((String) ar.get(data));
						}
					} else {
						if (ar.get(data) != null) {
							BigDecimal bd = new BigDecimal((String) ar.get(data));
							String DoubleNumber = fmtInteger.format(bd.doubleValue());
							cell.setCellValue(DoubleNumber);
						} else {
							cell.setCellValue((String) ar.get(data));
						}
					}

				} else if (sign.equalsIgnoreCase("integer")) {

					if (ar.get(data) != null) {
						String number = (ar.get(data).toString());
						cell.setCellValue(number);
					} else {
						cell.setCellValue((String) ar.get(data));
					}

				} else if (sign.contains("#")) {
					String listName = sign.substring(1 + sign.indexOf("#")) + "List";
					List list = moduleList.get(listName);
					String module = ((String) ar.get(data)).trim();
					String moduleString = "";
					if (module != null && !"".equals(module)) {
						for (int j = 0; j < list.size(); j++) {
							Map map = (Map) list.get(j);
							if (module.equals(map.get("code"))) {
								moduleString = (String) map.get("description");
							}
						}
						cell.setCellValue(moduleString);
					}

				}

			}

		}

		// 输出流，输出excel
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			wb.write(baos);
			byte[] ba = baos.toByteArray();
			this.inputStream = new ByteArrayInputStream(ba);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputStream;
	}

	public InputStream OutExcel(String address, List<Object[]> list) {
		String[][] read = readFromExcel(address);
		InputStream is = writeToExcel(read, list);
		return is;

	}

	public String SetFileName(String fileName) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		StringBuilder name;

		try {
			name = new StringBuilder(new String(fileName.getBytes(), "ISO8859-1"));

		} catch (UnsupportedEncodingException e) {
			name = new StringBuilder();
		}

		name.append(date).append(".xls");
		return name.toString();

	}

}
