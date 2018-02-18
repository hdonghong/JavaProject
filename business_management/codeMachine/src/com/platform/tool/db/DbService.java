package com.platform.tool.db;

import java.sql.SQLException;
import java.util.List;

import com.platform.model.FieldBean;

public interface DbService {
	
	/**根据表名获取所有的列信息
	 * @param tableName
	 * @return
	 */
	public List<FieldBean> getAllColums(String tableName);
	public List<FieldBean> getAllColums(String tableName, String Owner, String dataBase) throws SQLException;
	
}
