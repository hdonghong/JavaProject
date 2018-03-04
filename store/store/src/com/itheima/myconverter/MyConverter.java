package com.itheima.myconverter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class MyConverter implements Converter {

	@Override
	public Object convert(Class arg0, Object arg1) {
		//将arg1从Object类型转成Date类型
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse((String)arg1);
			return date;
		} catch (Exception e) {
			
		}
		return null;
	}

}
