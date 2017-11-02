package pers.hdh.web.socket;

import pers.hdh.beans.User;
import pers.hdh.service.RecordService;
import pers.hdh.utils.BeanFactory;

import java.io.IOException;
import java.sql.SQLException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class NewsSocket {
	
	// 多例的
	public NewsSocket() {
		super();
		// System.out.println("echosocket");
	}

	@OnOpen
	public void open(Session session) {
		// 一个Session代表一个通信会话
		// System.out.println("sessionid: "+session.getId());
	}
	
	@OnClose
	public void close(Session session) {
		//System.out.println("session:"+session.getId()+"is closed");
	}
	
	@OnMessage
	public void getNews(Session session, String uid) throws SQLException {

		// 封装数据
		String news = "没有新消息";
		User user = new User();
		user.setUid(uid);

		// 处理业务逻辑
		RecordService service = (RecordService) BeanFactory.getBean("RecordService");
		int count = service.getNews(user);
		if (count > 0) {
			news = "您有"+count+"条新消息";
		}

		try {
			session.getBasicRemote().sendText(news);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
