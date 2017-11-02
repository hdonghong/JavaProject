package pers.hdh.web.socket;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class SocketConfig implements ServerApplicationConfig {

	/**
	 * 注解方式启动
	 * 自动执行
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		// TODO Auto-generated method stub
		//System.out.println("scanned..." + scanned.size());
		
		// 返回set，提供了过滤的作用！
		return scanned;
	}

	/**
	 * 接口方式启动
	 */
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
