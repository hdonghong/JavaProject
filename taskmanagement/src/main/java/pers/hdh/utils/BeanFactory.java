package pers.hdh.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 工厂类
 */
public class BeanFactory {
    public static Object getBean(String name) {
        try {
            // 获取文档对象
            Document doc = new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("mybeans.xml"));
            // 获取指定的元素
            Element e = (Element) doc.selectSingleNode("//bean[@id='"+name +"']");
            // 获取值
            String value = e.attributeValue("class");
            return Class.forName(value).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
