package com.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.monitor.webapi.WebSocketController;

/**
 * @ClassName: MonitorWebSocketConfiguration.java
 * @Description: web socket自定义配置;
 * @author wangmin
 * @date 2018年6月5日-上午9:48:58
 * @version 1.0V
 */
@Configuration
@EnableWebSocket
public class MonitorWebSocketConfiguration implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/web")
			.setAllowedOrigins("*")
			.addInterceptors(new MonitorWebSocketInterceptor());
	}
	
	@Bean
	public WebSocketHandler webSocketHandler () {
		return new WebSocketController();
	}

}
