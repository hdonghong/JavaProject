package management_web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

/**
 * @ClassName	PoiTest	
 * @Description	
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/09 16:39:43
 */
public class PoiTest {

	/**
	 * 简单生成一份excel表
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
//		a. 创建一个工作簿workbook
		Workbook workbook = new HSSFWorkbook();
		
//		b. 创建一个工作表sheet
		Sheet sheet = workbook.createSheet();
		
//		c. 创建一个行对象row（下标起始值为0），这里是第4行
		Row row = sheet.createRow(3);
		
//		d. 创建一个单元格对象cell（下标起始值为0），这里是第四列
		Cell cell = row.createCell(3);
		
//		e. 设置单元格的内容
		cell.setCellValue("我的github地址：https://github.com/hdonghong");
		
//		f. 设置单元格的样式（如字体）
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Microsoft YaHei UI");
		font.setFontHeightInPoints((short)40);
		cellStyle.setFont(font);
		cell.setCellStyle(cellStyle);
		
//		g. 保存，关闭流对象，在D盘生成excel测试.xls文件
		OutputStream os = new FileOutputStream("D:\\excel测试.xls");
		workbook.write(os);
		os.close();
	}
	
	/**
	 * 生成学生成绩单（不使用模板）
	 * 注意，下面使用的bigTitle、title、text等方法都是关于设置单元格样式的，
	 * 因加入后妨碍理解代码，在此不作展示，如需要，请自行到我github源码处下载
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		int cellNo = 1;// 单元格下标
		Cell cell = null;// 单元格对象
		int rowNo = 0;// 行下标
		Row row = null;// 行对象
		
		// 创建工作簿
		Workbook workbook = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = workbook.createSheet();
		// 设置单元格宽
		sheet.setColumnWidth(cellNo++, 30*256);
		sheet.setColumnWidth(cellNo++, 30*256);
		sheet.setColumnWidth(cellNo++, 30*256);
		
		//--------------设置大标题--------------
		row = sheet.createRow(rowNo);// 创建大标题的行对象
		row.setHeightInPoints(36);// 设置行高
		cellNo = 1;// 重置单元格下标为1
		cell = row.createCell(cellNo);// 在当前行上创建一个单元格对象
		sheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, cellNo, cellNo+2));// 合并单元格
		cell.setCellValue("学生成绩表");// 设置单元格内容
		cell.setCellStyle(this.bigTitle(workbook));// 设置单元格样式
		
		//--------------设置小标题--------------
		row = sheet.createRow(++rowNo);
		row.setHeightInPoints(26.25f);// 设置行高
		String titles[] = {"学号","姓名","成绩（单位：分）"};
		// 创建单元格对象，设置内容与样式
		for (String title : titles) {
			cell = row.createCell(cellNo++);
			cell.setCellValue(title);
			cell.setCellStyle(this.title(workbook));
		}
		
		//--------------模拟数据输出--------------
		row = sheet.createRow(++rowNo);
		row.setHeightInPoints(24);// 设置行高
		
		cellNo = 1;// 重置单元格下标为1
		cell = row.createCell(cellNo++);
		cell.setCellValue("200000");// 设置单元格内容，学号200000
		cell.setCellStyle(this.text(workbook));
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("老王");// 设置单元格内容，老王
		cell.setCellStyle(this.text(workbook));
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("59.9");// 设置单元格内容，59.9分
		cell.setCellStyle(this.text(workbook));
		
		// 保存，关闭流对象，在C盘生成excel测试.xls文件
		OutputStream os = new FileOutputStream("D:\\excel测试.xls");
		workbook.write(os);
		os.close();
	}
	
	/**
	 * 生成学生成绩单（使用模板）
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		int cellNo = 1;// 单元格下标
		Cell cell = null;// 单元格对象
		int rowNo = 0;// 行下标
		Row row = null;// 行对象
		
		// 读取已存在的模板工作簿
		Workbook workbook = new HSSFWorkbook(new FileInputStream("D:\\excel模板.xls"));
		// 读取工作表
		Sheet sheet = workbook.getSheetAt(0);
		
		//--------------设置大标题--------------
		// 不需要了
		
		//--------------设置小标题--------------
		// 不需要了
		
		//--------------模拟数据输出--------------
		row = sheet.createRow(rowNo += 2);// 读取到第三行
		row.setHeightInPoints(24);// 设置行高
		
		cellNo = 1;// 重置单元格下标为1
		cell = row.createCell(cellNo++);
		cell.setCellValue("200000");// 设置单元格内容，学号200000
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("老王");// 设置单元格内容，老王
		
		cell = row.createCell(cellNo++);
		cell.setCellValue("59.9");// 设置单元格内容，59.9分
		
		// 保存，关闭流对象，在D盘生成excel测试.xls文件
		OutputStream os = new FileOutputStream("D:\\excel测试2.xls");
		workbook.write(os);
		os.close();
	}
	
	/**
	 * 大标题的样式
	 * @param workbook
	 * @return
	 */
	public CellStyle bigTitle(Workbook workbook){
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short)16);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);					//字体加粗
		
		style.setFont(font);
		
		style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		return style;
	}
	/**
	 * 小标题的样式
	 * @param workbook
	 * @return
	 */
	public CellStyle title(Workbook workbook){
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)12);
		
		style.setFont(font);
		
		style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
		style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
		style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
		style.setBorderRight(CellStyle.BORDER_THIN);				//右细线
		
		return style;
	}
	
	/**
	 * 文字样式
	 * @param workbook
	 * @return
	 */
	public CellStyle text(Workbook workbook){
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short)10);
		
		style.setFont(font);
		
		style.setAlignment(CellStyle.ALIGN_LEFT);					//横向居左
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
		style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
		style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
		style.setBorderRight(CellStyle.BORDER_THIN);				//右细线
		
		return style;
	}
}
