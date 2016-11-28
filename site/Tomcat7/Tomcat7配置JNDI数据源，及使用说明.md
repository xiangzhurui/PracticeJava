# Tomcat7配置说明

## JNDI数据源，及使用
1. 参考与本文件同目录下的`context.xml`修改Tomcat配置文件`conf\context.xml`(增加<Resouce/>元素)
2. 调用时jdniName前需添加`java:comp/evn/`

