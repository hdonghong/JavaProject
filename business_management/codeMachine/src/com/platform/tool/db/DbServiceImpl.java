package com.platform.tool.db;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.platform.model.FieldBean;
import com.platform.tool.util.ConvertUtil;


public class DbServiceImpl implements DbService {
	
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String sqlstr = null;
	
	/**根据表名获取所有的列信息
	 * @param tableName
	 * @return
	 */
	public List<FieldBean> getAllColums(String tableName){
		List<FieldBean> returnList = new ArrayList<FieldBean>();
		DbConn dbConn = new DbConn();
		
		try {
			this.conn = dbConn.getConnection();
			this.sqlstr = "select * from " + tableName;
			this.st = conn.createStatement();
			this.rs = this.st.executeQuery(sqlstr);
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			
			for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
				String columName = resultSetMetaData.getColumnName(i);
				String proName = ConvertUtil.convertField(columName);				//java属性驼峰规则
				String dataType = ConvertUtil.getTypeName(resultSetMetaData.getColumnType(i)+"");
				
				FieldBean fieldBean = new FieldBean();
				fieldBean.setFieldName(columName.toLowerCase());
				fieldBean.setProName(proName);
				fieldBean.setProType(dataType);
				fieldBean.setColumnName(columName.toUpperCase());		//数据库字段全大写
				
				returnList.add(fieldBean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbConn.closeALL(conn, st, rs, pst);
		}
		return returnList;
		
	}
	
	//这个方法比上个获得内容多个字段的备注信息和是否主键，不同数据库获取字段备注的方式不同
	public List<FieldBean> getAllColums(String tableName, String Owner, String dataBase) throws SQLException{
		if("mysql".equals(dataBase)){
			return this.getMySqlCloumns(tableName);
		}else if("oracle".equals(dataBase)){
			return this.getOracleTCloumns(tableName, Owner);
		}
		return null;
	}

	/***
	 * 打印MySql的表模板参数文件(jsp):
	 * @throws SQLException
	 * @throws UnsupportedEncodingException
	 */
	
	public List<FieldBean> getMySqlCloumns(String tableName) throws SQLException{
		DbConn dbConn = new DbConn();
		this.conn = dbConn.getConnection();
		
		//System.setProperty("file.encoding", "UTF-8");
		List<FieldBean> returnList = new ArrayList<FieldBean>();
		try{
			Statement stmt = conn.createStatement();
			String sql = " show full columns from "+tableName;
			//System.out.println(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				FieldBean fieldBean = new FieldBean();
				String dataType = ConvertUtil.getTypeName(rs.getString("Type").toUpperCase());
				
				fieldBean.setColumnName(rs.getString("Field").toUpperCase());
				fieldBean.setFieldName(fieldBean.getColumnName().toLowerCase());
				fieldBean.setProName(ConvertUtil.convertField(fieldBean.getColumnName()));
				fieldBean.setProType(dataType);
				fieldBean.setProComment(rs.getString("Comment")); //varchar int text datetime
				fieldBean.setPrimary("PRI".equals(rs.getString("Key"))?true:false);
				fieldBean.setMandatory(!"YES".equals(rs.getString("Null"))?true:false);
				
				returnList.add(fieldBean);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
			dbConn.closeALL(conn, st, rs, pst);
		}
		return returnList;
	}


	/***
	 * 打印ORACLE的表模板参数文件(jsp):
	 * @throws SQLException
	 */
	public List<FieldBean> getOracleTCloumns(String Table,String Owner) throws SQLException{
		DbConn dbConn = new DbConn();
		this.conn = dbConn.getConnection();
		
		List<FieldBean> returnList = new ArrayList<FieldBean>();
		try{
			Statement stmt = conn.createStatement();
			String sql=
			 "select "+
			 "	       comments as \"Name\","+
			 "	       a.column_name \"Code\","+
			 "	       a.DATA_TYPE as \"DataType\","+
			 "	       b.comments as \"Comment\","+
			 "	       decode(c.column_name,null,'FALSE','TRUE') as \"Primary\","+
			 "	       decode(a.NULLABLE,'N','TRUE','Y','FALSE','') as \"Mandatory\","+
			 "	       '' \"sequence\""+
			 "	 from "+
			 "	     all_tab_columns a, "+
			 "	     all_col_comments b,"+
			 "	     ("+
			 "	      select a.constraint_name, a.column_name"+
			 "	        from user_cons_columns a, user_constraints b"+
			 "	       where a.constraint_name = b.constraint_name"+
			 "	             and b.constraint_type = 'P'"+
			 "	             and a.table_name = '"+Table+"'"+
			 "	     ) c"+
			 "	 where "+
			 "	   a.Table_Name=b.table_Name "+
			 "	   and a.column_name=b.column_name"+
			 "	   and a.Table_Name='"+Table+"'"+
			 "	   and a.owner=b.owner "+
			 "	   and a.owner='"+Owner+"'"+
			 "	   and a.COLUMN_NAME = c.column_name(+)" +
			 "	order by a.COLUMN_ID";
			System.out.println(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				FieldBean fieldBean = new FieldBean();
				
				String dataType = ConvertUtil.getTypeName(rs.getString("DataType").toUpperCase());
				
				
				fieldBean.setColumnName(rs.getString("Code").toUpperCase());
				fieldBean.setFieldName(fieldBean.getColumnName().toLowerCase());
				fieldBean.setProName(ConvertUtil.convertField(fieldBean.getColumnName()));
				//fieldBean.setProType(rs.getString("DataType").toUpperCase());
				fieldBean.setProType(dataType);
				
				
				fieldBean.setProComment(rs.getString("Comment")); //varchar int text datetime
				fieldBean.setPrimary("TRUE".equals(rs.getString("Primary"))?true:false);
				fieldBean.setMandatory(!"YES".equals(rs.getString("Mandatory"))?true:false);
				
				returnList.add(fieldBean);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
			dbConn.closeALL(conn, st, rs, pst);
		}
		return returnList;
	}
	//修改前
/*	public List<FieldBean> getOracleTCloumns(String Table,String Owner) throws SQLException{
		DbConn dbConn = new DbConn();
		this.conn = dbConn.getConnection();
		
		List<FieldBean> returnList = new ArrayList<FieldBean>();
		try{
			Statement stmt = conn.createStatement();
			String sql=
					"select "+
							"	       comments as \"Name\","+
							"	       a.column_name \"Code\","+
							"	       a.DATA_TYPE as \"DataType\","+
							"	       b.comments as \"Comment\","+
							"	       decode(c.column_name,null,'FALSE','TRUE') as \"Primary\","+
							"	       decode(a.NULLABLE,'N','TRUE','Y','FALSE','') as \"Mandatory\","+
							"	       '' \"sequence\""+
							"	 from "+
							"	     all_tab_columns a, "+
							"	     all_col_comments b,"+
							"	     ("+
							"	      select a.constraint_name, a.column_name"+
							"	        from user_cons_columns a, user_constraints b"+
							"	       where a.constraint_name = b.constraint_name"+
							"	             and b.constraint_type = 'P'"+
							"	             and a.table_name = '"+Table+"'"+
							"	     ) c"+
							"	 where "+
							"	   a.Table_Name=b.table_Name "+
							"	   and a.column_name=b.column_name"+
							"	   and a.Table_Name='"+Table+"'"+
							"	   and a.owner=b.owner "+
							"	   and a.owner='"+Owner+"'"+
							"	   and a.COLUMN_NAME = c.column_name(+)" +
							"	order by a.COLUMN_ID";
			System.out.println(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				FieldBean fieldBean = new FieldBean();
				fieldBean.setColumnName(rs.getString("Field").toUpperCase());
				fieldBean.setFieldName(fieldBean.getColumnName().toLowerCase());
				fieldBean.setProName(ConvertUtil.convertField(fieldBean.getColumnName()));
				fieldBean.setProType(rs.getString("Type").toUpperCase());
				fieldBean.setProComment(rs.getString("Comment")); //varchar int text datetime
				fieldBean.setPrimary("YES".equals(rs.getString("Key"))?true:false);
				fieldBean.setMandatory(!"YES".equals(rs.getString("Null"))?true:false);
				
				returnList.add(fieldBean);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
			dbConn.closeALL(conn, st, rs, pst);
		}
		return returnList;
	}
*/}
