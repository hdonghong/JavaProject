package pers.hdh.webservice.invoking;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import pers.hdh.webservice.IWeather;

/**
 * @ClassName	WeatherInvokingClient	
 * @Description
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/16 10:41:34
 */
public class WeatherInvokingClient {

	public static void main(String[] args) {
		// 1.创建客户端代理工厂
		JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
		
		// 2.设置服务端的访问地址
		client.setAddress("http://localhost:23333/weather?wsdl");
		
		// 3.设置portType的class，服务端的接口
		client.setServiceClass(IWeather.class);
		
		// 4.获取portType
		IWeather weatherService = (IWeather)client.create();
		
		// 5.调用远程方法
		System.out.println(weatherService.getWeatherInfo("广州"));
	}
}
