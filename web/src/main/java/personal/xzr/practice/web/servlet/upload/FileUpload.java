package personal.xzr.practice.web.servlet.upload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Commons-fileupload的文件上传处理类<br/> 
 * 接受文件上传，及表单数据
 * 
 * @author XiangZhuRui
 * @date 2016.11.20
 * @since JDK 1.7
 */
public class FileUpload {
	final static Logger				log	= LoggerFactory.getLogger(FileUpload.class);
	private Map<String, String>		params;
	private Map<String, FileItem>	files;
	private Map<String, String>		message;
	private String					userId;
	private String					inputNum;
	private String					filePath;

	public FileUpload() {
		this.params = new HashMap<String, String>();
		this.files = new HashMap<String, FileItem>();
		this.message = new HashMap<String, String>();
	}

	public boolean setMap(HttpServletRequest request) throws UnsupportedEncodingException {
		String appRootPath = request.getServletContext().getRealPath("/");
		log.info("获取站点绝对路径[{}]", appRootPath);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("GBK");
		List<?> fileItems = null;
		try {
			fileItems = upload.parseRequest(request);
			Iterator<?> iter = fileItems.iterator();

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("GBK");
					log.debug("{}==={}", fieldName, fieldValue);
					params.put(fieldName, fieldValue);
				} else {
					this.saveFile(item, appRootPath);
				}
			}
		} catch (FileUploadException e1) {
			log.warn("", e1);
			message.put("Content", "文件上传失败!");
			return false;
		} catch (Exception e) {
			log.error("", e);
			message.put("Content", "文件上传失败，发生了错误!");
			return false;
		}
		message.put("FlagStr", "success");
		return true;
	}

	// 文件保存在WEB-INF/upload/Excel_CSV/{currentDate}/inputNum_userId_fileName
	public void saveFile(FileItem item, String appRootPath) throws Exception {
		String fileName = item.getName();
		log.debug("初始获取到的fileName是[{}]", fileName);
		int lastIndex = fileName.lastIndexOf("\\");
		fileName = fileName.substring(lastIndex + 1);

		fileName = this.getInputNum() + "_" + this.getUserId() + "_" + fileName;
		log.debug("处理后的fileName是[{}]", fileName);
		log.info("应用文件路径是{}", appRootPath);
		Path path = Paths.get(appRootPath, "WEB-INF", "upload", "Excel_CSV", DateTime.now().toString("yyyy-MM-dd"),
		        fileName);
		File saveFile = path.toFile();
		this.setFilePath(saveFile.getPath());
		if (!path.getParent().toFile().exists()) {
			log.info("将要上传的文件夹[{}]不存在，创建一个", path.getParent());
			path.getParent().toFile().mkdirs();
		}
		log.info("文件上传到的路径是[{}]", saveFile);
		item.write(saveFile);
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Map<String, FileItem> getFiles() {
		return files;
	}

	public void setFiles(Map<String, FileItem> files) {
		this.files = files;
	}

	public Map<String, String> getMessage() {
		return message;
	}

	public void setMessage(Map<String, String> message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInputNum() {
		return inputNum;
	}

	public void setInputNum(String inputNum) {
		this.inputNum = inputNum;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
