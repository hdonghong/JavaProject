package execute;

import java.sql.SQLException;
import com.platform.main.CodeMeachine;

public class RunEntrance {
	
	public static void main(String[] args) throws SQLException {
		String tableName = "PACKING_LIST_C";
		
		
		CodeMeachine.setGeneratePath("F:\\Study\\code\\");
		CodeMeachine.setPackageName("pers.hdh.management");
		
		//CodeMeachine.generateFileWithDb("demo.ftl", tableName, "Demo.java");
		
		CodeMeachine.generateFileWithDb("config.ftl", tableName, "Config.txt");
		CodeMeachine.generateFileWithDb("poModel.ftl", tableName, ".java");
		CodeMeachine.generateFileWithDb("mapper.hbm.ftl", tableName, ".hbm.xml");
		CodeMeachine.generateFileWithDb("ServiceInterface.ftl", tableName, "Service.java");
		CodeMeachine.generateFileWithDb("ServiceImpl.ftl", tableName, "ServiceImpl.java");
		CodeMeachine.generateFileWithDb("Action.ftl", tableName, "Action.java");
		
	
		CodeMeachine.generateFileWithDb("jDeptListPage.jsp", tableName, "ListPage.jsp");
		//CodeMeachine.generateFileWithDb("jRole.jsp", tableName, "List.jsp");
		CodeMeachine.generateFileWithDb("jDeptCreate.jsp", tableName, "Create.jsp");
		CodeMeachine.generateFileWithDb("jDeptUpdate.jsp", tableName, "Update.jsp");
		CodeMeachine.generateFileWithDb("jDeptView.jsp", tableName, "View.jsp");
		
		
		
		System.out.println("Generate success.");
	}
	
}
