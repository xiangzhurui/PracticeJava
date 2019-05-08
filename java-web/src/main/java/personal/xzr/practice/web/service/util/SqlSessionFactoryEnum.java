package personal.xzr.practice.web.service.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 示例：mybatis 单独使用的工具类
 *
 * @author XZR
 * @version 2016.07.28
 */
public enum SqlSessionFactoryEnum {
    INSTANCE;

    private SqlSessionFactory factory;

    SqlSessionFactoryEnum() {
        System.out.println(System.currentTimeMillis());
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(inputStream);
    }

    /**
     * 获取 SqlSessionFactory 实例
     *
     * @return 返回 SqlSessionFactory 的唯一实例
     */
    public SqlSessionFactory getFactory() {
        return factory;
    }
}
