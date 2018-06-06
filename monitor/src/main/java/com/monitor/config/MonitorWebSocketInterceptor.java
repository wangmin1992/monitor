package com.monitor.config;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @ClassName: MonitorWebSocketInterceptor.java
 * @Description: web socket请求拦截器实现；
 * @author wangmin
 * @date 2018年6月5日-上午9:52:28
 * @version 1.0V
 */
public class MonitorWebSocketInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest)request;
			attributes.put("WEBSOCKET_USERID", servletServerHttpRequest.getServletRequest().getSession().getId());
			attributes.put("USER_SESSION", servletServerHttpRequest.getServletRequest().getSession());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		
	}

}
