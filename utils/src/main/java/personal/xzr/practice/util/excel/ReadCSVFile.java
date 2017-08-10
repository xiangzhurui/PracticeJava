package personal.xzr.practice.util.excel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理CSV格式的文本
 */
public class ReadCSVFile {
	private static final Logger	log							= LoggerFactory.getLogger(ReadCSVFile.class);

	private Map<String, String>	msgMap						= new HashMap<String, String>();
	private String				filePath;

	public boolean read() throws IOException {
		String extFileName = filePath.substring(filePath.lastIndexOf("."));
		log.info("上传的文件类型是:[{}]", extFileName);
		if (".csv".equalsIgnoreCase(extFileName)) {
			log.info("文件是.csv文件：[{}]", filePath);
			msgMap.put("isSuccess", "false");
			msgMap.put("msgContent", "上传文件不正确，当前仅支持CSV格式文件");
			return this.readCSV();
		} else {
			msgMap.put("isSuccess", "false");
			msgMap.put("msgContent", "上传文件是格式错误");
			return false;
		}
	}

	/**
	 * 读取csv文件并将记录信息存入数据表 [tbl_lroverridedata]
	 * 
	 * @return
	 */
	private boolean readCSV() {
		Reader in = null;
		try {
			in = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			log.error("csv文件不存在", e);
		}
		Iterable<CSVRecord> records = null;
		try {
			records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		} catch (IOException e) {
			log.error("文件读取异常", e);
		}
		List<RowTemplate> customerList = new ArrayList<RowTemplate>();
		for (CSVRecord record : records) {
			try {
				String mobilePhone = record.get("手机号码").trim();
				String birthday = record.get("出生日期").trim();
				String gender = record.get("性别").trim();
				String name = record.get("姓名").trim();
				String idNo = record.get("证件号码").trim();
				String remark = record.get("备注").trim();
				RowTemplate bean = new RowTemplate();
				try {
					bean.setGender(gender);
				} catch (IllegalArgumentException e) {
					log.error("{}性别参数错误", mobilePhone, e);
				}
				bean.setBirthday(convertString2SQLDate(birthday));
				bean.setIdNo(idNo);
				bean.setMobilePhone(mobilePhone);
				bean.setName(name);
				bean.setRemark(remark);
				customerList.add(bean);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof IllegalArgumentException) {
					log.warn("csv文件字符编码错误，非gbk编码文件");
				}
			}
		}
		int beansVolume = customerList.size();
		log.info("数据量总数[{}]", beansVolume);

		boolean isSuccess = this.executeBatch(customerList);
		log.debug("更新数据库结果[{}]", isSuccess);
		Map<String, String> map = new HashMap<String, String>();
		map.put("beansVolume", String.valueOf(beansVolume));
		map.put("isSuccess", String.valueOf(isSuccess));
		this.setMsgMap(map);
		return isSuccess;
	}

	private boolean executeBatch(List<RowTemplate> beans) {
		boolean b = false;
		return b;
	}

	private static java.sql.Date convertString2SQLDate(String str) {
		Pattern date0 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		Pattern date1 = Pattern.compile("[0-9]{4}\\/[0-9]{1,2}\\/[0-9]{0,2}");
		java.sql.Date sqlDate = null;
		try {
			if (date0.matcher(str).matches()) {
				java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
				sqlDate = new java.sql.Date(date.getTime());
			} else if (date1.matcher(str).matches()) {
				java.util.Date date = new SimpleDateFormat("yyyy/MM/dd").parse(str);
				sqlDate = new java.sql.Date(date.getTime());
			} else if (StringUtils.isNotBlank(str)) {
				sqlDate = new java.sql.Date(32503651200000L); // 该日期为公元3000-01-01
				log.info("生日日期不为空，但格式错误,参数[{}]", str);
			} else {
				log.info("日期输入为空或其他错误,参数值为[{}]", str);
			}

		} catch (Exception e) {
			log.error("日期转换错误，可能是字符串类型错误，转换前字符串[{}]", str, e);
		}
		return sqlDate;
	}

	public Map<String, String> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<String, String> msgMap) {
		this.msgMap = msgMap;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
