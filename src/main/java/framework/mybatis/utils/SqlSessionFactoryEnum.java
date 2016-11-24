package framework.mybatis.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public enum SqlSessionFactoryEnum {
	INSTANCE;
	private SqlSessionFactory factory;

	private SqlSessionFactoryEnum() {
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public SqlSessionFactory getFactory() {
		return factory;
	}
}
