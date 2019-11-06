package com.xiangzhurui.core.java.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取JDBC连接工具类
 * @author XiangZhuRui
 *
 */
public class JdbcDataSourceUtil {
	private static String	driverClassName;
	private static String	url;
	private static String	username;
	private static String	password;

	public static Connection getConnection(String driverClassName, String url, String username, String password) {
		Connection conn = null;
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection() {
		setJdbcProperties();
		Connection conn = null;
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private static void setJdbcProperties() {
		File file = new File(JdbcDataSourceUtil.class.getResource("/jdbc.properites").getFile());
		InputStream inStream = null;
		Properties p = new Properties();
		try {
			inStream = new FileInputStream(file);
			p.load(inStream);
			driverClassName = p.getProperty("jdbc.driverClassName");
			username = p.getProperty("jdbc.username");
			url = p.getProperty("jdbc.url");
			password = p.getProperty("jdbc.password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			p.clone();
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
