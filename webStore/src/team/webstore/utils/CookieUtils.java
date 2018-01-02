package team.webstore.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	/**
	 * 通过名称在cookie数组获取指定的cookie
	 * @param name cookie名称
	 * @param cookies cookie数组
	 * @return 返回指定的cookie
	 */
	public static Cookie getCookieByName(String name, Cookie[] cookies) {
		if(cookies!=null){
			for (Cookie c : cookies) {
				//通过名称获取
				if(name.equals(c.getName())){
					//返回
					return c;
				}
			}
		}
		return null;
	}
	
	/**
	 * 将cookie按照指定的时间通过response保存到指定的路径下
	 * @param cookie 目标cookie
	 * @param uri 保存的路径
	 * @param expiry 生存时间
	 * @param response 通过response写到浏览器
	 */
	public static void addCookie(Cookie cookie, String uri, int expiry, HttpServletResponse response) {
		cookie.setPath(uri);
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
	}
	
	
}
