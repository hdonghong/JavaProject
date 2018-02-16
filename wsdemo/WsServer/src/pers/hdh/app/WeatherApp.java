package pers.hdh.app;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import pers.hdh.webservice.WeatherImpl;

/**
 * @ClassName	WeatherApp	
 * @Description:TODO
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/16 09:56:03
 */
public class WeatherApp {

	public static void main(String[] args) {
		// 1.JAXWS规范的服务工厂bean
		JaxWsServerFactoryBean bean = new JaxWsServerFactoryBean();
		// 2.设置发布地址，端口号别写0-1023，选择5位数的最好
		bean.setAddress("http://localhost:23333/weather");
		// 3.设置服务类
		bean.setServiceBean(new WeatherImpl());
		// 4.发布
		bean.create();
	}
}
