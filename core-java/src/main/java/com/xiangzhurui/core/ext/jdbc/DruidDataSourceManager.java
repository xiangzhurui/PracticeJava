package com.xiangzhurui.core.ext.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiangzhurui
 */
@Slf4j
public class DruidDataSourceManager {

  private DataSource dataSource = null;


  private DruidDataSourceManager() {
    loadDatasource();
  }

  /**
   * 用单例模式确保同一个类加载器只返回一个链接对象
   *
   * @return
   */
  public static DruidDataSourceManager getInstace() {
    return SingletonHolder.druidConnection;
  }

  /**
   * 释放资源
   *
   * @param resultSet
   * @param statement
   * @param connection
   */
  public static void close(ResultSet resultSet, Statement statement, Connection connection) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        throw new RuntimeException("结果集关闭异常", e);
      }
    }
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        throw new RuntimeException("pstm关闭异常", e);
      }
    }
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new RuntimeException("数据库连接关闭异常", e);
      }
    }
  }

  /**
   * 加载数据源
   *
   * @return
   */
  private void loadDatasource() {
    try {
      // 1.加载properties文件
      InputStream is = DruidDataSourceManager.class.getResourceAsStream("/META-INF/properties/druid.properties");
      Properties properties = new Properties();

      // 2.加载输入流
      properties.load(is);

      // 3.获取数据源
      dataSource = DruidDataSourceFactory.createDataSource(properties);
    } catch (Exception e) {
      log.error("加载数据源失败。", e);
    }
  }

  /**
   * 返回一个数据源
   *
   * @return
   */
  public DataSource getDataSource() {
    return this.dataSource;
  }

  /**
   * 返回一个链接
   *
   * @return
   */
  public Connection getConnection() {
    Connection connection = null;
    try {
      connection = this.dataSource.getConnection();
    } catch (SQLException e) {
      log.error("获取数据库连接失败。", e);
    }
    return connection;
  }

  private static class SingletonHolder {

    private static final DruidDataSourceManager druidConnection = new DruidDataSourceManager();

  }

}
