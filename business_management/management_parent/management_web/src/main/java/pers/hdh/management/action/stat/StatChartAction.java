package pers.hdh.management.action.stat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.dao.springdao.SqlDao;
import pers.hdh.management.utils.file.FileUtil;

/**
 * @ClassName	StatChartAction	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/13 22:16:26
 */
public class StatChartAction extends BaseAction {

	private static final long serialVersionUID = 8947707083980601866L;
	
	// 直接注入Dao，但最好还是提供Service层，这里省事，故省略
	private SqlDao sqlDao;
	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}
	
	/**
	 * 新版amcharts
	 * 厂家销量排名
	 * @return
	 * @throws Exception
	 */
	public String factorysale() throws Exception {
		String sql = "select factory_name, sum(amount) sumAmount from contract_product_c group by factory_name order by sumAmount desc";
		
		// 执行sql语句，获取统计结果
		List<String> list = sqlDao.executeSQL(sql);
		
		// 拼接成xml文件字符串
		String xmlContent = genPieDataSource(list);
		
		// 将拼接好的字符串写入到data.xml中
		writeXML("stat/chart/factorysale/data.xml", xmlContent);
		
		return "factorysale";
	}
	
	/**
	 * 旧版amcharts
	 * 厂家销量排名
	 * @return
	 * @throws Exception
	 */
/*	public String factorysale() throws Exception {
		String sql = "select factory_name, sum(amount) sumAmount from contract_product_c group by factory_name order by sumAmount desc";
		
		// 执行sql语句，获取统计结果
		List<String> list = sqlDao.executeSQL(sql);
		
		// 拼接成xml文件字符串
		String xmlContent = genPieDataSource(list);
		
		// 将拼接好的字符串写入到data.xml中
		writeXML("stat/chart/factorysale/data.xml", xmlContent);
		
		return "factorysale";
	}*/
	/**
	 * 产品销量排名
	 * @return
	 * @throws Exception
	 */
	public String productsale() throws Exception {
		String sql = "SELECT * FROM (" + 
				"SELECT product_no, sum(amount) totalAmount FROM CONTRACT_PRODUCT_C GROUP BY product_no ORDER BY totalAmount desc)" + 
				"WHERE ROWNUM < 16";
		
		// 执行sql语句，获取统计结果
		List<String> list = sqlDao.executeSQL(sql);
		
		// 拼接成xml文件字符串
		String xmlContent = genBarDataSource(list);
		
		// 将拼接好的字符串写入到data.xml中
		writeXML("stat/chart/productsale/data.xml", xmlContent);
		
		return "productsale";
	}
	/**
	 * 在线人数统计 	
	 	select a.a1, NVL(b.c,0)
		from
		    (select * from online_info_t) a
		left join
		    (select to_char(login_time,'HH24') a1, count(*) c from login_log_p group by to_char(login_time,'HH24')) b
		on a.a1 = b.a1
		order by a.a1;
	 * @return
	 * @throws Exception
	 */
	public String onlineinfo() throws Exception {
		String sql = " select a.a1, NVL(b.c,0) " + 
				" from " + 
				" (select * from online_info_t) a " + 
				" left join " + 
				" (select to_char(login_time,'HH24') a1, count(*) c from login_log_p group by to_char(login_time,'HH24')) b " + 
				" on a.a1 = b.a1 " + 
				" order by a.a1 ";
		
		// 执行sql语句，获取统计结果
		List<String> list = sqlDao.executeSQL(sql);
		// 拼接成xml文件字符串
		String xmlContent = genBarDataSource(list);
		
		// 将拼接好的字符串写入到data.xml中
		writeXML("stat/chart/onlineinfo/data.xml", xmlContent);
		
		return "onlineinfo";
	}

	/**
	 * 拼接生成饼图的数据源
	 * @param list
	 * @return
	 */
	private String genPieDataSource(List<String> list) {
		StringBuilder dataXmlStr = new StringBuilder();
		dataXmlStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		dataXmlStr.append("<pie>");
		for (int i = 0; i < list.size(); i += 2) {
			dataXmlStr.append("<slice title=\""+ list.get(i)+"\" pull_out=\"true\">"+ list.get(i+1) +"</slice>");
		}
		dataXmlStr.append("</pie>");
		return dataXmlStr.toString();
	}
	
	/**
	 * 拼接生成柱状图的数据源
	 * @param list
	 * @return
	 */
	private String genBarDataSource(List<String> list) {
		StringBuilder dataXmlStr = new StringBuilder();
		dataXmlStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		dataXmlStr.append("<chart><series>");
		
		for (int i = 0; i < list.size(); i += 2)
			dataXmlStr.append("<value xid=\""+ (i>>1) +"\">"+ list.get(i) +"</value>");
		dataXmlStr.append("</series><graphs><graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");
		
		for (int i = 1; i < list.size(); i += 2)
			dataXmlStr.append("<value xid=\""+ (i>>1) +"\" description=\"\" url=\"\">" + list.get(i) + "</value>");
		dataXmlStr.append("	</graph></graphs>");
		
		dataXmlStr.append("<labels><label lid=\"0\">");
		dataXmlStr.append("<x>0</x><y>20</y><rotate></rotate><width></width><align>center</align><text_color></text_color><text_size></text_size>");
		dataXmlStr.append("<text><![CDATA[<b>2018-02-14 10:51:56完成</b>]]></text>");
		dataXmlStr.append("</label></labels></chart>");
		
		return dataXmlStr.toString();
	}

	/**
	 * 生成xml文件
	 * @param filename 生成的xml文件名
	 * @param content 写入xml文件中的内容，包括需要约束内容
	 * @throws FileNotFoundException
	 */
	private void writeXML(String filename, String content) throws FileNotFoundException {
		String path = ServletActionContext.getServletContext().getRealPath(File.separator);
		new FileUtil().createTxt(path, filename, content, "UTF-8");
	}
}
