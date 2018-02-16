package pers.hdh.webservice;

/**
 * @ClassName	WeatherImpl	
 * @Description:TODO
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/16 09:49:42
 */
public class WeatherImpl implements IWeather {

	@Override
	public String getWeatherInfo(String location) {
//		随便实现一下
		if ("广州".equals(location)) return "晴";
		else if ("深圳".equals(location)) return "多云";
		else return "信息查询能力有限，查询不到您所在地区天气情况";
	}
}
