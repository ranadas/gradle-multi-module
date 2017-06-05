package com.rdas;

import com.corundumstudio.socketio.SocketIOServer;
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


    @Autowired
    private SocketIOServer server;

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(DbServerApplication.class, args);

        System.out.println("\n\n  ** Starting database server. ** \n\n");

        ConfigurableApplicationContext ctx = SpringApplication.run(DbServerApplication.class, args);
        DbServerApplication mainObj = ctx.getBean(DbServerApplication.class);
        mainObj.init();

        System.out.println("DbServer Application exited");
    }

    public void init() throws Exception {
        System.out.println("\n\n\ninside init method, starting socketio server");
        server.start();
    }
}
