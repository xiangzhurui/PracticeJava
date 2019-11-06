package com.xiangzhurui.core.java.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Properties文件读取示例
 * 
 * @author XiangZhuRui
 *
 */
public class PropertiesUtil {
    static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    private Properties  properties;

    public PropertiesUtil(String filePath) {
        super();
        InputStream inStream = null;
        Properties p = new Properties();
        try {
            log.info("当前读取的properties文件位于[{}]", filePath);
            inStream = new FileInputStream(filePath);
            p = new Properties();
            p.load(inStream);
            log.info("数据配置属性加载成功。");
        } catch (FileNotFoundException e) {
            log.error("文件不存在。", e);
        } catch (IOException e) {
            log.error("加载properties文件失败。", e);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                    log.info("文件输入流关闭成功。");
                } catch (IOException e) {
                    log.error("文件输入流关闭失败。", e);
                }
            }
        }
        this.properties = p;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
