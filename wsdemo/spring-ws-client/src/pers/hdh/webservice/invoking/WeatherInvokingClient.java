package pers.hdh.webservice.invoking;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 1.获取portType
		IWeather weatherService = (IWeather)context.getBean("weatherService");
		
		// 2.调用远程方法
		System.out.println(weatherService.getWeatherInfo("深圳"));
	}
}
