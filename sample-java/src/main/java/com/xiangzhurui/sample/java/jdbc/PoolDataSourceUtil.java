package com.xiangzhurui.sample.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用dbcp2数据库连接池的工具类
 * 
 * @author XiangZhuRui
 *
 */
public class PoolDataSourceUtil {

    public static void main(String[] args) {

    }

    public <T> T select(String sql, T T) {
        Connection conn = JdbcDataSourceUtil.getConnection();
        T t = null;
        try {
            t = (T) T.getClass().newInstance();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

}
