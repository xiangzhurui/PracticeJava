package com.xiangzhurui.sample.jsch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 使用SFTP协议上传下载
 *
 * @author XiangZhuRui
 */
public class SftpClient {
    private static final Logger log = LoggerFactory.getLogger(SftpClient.class);
    private static String host;
    private static int port;
    private static String remoteUserDir;
    private static String userName;
    private static String password;
    private static String remoteUpload;
    private static String remoteDownload;
    private static String localUpload;
    private static String localDownload;
    private static String localRootDir;
    private static String WEBINFPath;

    static {
        WEBINFPath = SftpClient.class.getResource("").getPath();
        WEBINFPath = WEBINFPath.substring(0, WEBINFPath.lastIndexOf("classes"));
        String propPath = SftpClient.class.getResource("/sftp.properties").getFile();
        log.info("FTP信息配置得位置:[{}]", propPath);
        File file = new File(propPath);
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("sftp信息配置文件不存在,当前获取到的路径为[{}],以下为异常堆栈信息：", propPath, e);
        }
        Properties properties = new Properties();
        try {
            properties.load(inStream);
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port", "22"));
            userName = properties.getProperty("username");
            password = properties.getProperty("password");
            remoteUpload = properties.getProperty("remoteUpload");
            remoteDownload = properties.getProperty("remoteDownload");
            localUpload = properties.getProperty("localUpload");
            localDownload = properties.getProperty("localDownload");
            remoteUserDir = properties.getProperty("remoteUserDir");
            localRootDir = properties.getProperty("localRootDir");
        } catch (IOException e) {
            log.error("加载properties异常:", e);
        }
    }

    public SftpClient() {
        super();
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        SftpClient.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        SftpClient.port = port;
    }

    public static String getRemoteUserDir() {
        return remoteUserDir;
    }

    public static void setRemoteUserDir(String remoteUserDir) {
        SftpClient.remoteUserDir = remoteUserDir;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        SftpClient.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SftpClient.password = password;
    }

    public static String getRemoteUpload() {
        return remoteUpload;
    }

    public static void setRemoteUpload(String remoteUpload) {
        SftpClient.remoteUpload = remoteUpload;
    }

    public static String getRemoteDownload() {
        return remoteDownload;
    }

    public static void setRemoteDownload(String remoteDownload) {
        SftpClient.remoteDownload = remoteDownload;
    }

    public static String getLocalUpload() {
        return localUpload;
    }

    public static void setLocalUpload(String localUpload) {
        SftpClient.localUpload = localUpload;
    }

    public static String getLocalDownload() {
        return localDownload;
    }

    public static void setLocalDownload(String localDownload) {
        SftpClient.localDownload = localDownload;
    }

    public static String getLocalRootDir() {
        return localRootDir;
    }

    public static void setLocalRootDir(String localRootDir) {
        SftpClient.localRootDir = localRootDir;
    }

    public static String getWEBINFPath() {
        return WEBINFPath;
    }

    public boolean uploadFile(File file) {
        log.info("文件上传,[{}]", file);
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(userName, host, port);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no"); /* 接受所有key */
            session.setConfig(config);
            session.connect();
            log.info("SSH login success.");
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            log.info("sftp channel create success.");

            log.debug("当前本地路径[{}]", channelSftp.lpwd());
            log.debug("当前远端路径[{}]", channelSftp.pwd());
            try {
                log.info("sftp切换到上传目录");
                channelSftp.cd(remoteUpload);
            } catch (Exception e) {
                log.info("{}目录不存在，创建", remoteUpload);
                channelSftp.mkdir(remoteUpload);
                channelSftp.cd(remoteUpload);
                log.info("sftp切换到创建的上传目录");
            }
            channelSftp.put(new FileInputStream(file), file.getName());
            log.info("文件{}传输成功", file.getPath());
            System.out.println("getLongname() \n" + channelSftp.pwd());
            return true;
        } catch (Exception ex) {
            log.error("上传文件异常.", ex);
            return false;
        } finally {
            /* 操作完毕后，关闭通道并退出本次会话 */
            channelSftp.exit();
            log.info("sftp Channel exited.");

            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
                log.info("channelSftp disconnected.");
            }

            if (session != null && session.isConnected()) {
                session.disconnect();
                log.info("Host Session disconnected.");
            }
        }
    }

    /**
     * 上传localUpload目录下名为 [String fileName]的文件,<br/>
     * 此方法需先创建本地文件目录
     */
    public boolean uploadFile(String fileName) {
        log.info("文件上传,文件名为[{}]", fileName);
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(userName, host, port);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no"); /* 接受所有key */
            session.setConfig(config);
            session.connect();
            log.info("SSH login success.");
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            log.info("sftp channel create success.");

            log.debug("当前本地路径[{}]", channelSftp.lpwd());
            log.debug("当前远端路径[{}]", channelSftp.pwd());
            try {
                log.info("sftp切换到上传目录");
                channelSftp.cd(remoteUpload);
            } catch (Exception e) {
                log.info("{}目录不存在，创建", remoteUpload);
                channelSftp.mkdir(remoteUpload);
                channelSftp.cd(remoteUpload);
                log.info("sftp切换到创建的上传目录");
            }
            String filePath = WEBINFPath + File.separator + localRootDir + File.separator + localUpload + File.separator
                    + fileName;
            File file = new File(filePath);
            log.info("上传文件的路径{},文件名{}", file.getPath(), file.getName());

            channelSftp.put(new FileInputStream(file), file.getName());
            log.info("文件{}传输成功", file.getPath());
            System.out.println("getLongname() \n" + channelSftp.pwd());
            return true;
        } catch (Exception ex) {
            log.error("上传文件异常.", ex);
            return false;
        } finally {
            /* 操作完毕后，关闭通道并退出本次会话 */
            channelSftp.exit();
            log.info("sftp Channel exited.");

            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
                log.info("channelSftp disconnected.");
            }

            if (session != null && session.isConnected()) {
                session.disconnect();
                log.info("Host Session disconnected.");
            }
        }
    }

    public boolean downloadFile(File file) {

        return false;
    }

    /**
     * 下载文件中的所有csv文件，并删除远端文件
     */
    public boolean downloadDir() {
        log.info("登录信息,用户名：{},密码：{},端口号：{}", userName, password, port);
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(userName, host, port);
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
                channelSftp.cd(remoteDownload);
            } catch (Exception e) {
                log.error("华云天下导出文件目录不存在", e);
                return false;
            }
            log.debug(channelSftp.pwd());

            /* 本地文件目录 */
            File downloadDir = new File(WEBINFPath + File.separator + localRootDir + File.separator + localDownload);
            if (!downloadDir.exists()) {
                downloadDir.mkdirs();
            }

            // 列出服务器指定的文件列表
            Vector<?> v = channelSftp.ls("*.csv");
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
                            channelSftp.get(lsEntry.getFilename(), downloadDir.getPath());
                            log.info("下载文件{}，到{}", lsEntry.getFilename(), downloadDir.getPath());
                            channelSftp.rm(lsEntry.getFilename());
                            log.info("删除远程文件{}", lsEntry.getFilename());
                        } catch (Exception e) {
                            log.error("下载文件{}失败", lsEntry.getFilename());
                        }
                    }

                }
            }
            return true;
        } catch (Exception ex) {
            log.error("Exception found while tranfer the response.", ex);
            return false;
        } finally {
            /* 操作完毕后，关闭通道并退出本次会话 */
            channelSftp.exit();
            log.info("sftp Channel exited.");

            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
                log.info("channelSftp disconnected.");
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
                log.info("Host Session disconnected.");
            }
        }
    }

    public List<File> lsFiles(File file) {
        return null;
    }

}