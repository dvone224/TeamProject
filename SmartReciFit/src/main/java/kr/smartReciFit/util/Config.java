package kr.smartReciFit.util;


import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Config {
	private static SqlSessionFactory sqlSessionFactory;
	static {

		try {
			String resource = "kr/smartReciFit/mybatis/config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
		
	}
}
