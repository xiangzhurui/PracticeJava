package com.xiangzhurui.sample.java.jdbc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiangzhurui
 * @version 2019-04-17 10:15
 */
public class JdbcUrlTemplate {
    /**
     * jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
     * example:
     * <pre>
     *     jdbc:sqlserver://localhost;user=MyUserName;password=*****;
     * </pre>
     *
     * @return
     */
    public static String getSQLServerUrl(String serverName, String instanceName, Integer portNumber, Map<String, String> propertyMap) {
        String s = "jdbc:sqlserver://" + serverName + "/" + instanceName + ":" + portNumber;
        if (propertyMap != null && propertyMap.size() > 0) {
            for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                s += ";" + entry.getKey() + "=" + entry.getValue();
            }
        }
        return s;
    }

    /**
     * jdbc:mysql://host1:33060/sakila
     * jdbc.url=jdbc:mysql://localhost:3306/demo?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true
     *
     * @return
     */
    public static String getMySQLJdbcUrl(String host, Integer port, String schema, Map<String, String> propertyMap) {
        String s = "jdbc:mysql://" + host + ":" + port + "/" + schema;
        if (propertyMap != null && propertyMap.size() > 0) {
            s += "?";
            for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                s += entry.getKey() + "=" + entry.getValue() + "&";
            }
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getSQLServerUrl("abc", "testInstance", 124, null));
        HashMap<String, String> propertyMap = new LinkedHashMap<String, String>() {{
            put("useSSL", "false");
            put("characterEncoding", "utf8");
            put("serverTimezone", "Asia/Shanghai");
            put("allowMultiQueries", "true");
        }};

        System.out.println(getMySQLJdbcUrl("localhost", 3306, "demo", propertyMap));
    }
}
