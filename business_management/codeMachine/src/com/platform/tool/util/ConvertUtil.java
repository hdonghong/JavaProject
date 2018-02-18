package com.platform.tool.util;

import java.sql.Types;
import java.util.Date;

import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.oracore.OracleType;

/**
 * @Description:
 * @Author:		传智播客 java学院	传智宋江
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2015年1月10日
 */
public class ConvertUtil {
	
	/*
	 * 数据库表规范，带业务后缀 _P权限表，_B基础表，_C业务表
	 * 转换类名去掉业务后缀，然后驼峰规则
	 */
	public static String getClassName(String tableName){
		tableName = tableName.toUpperCase();
		if("_".equals(tableName.substring(tableName.length()-2, tableName.length()-1))){
			tableName = tableName.substring(0,tableName.length()-2);
		}
		return formatAaa(convertField(tableName));
	}
	
	/**
	 * 把数据库中的字段转换为变量类型
	 * 如（USER_ID ----> userId）
	 * @param field
	 * @return
	 */
	public static String convertField(String name) {
		//分隔符
		char separator = '_';
		//转化为小写
		String variable = name.toLowerCase();
		
		if (variable.indexOf(separator)>-1) {
			char[] varArray = variable.toCharArray();
			for(int i=0;i<varArray.length;i++){
				if (varArray[i]==separator&&i<varArray.length-1) {
					varArray[i+1]=Character.toUpperCase(varArray[i+1]);
				}
			}
			variable = new String(varArray).replaceAll("_", "");
		}
		
		return variable;
		
	}	
	
	//首字母大写
	public static String formatAaa(String source) {
		if (source==null) return null;
		if (source.equals("")) return "";
	
		String a;
		a = source.substring(0, 1);
		a = a.toUpperCase();
		return a + source.substring(1);
	}
	
	/**通过resultSetMetaData 对象获取字符串型的类型名
	 * @param type
	 * @return
	 */
/*	public static  String getTypeName(int type){
		
		String typeName = String.class.getSimpleName();
		
		switch (type) {
		case Types.VARCHAR:
		case Types.BLOB:
			break;
		case Types.INTEGER:
		case Types.SMALLINT:
		case Types.NUMERIC:
			typeName=Integer.class.getSimpleName();
			break;
		case Types.DATE:
		case Types.TIMESTAMP:
			typeName=Date.class.getSimpleName();
			break;
		case Types.BOOLEAN:
			typeName=Boolean.class.getSimpleName();
			break;
		case Types.FLOAT:
			typeName=Float.class.getSimpleName();
			break;
		default:
			break;
		}
		
		return typeName;
		
	}*/
	
	
	public static  String getTypeName(int type){
		
		String typeName = String.class.getSimpleName();
		
		switch (type) {
		case Types.VARCHAR:
		case Types.BLOB:
			break;
		case Types.INTEGER:
		case Types.SMALLINT:
		case Types.NUMERIC:
			typeName=Integer.class.getSimpleName();
			break;
		case Types.DATE:
		case Types.TIMESTAMP:
			typeName=Date.class.getSimpleName();
			break;
		case Types.BOOLEAN:
			typeName=Boolean.class.getSimpleName();
			break;
		case Types.FLOAT:
			typeName=Float.class.getSimpleName();
			break;
		default:
			break;
		}
		
		return typeName;
		
	}
	
	/**通过resultSetMetaData 对象获取字符串型的类型名 mysql已验证
	 * @param type 数据库设计规范：VARCHAR,CHAR,INTEGER,NUMERIC(5,2),TIMESTAMP
	 * @return
	 */
	public static String getTypeName(String type){
		System.out.println(type);
		String typeName = String.class.getSimpleName();;
		
		if(type.startsWith("INT")){
			typeName = Integer.class.getSimpleName();
		}else if(type.startsWith("NUMERIC") || type.startsWith("DECIMAL") || type.startsWith("NUMBER")){
			typeName = Double.class.getSimpleName();
		}else if(type.startsWith("TIMESTAMP") || type.startsWith("DATETIME") || type.startsWith("DATE")){
			typeName = Date.class.getSimpleName();
		}
		
		return typeName;
		
	}
	
}
