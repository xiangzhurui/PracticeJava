package com.xiangzhurui.core.util;

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
 */
public class PropertiesLoader {

  private static final Logger log = LoggerFactory.getLogger(PropertiesLoader.class);

  public static Properties loadPath(String filePath) {
    InputStream inStream = null;
    Properties properties = new Properties();
    try {
      log.info("当前读取的properties文件位于[{}]", filePath);
      inStream = new FileInputStream(filePath);
      properties = new Properties();
      properties.load(inStream);
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
    return properties;
  }


}
