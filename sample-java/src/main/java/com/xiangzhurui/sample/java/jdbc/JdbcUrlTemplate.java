package com.xiangzhurui.sample.java.jdbc;

import java.util.Map;

/**
 * @author xiangzhurui
 * @version 2019-04-17 10:15
 */
public class JdbcUrlTemplate {
    /**
     * jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
     *
     * @return
     */
    public static String getSQLServerUrl(String serverName, String instanceName, Integer portNumber, Map<String, String> propertyMap) {
        String s = "jdbc:sqlserver://" + serverName + "\\" + instanceName + ":" + String.valueOf(portNumber);
        for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
            s += ";" + entry.getKey() + "=" + entry.getValue();
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getSQLServerUrl("abc","testInstance",124,null));
    }
}
