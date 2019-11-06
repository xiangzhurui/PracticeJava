package com.xiangzhurui.core.ext.ftp;

import java.io.File;
import java.util.List;
import java.util.Properties;

/**
 * 文件上传下载接口
 *
 * @author XiangZhuRui
 */
public interface FileTransfer {

    /**
     * @param localFile
     * @param remotePath
     * @return
     */
	boolean uploadFile(File localFile, String remotePath);

    /**
     * @param localPath
     * @param remotePath
     * @return
     */
	boolean uploadFile(String localPath, String remotePath);

    /**
     * @param localFile
     * @param remotePath
     * @return
     */
	boolean uploadAllInDir(File localFile, String remotePath);

    /**
     * @param localPath
     * @param remotePath
     * @return
     */
	boolean uploadAllInDir(String localPath, String remotePath);

    /**
     * @param localFile
     * @param remotePath
     * @return
     */
	boolean downloadFile(File localFile, String remotePath);

    /**
     * @param localPath
     * @param remotePath
     * @return
     */
	boolean downloadFile(String localPath, String remotePath);

    /**
     * @param localDirPath
     * @param remoteDirPath
     * @return
     */
	boolean downloadAllInDir(String localDirPath, String remoteDirPath);

    /**
     * @param localDirFile
     * @param remoteDirPath
     * @return
     */
	boolean downloadAllInDir(File localDirFile, String remoteDirPath);

    /**
     * 删除远程文件
     *
     * @param remotePath 远程文件路径
     * @return
     */
	boolean deleteRemoteFile(String remotePath);

    /**
     * 删除远程目录下的所有文件
     *
     * @param remoteDirPath 远程目录路径
     * @return
     */
	boolean deleteAllInDir(String remoteDirPath);

    /**
     * 列出远端所有文件
     *
     * @param remoteDirPath
     * @return
     */
	List<String> lsAllFiles(String remoteDirPath);

    /**
     * 登录认证方法
     *
     * @return
     */
	boolean authenticate();

    boolean authenticate(Properties pros);

    boolean authenticate(String host, int port, String username, String password);

    /**
     * 断开连接
     *
     * @return
     */
	boolean disConnect();
}
