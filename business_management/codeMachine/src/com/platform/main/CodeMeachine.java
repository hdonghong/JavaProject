package com.platform.main;

import java.sql.SQLException;
import java.util.Map;

import com.platform.tool.data.DataService;
import com.platform.tool.data.DataServiceImpl;
import com.platform.tool.freeMaker.FreeMakerUtil;
import com.platform.tool.util.ConvertUtil;

public class CodeMeachine {
	
	//默认生成文件的路径
	private static String generatePath = "D:\\";
	private static String packageName = "pers.hdh.management.domain";
	
	private static FreeMakerUtil freeMakerUtil = new FreeMakerUtil();
	private static DataService dataService = new DataServiceImpl();
	
	/**生成VO文件
	 * @param tableName
	 * @param packageName
	 * @param classPre
	 * @throws SQLException 
	 */
	public static void generateVoFile(String tableName,String packageName,String classPre,String fileName) throws SQLException{
		generateFileWithDb("voModel.ftl", tableName, packageName, classPre,fileName);
	}
	
	/**生成Dao文件
	 * @param tableName
	 * @param packageName
	 * @param classPre
	 */
	public static void generateDaoFile(String tableName,String packageName,String classPre,String fileName){
		generateFileWithOutDb("daoModel.ftl", tableName, packageName, classPre,fileName);
	}
	
	/**根据不同模板生成文件
	 * （无需操作数据库，没有列数据）
	 * @param templateName
	 * @param tableName
	 * @param packageName
	 * @param classPre
	 */
	public static void generateFileWithOutDb(String templateName,String tableName,String packageName,String classPre,String fileName){
		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableName, packageName, classPre);
		freeMakerUtil.generateFile(templateName, templateData, generatePath+fileName);
	}
	
	/**根据不同模板生成文件
	 * （包含列数据）
	 * @param templateName
	 * @param tableName
	 * @param packageName
	 * @param classPre
	 * @throws SQLException 
	 */
	public static void generateFileWithDb(String templateName,String tableName,String packageName,String classPre,String fileName) throws SQLException{
		Map<String, Object> templateData = dataService.getDbTemplateData(tableName, packageName, classPre);
		freeMakerUtil.generateFile(templateName, templateData, generatePath+fileName);
	}
	
	public static void generateFileWithDb(String templateName,String tableName,String fileNameSuffix) throws SQLException{
		String className = ConvertUtil.getClassName(tableName);
		Map<String, Object> templateData = dataService.getDbTemplateData(tableName, packageName, className);
		
		String fileNamePrefix = ConvertUtil.formatAaa(className);
		if(fileNameSuffix.endsWith(".jsp")){
			fileNamePrefix = "j" + fileNamePrefix;		//如果是jsp文件按 jModelList.jsp命名规则
		}
		freeMakerUtil.generateFile(templateName, templateData, generatePath+fileNamePrefix+fileNameSuffix);
	}
	
	/**
	 *设置文件生成目录
	 * @param generatePath
	 */
	public static void setGeneratePath(String generatePath) {
		CodeMeachine.generatePath = generatePath;
	}
	
	//设置包路径
	public static void setPackageName(String packageName) {
		CodeMeachine.packageName = packageName;
	}

}
