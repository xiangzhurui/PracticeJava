package personal.xzr.practice.util;

import javax.sql.DataSource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiangzhurui
 * @version 2018/6/16 22:21
 */
@Slf4j
public class DruidDataSourceSingleton {

    @Getter
    private DataSource dataSource = null;


    private DruidDataSourceSingleton() {
        loadDatasource();
    }

    private static class SingletonHolder {

        private static final DruidDataSourceSingleton druidConnection = new DruidDataSourceSingleton();

    }

    /**
     * 加载数据源
     *
     * @return
     */
    private void loadDatasource() {
        try {
            // 1.加载properties文件
            InputStream is = DruidDataSourceSingleton.class.getClassLoader().getResourceAsStream("META-INF/conf/druid.properties");
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
     * 用单例模式确保同一个类加载器只返回一个链接对象
     *
     * @return
     */
    public static final DruidDataSourceSingleton getInstace() {
        return SingletonHolder.druidConnection;
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


    public void release(Connection connection) {
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

    public static void main(String[] args) {
        System.out.println(new BigDecimal("11.0").stripTrailingZeros());
        System.out.println(new BigDecimal("11.10").stripTrailingZeros().toString());
    }

}
