package com.itheima.utils;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 工厂实体
 * @author Lenovo
 *
 */
public class BeanFactory {
	/**
	 * 通过id获取一个指定的实现类
	 * @param id
	 * @return
	 */
	public static Object getBean(String id) {
		InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
		try {
			//1.获取document对象
			Document doc = new SAXReader().read(is);
			//2.获取指定的bean对象
			Element e = (Element) doc.selectSingleNode("//bean[@id='"+id+"']");
			//3.获取bean对象的class属性
			String value = e.attributeValue("class");
			//4.反射
			return Class.forName(value).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
