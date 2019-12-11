package com.mxz.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
*@Description
*@autor:mxz
*2018-07-29
**/
//@Configuration
//@MapperScan(package路径)
public class SqlSessionFactoryConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().addScripts("执行的sql").build();
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		return sessionFactoryBean.getObject();
	}
}
