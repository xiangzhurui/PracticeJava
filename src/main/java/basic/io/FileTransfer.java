package basic.io;

import java.io.File;
import java.util.List;
import java.util.Properties;

/**
 * 文件上传下载接口
 * 
 * @author XiangZhuRui
 *
 */
public interface FileTransfer {

	/**
	 * @param localFile
	 * @param remotePath
	 * @return
	 */
	public boolean uploadFile(File localFile, String remotePath);

	/**
	 * @param localPath
	 * @param remotePath
	 * @return
	 */
	public boolean uploadFile(String localPath, String remotePath);

	/**
	 * @param localFile
	 * @param remotePath
	 * @return
	 */
	public boolean uploadAllInDir(File localFile, String remotePath);

	/**
	 * @param localPath
	 * @param remotePath
	 * @return
	 */
	public boolean uploadAllInDir(String localPath, String remotePath);

	/**
	 * @param localFile
	 * @param remotePath
	 * @return
	 */
	public boolean downloadFile(File localFile, String remotePath);

	/**
	 * @param localPath
	 * @param remotePath
	 * @return
	 */
	public boolean downloadFile(String localPath, String remotePath);

	/**
	 * @param localDirPath
	 * @param remoteDirPath
	 * @return
	 */
	public boolean downloadAllInDir(String localDirPath, String remoteDirPath);

	/**
	 * @param localDirFile
	 * @param remoteDirPath
	 * @return
	 */
	public boolean downloadAllInDir(File localDirFile, String remoteDirPath);

	/**
	 * 列出远端所有文件
	 * 
	 * @param remoteDirPath
	 * @return
	 */
	public List<String> lsAllFiles(String remoteDirPath);

	/**
	 * 登录认证方法
	 * 
	 * @return
	 */
	public boolean authenticate();

	public boolean authenticate(Properties pros);

	public boolean authenticate(String host, int port, String username, String password);

	/**
	 * 断开连接
	 * 
	 * @return
	 */
	public boolean disConnect();
}
