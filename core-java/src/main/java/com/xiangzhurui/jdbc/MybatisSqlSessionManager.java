package com.xiangzhurui.jdbc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * mybatis 配置加载
 *
 * @author xiangzhurui
 */
@Slf4j
public class MybatisSqlSessionManager {

  private SqlSessionFactory sqlSessionFactory;

  private DataSource dataSource = DruidDataSourceManager.getInstace().getDataSource();

  private MybatisSqlSessionManager() {
    loadSqlSessionFactory();
  }

  public static MybatisSqlSessionManager getInstance() {
    return SingletonHolder.INSTANCE;
  }

  private void loadSqlSessionFactory() {

    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);
    configuration.setLazyLoadingEnabled(true);
    configuration.setLogImpl(org.apache.ibatis.logging.slf4j.Slf4jImpl.class);
    configuration.setCallSettersOnNulls(true);
    Set<String> strings = scanSqlMapXml("META-INF/mybatis/sqlMap");
    strings.forEach(xmlMapperResource -> {
                      try (InputStream resourceAsStream = Resources.getResourceAsStream(xmlMapperResource)) {
                        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resourceAsStream,
                                                                                 configuration,
                                                                                 xmlMapperResource,
                                                                                 configuration.getSqlFragments());
                        xmlMapperBuilder.parse();
                      } catch (IOException e) {
                        log.error("", e);
                      }
                    }
    );

    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);

    this.sqlSessionFactory = factory;
  }

  private Set<String> scanSqlMapXml(String sqlMapDir) {
    try {
      File resourceAsFile = Resources.getResourceAsFile(sqlMapDir);
      if (!resourceAsFile.exists()) {
        return null;
      }
      Path dir = resourceAsFile.toPath();
      Set<String> collect = Files.list(dir)
          .map(path -> {
            String s = path.toString();
            return s.substring(s.lastIndexOf(sqlMapDir));
          })
          .filter(s -> Objects.nonNull(s) && s.endsWith(".xml"))
          .collect(Collectors.toSet());
      return collect;
    } catch (IOException e) {
      log.error("", e);
    }
    return null;
  }

  public SqlSessionFactory getSqlSessionFactory() {
    return this.sqlSessionFactory;
  }

  public static SqlSession openSession() {
    return openSession(true);
  }

  public static SqlSession openSession(boolean autoCommit) {
    SqlSessionFactory sqlSessionFactory = getInstance().getSqlSessionFactory();
    return sqlSessionFactory.openSession(autoCommit);
  }

  public static <T> T getMapper(Class<T> tClass) {
    T mapper = openSession().getMapper(tClass);
    return mapper;
  }

  private static class SingletonHolder {

    private final static MybatisSqlSessionManager INSTANCE = new MybatisSqlSessionManager();
  }

}
