package com.rdas.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rdas on 05/06/2017.
 */
@Configuration
public class NettyServerConfig {
    private static final String HOST = "localhost";
    private static final int PORT = 9092;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(HOST);
        config.setPort(PORT);
        return new SocketIOServer(config);
    }
}
