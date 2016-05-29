package com.zheng.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcTemplateUtils {
	
	private static final String KEY_DRIVER_CLASS_NAME = "jdbc.driverClassName";
	private static final String KEY_URL = "jdbc.url";
	private static final String KEY_USERNAME = "jdbc.username";
	private static final String KEY_PASSWORD = "jdbc.password";
	
	

	private static JdbcTemplate jdbcTemplate;
	
	public static JdbcTemplate getJdbcTemplate() {
		if(jdbcTemplate == null) {
			jdbcTemplate = createJdbcTemplate();
		}
		
		return jdbcTemplate;
	}

	private static JdbcTemplate createJdbcTemplate() {
		Properties prop = new Properties();
		DruidDataSource ds = null;
		try {
			prop.load(JdbcTemplateUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			ds = new DruidDataSource();
			ds.setDriverClassName(prop.getProperty(KEY_DRIVER_CLASS_NAME));
			ds.setUrl(prop.getProperty(KEY_URL));
			ds.setUsername(prop.getProperty(KEY_USERNAME));
			ds.setPassword(prop.getProperty(KEY_PASSWORD));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new JdbcTemplate(ds);
	}
}
