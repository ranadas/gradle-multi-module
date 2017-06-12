package com.rdas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by rdas on 05/06/2017.
 */
@Configuration
public class DerbyDbConfig {
	@Primary
	@Bean(name = "dataSource", destroyMethod = "")
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.DERBY)
				.addScript("create_tables.sql")
				.build();
		return db;
	}


	@Bean(name = "derbyDataSource", destroyMethod = "")
	public DataSource derbyDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		dataSource.setUrl("jdbc:derby:temp\\database\\test01;create=true");
		dataSource.setUsername("");
		dataSource.setPassword("");
		return dataSource;
	}
}
