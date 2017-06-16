package com.rdas.jmsa.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import java.net.URI;

//import javax.jms.ConnectionFactory;

/**
 * Created by x148128 on 30/05/2017.
 * https://turreta.com/2017/01/26/spring-boot-embedded-activemq/
 * https://github.com/Turreta/Spring-Boot-Embedded-JMS
 *
 *
 * http://www.devglan.com/spring-boot/spring-boot-jms-activemq-example
 * http://www.javainuse.com/spring/sprboot_activemq
 *
 * https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-activemq/pom.xml
 */
@EnableJms
@Configuration
public class JmsConfiguration {
//    private static final String JMS_BROKER_URL = "vm://embedded?broker.persistent=false,useShutdownHook=false";
//    @Bean
//    public ConnectionFactory connectionFactory() {
//    }

    //@Value("spring.activemq.broker-url")
    private String BROKER_URL = "tcp://localhost:61616";

    //@Value("spring.activemq.user")
    private String BROKER_USERNAME = "admin";

    //@Value("spring.activemq.password")
    private String BROKER_PASSWORD = "admin";

    public static final String DB_QUEUE = "db_queue";
    public static final String FILE_PROCESS_QUEUE = "file_process_queue";
    public static final String MACHINE_LEARNING_QUEUE = "ml_queue";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUserName(BROKER_USERNAME);
        connectionFactory.setPassword(BROKER_PASSWORD);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestinationName(DB_QUEUE);
        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }

    /**
     * This represents a JMS server. In this case, this is an embedded JMS server.
     * In production, perhaps connect to external JMS servers.
     *
     * @return
     * @throws Exception
     */
    @Bean
    public BrokerService createBrokerService() throws Exception {
        BrokerService broker = new BrokerService();
        TransportConnector connector = new TransportConnector();
        connector.setUri(new URI(BROKER_URL));
        broker.addConnector(connector);
        return broker;
    }
}
