package com.rdas.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
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


    //@Bean(name = "derbyServerDataSource", destroyMethod = "")
    public DataSource derbyServerDataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
//		dataSource.setUrl("jdbc:derby:temp\\database\\test01;create=true");
//		dataSource.setUsername("");
//		dataSource.setPassword("");
//		return dataSource;
        return DataSourceBuilder
                .create()
//                .url("jdbc:h2:D:/work/workspace/fork/gs-serving-web-content/initial/data/fdata;DATABASE_TO_UPPER=false")
                //.username("derbyuser")
                //.password("derbypass")
                .driverClassName("org.apache.derby.jdbc.EmbeddedDriver")
                .build();
    }

    //@Bean(name = "derbyClientDataSource", destroyMethod = "")
    public DataSource derbyClientDataSource() {
/*
spring.datasource.url=jdbc:derby://10.12.1.1:1527/example1;create=true
spring.datasource.username=test1
spring.datasource.password=pass1
spring.datasource.driver-class-name=org.apache.derby.jdbc.ClientDriver
 */
        return DataSourceBuilder
                .create()
                .build();
    }
}
