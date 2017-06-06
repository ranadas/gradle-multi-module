package com.rdas.dbaccess;

import com.rdas.DbServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;


/**
 * Created by rdas on 06/06/2017.
 */
@ContextConfiguration(classes = DbServerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDerbyConnection {

    @Autowired
    private DataSource derbyDB;


    @Test
    public void testConnectionIsValid() {
        System.out.println(derbyDB);
    }


    @TestPropertySource(
            properties = {
                    "spring.datasource.driverClassName=org.apache.derby.jdbc.EmbeddedDriver",
                    "spring.datasource.urljdbc:derby:target/database/message;create=true",
                    "spring.datasource.username=app",
                    "spring.datasource.password=app"
            }
    )
    @Configuration
    static class TestConfig {
        @Bean(name = "mysqlJdbcTemplate")
        public JdbcTemplate jdbcTemplate(@Qualifier("derbyDB") DataSource dsMySQL) {
            return new JdbcTemplate(dsMySQL);
        }


        @Bean(name = "derbyDB")
        //@ConfigurationProperties(prefix = "spring.ds_mysql")
        public DataSource mysqlDataSource() {
            return (DataSource) DataSourceBuilder.create().build();
        }
    }


}
