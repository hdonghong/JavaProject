package pers.hdh.webservice;

import javax.jws.WebService;

/**
 * @ClassName	IWeather	
 * @Description:TODO
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/16 09:47:54
 */
@WebService          // 作为WebService的标识
public interface IWeather {

	/**
	 * 获取本地天气信息
	 * @param location 当地地址
	 * @return
	 */
	String getWeatherInfo(String location);
}
