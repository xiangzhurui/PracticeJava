package basic.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用FTP协议上传下载的工具类，依赖于 Apache Commons Net 库
 * 
 * @author XiangZhuRui
 *
 */
public class FtpImplFileTransfer implements FileTransfer {
	final static Logger	log	= LoggerFactory.getLogger(FtpImplFileTransfer.class);
	private String		host;
	private int			port;
	private String		username;
	private String		password;
	private FTPClient	ftpClient;

	@Override
	public boolean uploadFile(File localFile, String remotePath) {
		log.info("开始上传文件");
		BufferedInputStream buffIn = null;
		try {
			ftpClient.changeWorkingDirectory(remotePath);
			buffIn = new BufferedInputStream(new FileInputStream(localFile));
			ftpClient.storeFile(localFile.getName(), buffIn);
			return true;
		} catch (FileNotFoundException e1) {
			log.error("上传失败,本地文件不存在。", e1);
			return false;
		} catch (IOException e) {
			log.error("上传失败", e);
			return false;
		} finally {
			try {
				if (buffIn != null) {
					buffIn.close();
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}

	}

	@Override
	public boolean uploadFile(String localPath, String remotePath) {
		File localFile = new File(localPath);
		return this.uploadFile(localFile, remotePath);
	}

	@Override
	public boolean uploadAllInDir(File localFile, String remotePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean uploadAllInDir(String localPath, String remotePath) {
		File localFile = new File(localPath);
		return this.uploadAllInDir(localFile, remotePath);
	}

	@Override
	public boolean downloadFile(File localFile, String remotePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean downloadFile(String localPath, String remotePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean downloadAllInDir(String localDirPath, String remoteDirPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean downloadAllInDir(File localDirFile, String remoteDirPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> lsAllFiles(String remoteDirPath) {
		List<String> paths = new ArrayList<String>();
		String directory = null;
		if (remoteDirPath.startsWith("/") && remoteDirPath.endsWith("/")) {
			directory = remoteDirPath;
		}
		// 更换目录到当前目录
		try {
			ftpClient.changeWorkingDirectory(directory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FTPFile[] files = null;
		try {
			files = ftpClient.listFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (FTPFile file : files) {
			if (file.isFile()) {
				paths.add(directory + file.getName());
			} else if (file.isDirectory()) {
				lsAllFiles(directory + file.getName() + "/");
			}
		}
		return paths;
	}

	@Override
	public boolean authenticate() {
		ftpClient = new FTPClient();
		try {
			ftpClient.connect(host, port);
			if (FTPReply.isPositiveCompletion(this.ftpClient.getReplyCode())) {
				if (ftpClient.login(username, password)) {
					ftpClient.setControlEncoding("UTF-8");
					ftpClient.login(username, password);
					log.info("FTP登录成功");
					log.info("FTP连接成功");
					return true;
				}
			}
			return false;
		} catch (SocketException e) {
			log.error("", e);
			return false;
		} catch (IOException e) {
			log.error("", e);
			return false;
		}
	}

	@Override
	public boolean authenticate(Properties pros) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean authenticate(String host, int port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		return this.authenticate();
	}

	@Override
	public boolean disConnect() {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
				log.info("FTP断开成功");
				return true;
			} catch (IOException e) {
				log.error("FTP退出失败", e);
				return false;
			}
		} else {
			return true;
		}
	}

}
