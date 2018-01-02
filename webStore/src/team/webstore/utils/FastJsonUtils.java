package team.webstore.utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonUtils {
	
	/**
	 * 将对象转成json串
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object){
		//DisableCircularReferenceDetect来禁止循环引用检测
		return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
	}
	//输出json
	public static void write_json(HttpServletResponse response,String jsonString)
	{
		response.setContentType("application/json;utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/**
	 * ajax提交后回调的json字符串
	 * @return
	 */
	public static String ajaxResult(boolean success,String message)
	{
		Map<String, Object> map=new HashMap<>();
		map.put("success", success);//是否成功
		map.put("message", message);//文本消息
		String json= JSON.toJSONString(map);		
		return json;
	}
	

	/**
	 * JSON串自动加前缀
	 * @param json 原json字符串
	 * @param prefix 前缀
	 * @return 加前缀后的字符串
	 */

	public static String JsonFormatterAddPrefix(String json,String prefix,Map<String,Object> newmap)
	{
		if(newmap == null){
			newmap = new HashMap<>();
		}
		Map<String,Object> map = (Map<String, Object>) JSON.parse(json);

		for(String key:map.keySet())
		{
			Object object=map.get(key);
			if(isEntity(object)){
				String jsonString = JSON.toJSONString(object);
				JsonFormatterAddPrefix(jsonString,prefix+key+".",newmap);
				
			}else{
				newmap.put(prefix+key, object);
			}
			
		}
		return JSON.toJSONString(newmap);		
	}
	/**
	 * 判断某对象是不是实体
	 * @param object
	 * @return
	 */
	private static boolean isEntity(Object object)
	{
		if(object instanceof String  )
		{
			return false;
		}
		if(object instanceof Integer  )
		{
			return false;
		}
		if(object instanceof Long  )
		{
			return false;
		}
		if(object instanceof java.math.BigDecimal  )
		{
			return false;
		}
		if(object instanceof Date  )
		{
			return false;
		}
		if(object instanceof java.util.Collection )
		{
			return false;
		}
		return true;
		
	}
}
