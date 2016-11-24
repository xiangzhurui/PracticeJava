# Tomcat7配置JNDI数据源，及使用说明

1. 参考与本文件同目录下的`context.xml`修改Tomcat配置文件`conf\context.xml`(增加<Resouce/>元素)
2. 类`com.sinosoft.utility.JdbcUrl`的方法`public JdbcUrl()`里使用以下代码填充。(调用时jdniName前需添加`java:comp/evn/`)
```
DBType = "COMMONSDBCP";
DBName = "java:comp/env/jdbc/sts_db";
```