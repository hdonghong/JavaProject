package pers.hdh.management.action.cargo;

import static java.io.File.separator;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import pers.hdh.management.action.BaseAction;
import pers.hdh.management.domain.ContractProduct;
import pers.hdh.management.service.ContractProductService;
import pers.hdh.management.utils.DownloadUtil;
import pers.hdh.management.utils.UtilFuns;

/**
 * @ClassName	OutProductAction	
 * @Description	出货表的控制器
 * @author		hdonghong
 * @version 	v1.0 
 * @since		2018/02/09 17:11:14
 */
public class OutProductAction extends BaseAction {

	private static final long serialVersionUID = 6119590426676814968L;
	
	private String inputDate;
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	
	private ContractProductService contractProductService;
	public void setContractProductService(ContractProductService contractService) {
		this.contractProductService = contractService;
	}

	/**
	 * 进入出货表的打印页面
	 * @return
	 * @throws Exception
	 */
	public String toedit() throws Exception {
		return "toedit";
	}
	
	/**
	 * 模板打印
	 * @return
	 * @throws Exception
	 */
	public String print() throws Exception {
		int cellNo = 1;// 单元格下标
		Cell cell = null;// 单元格对象
		int rowNo = 0;// 行下标
		Row row = null;// 行对象
		
		// 读取工作簿
		String path = ServletActionContext.getRequest().getRealPath(separator) + separator + "make" + separator + "xlsprint" + separator + "tOUTPRODUCT.xls";
		Workbook workbook = new HSSFWorkbook(new FileInputStream(path));
		// 读取工作表
		Sheet sheet = workbook.getSheetAt(0);
		
		// 设置大标题
		row = sheet.getRow(rowNo);// 读取大标题的行对象
		cell = row.getCell(cellNo);// 在当前行上读取一个单元格对象
		cell.setCellValue(inputDate.replace("-0", "-").replace("-", "年") + "月份出货表");// 设置单元格内容，如，2018-02或2018-12
		
		// 设置小标题（跳过）
		
		// 数据输出
		row = sheet.getRow(rowNo += 2);// 读取到第三行
		CellStyle customCellStyle = row.getCell(cellNo++).getCellStyle();
		CellStyle contractNoCellStyle = row.getCell(cellNo++).getCellStyle();
		CellStyle productNoCellStyle = row.getCell(cellNo++).getCellStyle();
		CellStyle cnumberCellStyle = row.getCell(cellNo++).getCellStyle();
		CellStyle factoryCellStyle = row.getCell(cellNo++).getCellStyle();
		CellStyle deliveryPeriodCellStyle = row.getCell(cellNo++).getCellStyle();
		CellStyle shipTimeCellStyle = row.getCell(cellNo++).getCellStyle();
		CellStyle tradeTermsCellStyle = row.getCell(cellNo++).getCellStyle();
		
		String hql = "from ContractProduct where to_char(contract.shipTime, 'yyyy-MM') = '" + inputDate + "'";
		List<ContractProduct> list = contractProductService.find(hql, ContractProduct.class, null);// 查询出符合指定船期的货物列表
		for (ContractProduct contractProduct : list) {
			row = sheet.createRow(rowNo++);
			row.setHeightInPoints(24);// 设置行高
			
			cellNo = 1;// 重置单元格下标为1
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getContract().getCustomName());// 客户名称
			cell.setCellStyle(customCellStyle);
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getContract().getContractNo());// 订单号--- 合同号
			cell.setCellStyle(contractNoCellStyle);
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getProductNo());// 货号
			cell.setCellStyle(productNoCellStyle);
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getCnumber());// 数量
			cell.setCellStyle(cnumberCellStyle);
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getFactoryName());// 工厂名
			cell.setCellStyle(factoryCellStyle);
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getDeliveryPeriod()));// 交期
			cell.setCellStyle(deliveryPeriodCellStyle);
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getShipTime()));// 船期
			cell.setCellStyle(shipTimeCellStyle);
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getContract().getTradeTerms());// 贸易条款
			cell.setCellStyle(tradeTermsCellStyle);
			
		}
		
		// 下载到客户端
		DownloadUtil downloadUtil = new DownloadUtil();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();// 输出流
		workbook.write(outputStream);// 将excel表格中的内容输出到缓存中
		outputStream.close();// 刷新缓冲区
		HttpServletResponse response = ServletActionContext.getResponse();
		downloadUtil.download(outputStream, response, "出货表.xls");
		
		return NONE;
	}
	
	/**
	 * 打印出货表，不使用模板
	 * @return
	 * @throws Exception
	 */
/*	public String print() throws Exception {
		int cellNo = 1;// 单元格下标
		Cell cell = null;// 单元格对象
		int rowNo = 0;// 行下标
		Row row = null;// 行对象
		
		// 创建工作簿
		Workbook workbook = new HSSFWorkbook();
		// 创建工作表
		Sheet sheet = workbook.createSheet();
		// 设置单元格宽
		sheet.setColumnWidth(cellNo++, 26*256);
		sheet.setColumnWidth(cellNo++, 11*256);
		sheet.setColumnWidth(cellNo++, 29*256);
		sheet.setColumnWidth(cellNo++, 12*256);
		sheet.setColumnWidth(cellNo++, 15*256);
		sheet.setColumnWidth(cellNo++, 10*256);
		sheet.setColumnWidth(cellNo++, 10*256);
		sheet.setColumnWidth(cellNo++, 8*256);
		
		// 设置大标题
		row = sheet.createRow(rowNo);// 创建大标题的行对象
		row.setHeightInPoints(36);// 设置行高
		cellNo = 1;// 重置单元格下标为1
		cell = row.createCell(cellNo);// 在当前行上创建一个单元格对象
		sheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, cellNo, cellNo+7));// 合并单元格
		cell.setCellValue(inputDate.replace("-0", "-").replace("-", "年") + "月份出货表");// 设置单元格内容，如，2018-02或2018-12
		cell.setCellStyle(this.bigTitle(workbook));// 设置单元格样式
		
		// 设置小标题
		row = sheet.createRow(++rowNo);
		row.setHeightInPoints(26.25f);// 设置行高
		String titles[] = {"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};
		// 创建单元格对象，设置内容与样式
		for (String title : titles) {
			cell = row.createCell(cellNo++);
			cell.setCellValue(title);
			cell.setCellStyle(this.title(workbook));
		}
		
		// 数据输出
		String hql = "from ContractProduct where to_char(contract.shipTime, 'yyyy-MM') = '" + inputDate + "'";
		List<ContractProduct> list = contractProductService.find(hql, ContractProduct.class, null);// 查询出符合指定船期的货物列表
		for (ContractProduct contractProduct : list) {
			row = sheet.createRow(++rowNo);
			row.setHeightInPoints(24);// 设置行高
			
			cellNo = 1;// 重置单元格下标为1
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getContract().getCustomName());// 客户名称
			cell.setCellStyle(this.text(workbook));
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getContract().getContractNo());// 订单号--- 合同号
			cell.setCellStyle(this.text(workbook));
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getProductNo());// 货号
			cell.setCellStyle(this.text(workbook));
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getCnumber());// 数量
			cell.setCellStyle(this.text(workbook));
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getFactoryName());// 工厂名
			cell.setCellStyle(this.text(workbook));
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getDeliveryPeriod()));// 交期
			cell.setCellStyle(this.text(workbook));
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(UtilFuns.dateTimeFormat(contractProduct.getContract().getShipTime()));// 船期
			cell.setCellStyle(this.text(workbook));
			
			cell = row.createCell(cellNo++);
			cell.setCellValue(contractProduct.getContract().getTradeTerms());// 贸易条款
			cell.setCellStyle(this.text(workbook));
			
		}
		
		// 下载到客户端
		DownloadUtil downloadUtil = new DownloadUtil();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();// 输出流
		workbook.write(outputStream);// 将excel表格中的内容输出到缓存中
		outputStream.close();// 刷新缓冲区
		HttpServletResponse response = ServletActionContext.getResponse();
		downloadUtil.download(outputStream, response, "出货表.xls");
		
		return NONE;
	}*/
	
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
