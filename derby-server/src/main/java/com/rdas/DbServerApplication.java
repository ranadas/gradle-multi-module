package com.rdas;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

/**
 * Created by X148128 on 01/06/2017.
 * http://blog.jelastic.com/2017/04/27/hosting-spring-boot-java-applications/
 * https://stackoverflow.com/questions/26105061/spring-boot-without-the-web-server
 */
@SpringBootApplication
public class DbServerApplication {
    private static final String PORT = "8081";

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(DbServerApplication.class, args);

        System.out.println("\n\n  ** Starting database server. ** \n\n");

        ConfigurableApplicationContext ctx = SpringApplication.run(DbServerApplication.class, args);
        DbServerApplication mainObj = ctx.getBean(DbServerApplication.class);
        mainObj.init();

        System.out.println("DbServer Application exited");
    }

    public void init() throws Exception {
        System.out.println("\n\n\ninside init method");
		startTomcat();
    }

    private void startTomcat() throws Exception {
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = PORT + "";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("\n---> Configuring With basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}
