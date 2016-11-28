# MyBatis 与 Spring 整合配置说明

* 自动`<mybatis-spring:scan` 或 `MapperScannerConfigurer` bean自动扫描的包`basePackage`是Java接口，不是xml文件
Spring Context配置文件命名空间`xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"` 加 `<mybatis-spring:scan base-package="framework.mybatis" />`配置等效于
```xml
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
  <property name="basePackage" value="framework.mybatis"/>
  <property name="sqlSessionFactoryBeanName" value = "sqlSessionFactory"/>
</bean>
```
* `sqlMap`映射xml文件也需要扫描配置在`sqlSessionFactory` bean的 `mapperLocations` 属性。
```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="dataSource" ref="dataSource"></property>
  <property name="mapperLocations" value="classpath:framework/mybatis/dao/mapper/*.xml"/>
</bean>
```
