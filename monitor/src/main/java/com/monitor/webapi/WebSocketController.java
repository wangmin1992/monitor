package com.monitor.webapi;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @ClassName: WebSocketController.java
 * @Description: web socket请求controller;
 * @author wangmin
 * @date 2018年6月5日-上午9:56:10
 * @version 1.0V
 */
public class WebSocketController extends TextWebSocketHandler {

	/**
	 * 请求发送消息的时候，调用该方法；
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		sendMessage(msg, session);
	}
	
	public void sendMessage (String msg, WebSocketSession session) {
		try {
			if (session != null) {
				for (int i = 0 ; i < 100 ; i++) {
					session.sendMessage(new TextMessage("第" + i + "次回复！"));
					Thread.sleep(5000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
