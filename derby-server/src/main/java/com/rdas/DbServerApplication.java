package com.rdas;

import com.corundumstudio.socketio.SocketIOServer;
import com.github.tomaslanger.chalk.Chalk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by X148128 on 01/06/2017.
 * http://blog.jelastic.com/2017/04/27/hosting-spring-boot-java-applications/
 * https://stackoverflow.com/questions/26105061/spring-boot-without-the-web-server
 */
@SpringBootApplication
public class DbServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(DbServerApplication.class);
    @Autowired
    private SocketIOServer server;

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(DbServerApplication.class, args);
        logger.info(""+Chalk.on("\n" +
                "** Starting database server. ** \n").green());
        ConfigurableApplicationContext ctx = SpringApplication.run(DbServerApplication.class, args);
        DbServerApplication mainObj = ctx.getBean(DbServerApplication.class);
        mainObj.init();

        logger.info(""+Chalk.on("DbServer Application started").green().underline());
    }

    public void init() throws Exception {
        logger.info(""+Chalk.on("\nin DbServerApplication init, starting socketio server..\n").blue().underline());
        server.start();
    }
}
