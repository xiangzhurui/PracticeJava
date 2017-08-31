package personal.xzr.practice.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 使用SFTP传输协议的上传下载工具类
 *
 * @author XiangZhuRui
 *
 */
public class SftpImplFileTransfer implements FileTransfer {
	final static Logger	log	= LoggerFactory.getLogger(SftpImplFileTransfer.class);

	private String		host;
	private int			port;
	private String		username;
	private String		password;

	private Session		session;
	private ChannelSftp	channelSftp;

	@Override
	public boolean uploadFile(File localFile, String remotePath) {
		log.info("sftp切换到上传目录");
		try {
			channelSftp.cd(remotePath);
		} catch (SftpException e1) {
			log.info("{}目录不存在，创建", remotePath);
			try {
				channelSftp.mkdir(remotePath);
				channelSftp.cd(remotePath);
				log.info("sftp切换到创建的上传目录");
				log.info("当前远端路径是[{}]", channelSftp.pwd());
			} catch (SftpException e) {
				log.error("上传失败。原因是远端上传路径不存在，且没有创建文件权限。", e);
				return false;
			}
		}
		try {
			channelSftp.put(new FileInputStream(localFile), localFile.getName());
			log.info("文件{}传输成功", localFile.getPath());
			return true;
		} catch (FileNotFoundException e) {
			log.warn("上传失败，本地文件不存在", e);
			return false;
		} catch (SftpException e) {

			log.error("sftp相关错误。", e);
			return false;
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
		// TODO Auto-generated method stub
		return false;
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
		File localDirFile = new File(localDirPath);
		return this.downloadAllInDir(localDirFile, remoteDirPath);
	}

	@Override
	public boolean downloadAllInDir(File localDirFile, String remoteDirPath) {
		log.info("登录信息,用户名：{},密码：{},端口号：{}", username, password, port);
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(username, host, port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no"); /* 接受所有key */
			session.setConfig(config);
			session.connect();
			channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();
			log.info("当前本地路径[{}]", channelSftp.lpwd());
			log.info("当前远端路径[{}]", channelSftp.pwd());
			try {
				channelSftp.cd(remoteDirPath);
			} catch (Exception e) {
				log.error("remoteDirPath不存在", e);
				return false;
			}
			log.debug(channelSftp.pwd());
			// 列出服务器指定的文件列表
			Vector<?> v = channelSftp.ls("*.*");
			log.info("数量{}", v.size());
			if (v.size() == 0) {
				return true;
			}
			for (Object obj : v) {
				if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
					com.jcraft.jsch.ChannelSftp.LsEntry lsEntry = (com.jcraft.jsch.ChannelSftp.LsEntry) obj;
					log.info("{}", lsEntry.getFilename());
					if (!lsEntry.getAttrs().isDir()) {
						try {
							channelSftp.get(lsEntry.getFilename(), localDirFile.getPath());
							log.info("下载文件{}，到{}", lsEntry.getFilename(), localDirFile.getPath());
							channelSftp.rm(lsEntry.getFilename());
							log.info("删除远程文件{}", lsEntry.getFilename());
						} catch (Exception e) {
							log.error("下载文件{}失败", lsEntry.getFilename());
						}
					}

				}
			}
			return true;
		} catch (JSchException e) {
			log.error("发生Jsch相关错误", e);
			return false;
		} catch (SftpException e) {
			log.error("发生Sftp相关错误", e);
			return false;
		}

	}

	@Override
	public boolean deleteRemoteFile(String remotePath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllInDir(String remoteDirPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> lsAllFiles(String remoteDirPath) {
		List<String> fileList = new ArrayList<String>();
		// 列出服务器指定的文件列表
		Vector<?> v = null;
		try {
			v = channelSftp.ls("*.csv");
		} catch (SftpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.info("数量{}", v.size());
		if (v.size() == 0) {
		}
		for (Object obj : v) {
			if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
				com.jcraft.jsch.ChannelSftp.LsEntry lsEntry = (com.jcraft.jsch.ChannelSftp.LsEntry) obj;
				log.info("{}", lsEntry.getFilename());
				if (!lsEntry.getAttrs().isDir()) {
					try {
						String s = lsEntry.getFilename();
						fileList.add(s);
					} catch (Exception e) {
						log.error("下载文件{}失败", lsEntry.getFilename());
					}
				}

			}
		}
		return fileList;
	}

	/*
	 * 登录信息调用
	 * 
	 * @see basic.io.FileTransfer#authenticate()
	 */
	@Override
	public boolean authenticate() {
		try {
			JSch jsch = new JSch();
			if (password == null) {
				session = jsch.getSession(username, host);
			} else {
				session = jsch.getSession(username, host, port);
			}

			/* 接受所有key */
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			log.info("SSH登录成功，Session已创建成功");

			channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();
			log.info("当前本地路径[{}]", channelSftp.lpwd());
			log.info("当前远端路径[{}]", channelSftp.pwd());
			log.info("通道连接成功");
			return true;
		} catch (Exception e) {
			log.error("连接错误", e);
			return false;
		} finally {
			/* 操作完毕后，关闭通道并退出本次会话 */
			channelSftp.exit();
			log.info("关闭通道");
		}
	}

	/**
	 * 传入包含登录认证信息的Properties的方法
	 * 
	 * @param pros
	 * @return
	 */
	public boolean authenticate(Properties pros) {
		this.host = pros.getProperty("sftp.host").trim();
		this.port = Integer.valueOf(pros.getProperty("sftp.port", "22").trim());
		this.username = pros.getProperty("sftp.userName").trim();
		this.password = pros.getProperty("sftp.password").trim();
		return this.authenticate();

	}

	/**
	 * 第二种传入登录认证信息的登录方法
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(String host, int port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		return this.authenticate();

	}

	@Override
	public boolean disConnect() {
		try {
			if (channelSftp != null && channelSftp.isConnected()) {
				channelSftp.disconnect();
				log.info("断开通道");
			}

			if (session != null && session.isConnected()) {
				session.disconnect();
				log.info("结束会话。");
			}
		} catch (Exception e) {
			log.warn("断开连接失败.", e);
			return false;
		}
		log.info("已断开sftp连接。");
		return true;
	}

	public SftpImplFileTransfer() {
		super();
	}

}
