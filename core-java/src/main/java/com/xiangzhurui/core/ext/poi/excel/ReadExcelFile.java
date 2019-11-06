package com.xiangzhurui.core.ext.poi.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadExcelFile {
    final static Logger log = LoggerFactory.getLogger(ReadExcelFile.class);
    private File file;
    private String inputnum;
    // 列索引与标题映射，使列列顺序无关
    private Map<String, Integer> title;
    private Map<String, String> errorMsg;

    public ReadExcelFile() {
        super();
        this.title = new HashMap<String, Integer>();
        this.errorMsg = new HashMap<String, String>();
    }

    /**
     * 返回Date类型的出生日期
     *
     * @param cell
     * @return
     */
    public static java.util.Date getBirthdayCellValue(Cell cell) {

        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case STRING:
                String rts = cell.getRichStringCellValue().toString();
                return convertStringToDate(rts);
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    log.debug("当前单元格值是[{}]", cell.getDateCellValue());
                    return cell.getDateCellValue();
                } else {
                    return null;
                }
            case FORMULA:
                log.debug("当前单元格的值是：{}", cell.getCellFormula());
                return convertStringToDate(cell.getCellFormula());
            case BLANK:
                return null;
            default:
                log.debug("无法识别的单元格类型[{}]", cellType);
                return null;
        }
    }

    /**
     * 获取一般单元格内容，返回String
     *
     * @param cell
     * @return
     */
    private static String getCellContent(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case STRING:
                return cell.getRichStringCellValue().toString();
            case FORMULA:
                log.debug("当前单元格的值是：{}", cell.getCellFormula());
                return cell.getCellFormula();
            case BLANK:
                return null;
            default:
                log.debug("无法识别的单元格类型[{}]", cellType);
                return null;
        }
    }

    /**
     * 将String转为java.util.Date类型,支持两种字符串形式： yyyy-MM-dd 与 yyyy/MM/dd
     *
     * @param s
     * @return
     */
    private static Date convertStringToDate(String s) {
        // 匹配yyyy-MM-dd
        Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\-\\s]?((((0?"
                                            + "[13578])|(1[02]))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))"
                                            + "|(((0?[469])|(11))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|"
                                            + "(0?2[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12"
                                            + "35679])|([13579][01345789]))[\\-\\-\\s]?((((0?[13578])|(1[02]))"
                                            + "[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
                                            + "[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\-\\s]?((0?[" + "1-9])|(1[0-9])|(2[0-8]))))))");
        // 匹配yyyy/MM/dd
        Pattern p1 = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\/\\/\\s]?((((0?"
                                             + "[13578])|(1[02]))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))"
                                             + "|(((0?[469])|(11))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|"
                                             + "(0?2[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12"
                                             + "35679])|([13579][01345789]))[\\/\\/\\s]?((((0?[13578])|(1[02]))"
                                             + "[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
                                             + "[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/\\/\\s]?((0?[" + "1-9])|(1[0-9])|(2[0-8]))))))");
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter format1 = DateTimeFormat.forPattern("yyyy/MM/dd");
        log.debug("p校验2015-1-1的结果{}", p.matcher("2015-1-1").matches());
        log.debug("p1校验2015/01/1的结果{}", p1.matcher("2015/01/1").matches());
        if (p.matcher(s).matches()) {
            DateTime dateTime = DateTime.parse(s, format);
            return dateTime.toDate();
        } else if (p1.matcher(s).matches()) {
            DateTime dateTime = DateTime.parse(s, format1);
            return dateTime.toDate();
        } else {
            return null;
        }
    }

    /**
     * 检测作为标题栏的第一行
     *
     * @return
     * @throws EncryptedDocumentException
     * @throws InvalidFormatException
     * @throws IOException
     */
    public boolean checkAndSetTitile() throws EncryptedDocumentException, InvalidFormatException, IOException {
        Workbook wb = WorkbookFactory.create(file);
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

    public void read() throws EncryptedDocumentException, InvalidFormatException, IOException {
        if (null == title) {
            checkAndSetTitile();
        }
        List<RowTemplate> rowList = new ArrayList<RowTemplate>();
        Workbook wb = WorkbookFactory.create(file);
        Sheet sheet0 = wb.getSheetAt(0);
        int count = 0;
        for (Row row : sheet0) {// 读取第一个sheet页
            RowTemplate rt = new RowTemplate(); // 正确行
            Iterator<Cell> it = row.iterator();
            log.debug("行号是{}", row.getRowNum());
            if (0 == row.getRowNum()) {
                log.info("略过第一行表头");
                continue;
            }
            count++;
            String name = null;
            String gender = null;
            java.util.Date birthday = null;
            String age = null;
            String idNo = null;
            String email = null;
            String mobilePhone = null;
            String remark = null;
            while (it.hasNext()) {
                Cell cell = it.next();
                int cellClIx = cell.getColumnIndex();

                try {
                    if (cellClIx == title.get("name")) {
                        name = ReadExcelFile.getCellContent(cell);
                    }
                    if (cellClIx == title.get("gender")) {
                        gender = ReadExcelFile.getCellContent(cell);
                        log.debug("性别为[{}]", gender);
                        if ("男".equals(gender) || "M".equalsIgnoreCase(gender)) {
                            gender = "0";
                        } else if ("女".equals(gender) || "F".equalsIgnoreCase(gender)) {
                            gender = "1";
                        } else {
                            log.info("性别填写错误,当前值[{}]非可支持内容", gender);
                            gender = "";
                        }
                    }
                    if (cellClIx == title.get("birthday")) {

                        birthday = ReadExcelFile.getBirthdayCellValue(cell);
                    }
                    if (cellClIx == title.get("age")) {
                        age = ReadExcelFile.getCellContent(cell);
                    }
                    if (cellClIx == title.get("idNo")) {
                        idNo = ReadExcelFile.getCellContent(cell);
                    }
                    if (cellClIx == title.get("email")) {
                        email = ReadExcelFile.getCellContent(cell);
                    }
                    if (cellClIx == title.get("mobilePhone")) {
                        mobilePhone = ReadExcelFile.getCellContent(cell);
                    }
                    if (cellClIx == title.get("remark")) {
                        remark = ReadExcelFile.getCellContent(cell);
                    }
                } catch (Exception e) {
                    log.warn("文件内容格式错误，[{}]行[{}]列的单元格格式错误", cell.getRowIndex(), cellClIx, e);
                    continue;
                }

            }
            rt.setName(name);
            rt.setGender(gender);
            rt.setBirthday(birthday);
            rt.setAge(age);
            rt.setIdNo(idNo);
            rt.setMobilePhone(mobilePhone);
            rt.setRemark(remark);
            rt.setEmail(email);

            if (rt.isEmptyRow()) {
                log.info("第[{}]行为空行", row.getRowNum());
            } else {
                if (rt.hasMobilePhone()) {
                    log.debug("{}是手机号", rt.getMobilePhone());
                    rowList.add(rt);
                } else {
                    log.info("第[{}]行电话号码为空", row.getRowNum());
                }
            }
            if (1000 == count) {
                count = 0;
                int i = this.persistList(rowList);
                log.info("暂存数据条数{}", i);
                rowList.clear();
            }
        }
        if (count > 0) {
            log.info("剩余未提交的数据条数[{}]", count);
            int i = this.persistList(rowList);
            log.info("暂存数据条数{}", i);
        }
        wb.close();
    }

    private int persistList(List<RowTemplate> rowList) {
        if (rowList.isEmpty()) {
            log.info("参数List为空，尺寸为[{}]", rowList.size());
            return 0;
        }
        return 0;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getInputnum() {
        return inputnum;
    }

    public void setInputnum(String inputnum) {
        this.inputnum = inputnum;
    }

    public Map<String, Integer> getTitle() {
        return title;
    }

    public void setTitle(Map<String, Integer> title) {
        this.title = title;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

}
