package cn.itcast.export.util.file;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class PoiUtil {
	private static final String ENFONT = "Times New Roman";

	public HSSFFont defaultFont10(HSSFWorkbook wb) {
		HSSFFont curFont = wb.createFont(); // 设置字体
		curFont.setFontName(this.ENFONT);
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET); // 设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setFontHeightInPoints((short) 10);

		return curFont;
	}
	
	public HSSFFont defaultFont10Blod(HSSFWorkbook wb) {
		HSSFFont curFont = wb.createFont(); // 设置字体
		curFont.setFontName(this.ENFONT);
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET); // 设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		curFont.setFontHeightInPoints((short) 10);
		
		return curFont;
	}

	public HSSFFont defaultFont12(HSSFWorkbook wb) {
		HSSFFont curFont = wb.createFont(); // 设置字体
		curFont.setFontName(this.ENFONT);
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET); // 设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setFontHeightInPoints((short) 12);

		return curFont;
	}

	public HSSFFont blackFont12(HSSFWorkbook wb) {
		HSSFFont theFont = wb.createFont(); // 设置字体
		theFont.setFontName("黑体");
		theFont.setCharSet(HSSFFont.DEFAULT_CHARSET); // 设置中文字体，那必须还要再对单元格进行编码设置
		theFont.setFontHeightInPoints((short) 12);

		return theFont;
	}

	public HSSFFont songBoldFont16(HSSFWorkbook wb) {
		HSSFFont curFont = wb.createFont(); // 设置字体
		curFont.setFontName("宋体");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET); // 设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		curFont.setFontHeightInPoints((short) 16);

		return curFont;
	}

	public short money1Format(HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();
		return format.getFormat("#,###,###.0"); // 设置格式
	}

	public short money2Format(HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();
		return format.getFormat("#,###,###.00"); // 设置格式
	}

	public short rmb2Format(HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();
		return format.getFormat("\"￥\"#,###,###.00"); // 设置格式
	}

	public short rmb4Format(HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();
		return format.getFormat("\"￥\"#,###,##0.00"); // 设置格式
	}

	public short datevENFormat(HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();
		return format.getBuiltinFormat("m/d/yy"); // 设置格式
	}

	// 指定图片类型为jpg
	public void setPicture(HSSFWorkbook wb, HSSFPatriarch patriarch, String pic, int iRow, int iCol) throws IOException {
		// 判断文件是否存在
		File imgFile = new File(pic);
		if (imgFile.exists()) {
			// 图片处理
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg = ImageIO.read(imgFile);
			ImageIO.write(bufferImg, "jpg", byteArrayOut);

			HSSFClientAnchor anchor = new HSSFClientAnchor(190, 0, 1000, 0, (short) (iCol), iRow - 1, (short) (iCol + 1), iRow);
			patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
		}
	}

	// 指定图片类型为jpg
	public void setPicture(HSSFWorkbook wb, HSSFPatriarch patriarch, String pic, int iRowStart, int iColStart, int iRowStop, int iColStop) throws IOException {
		// 判断文件是否存在
		File imgFile = new File(pic);
		if (imgFile.exists()) {
			// 图片处理 
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg = ImageIO.read(imgFile);
			ImageIO.write(bufferImg, "jpg", byteArrayOut);

			// 左,上(0-255),右(0-1023),下
			HSSFClientAnchor anchor = new HSSFClientAnchor(20, 1, 1018, 0, (short) (iColStart), iRowStart, (short) (iColStop), iRowStop);
			patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
		}
	}

	// 画线
	public void setLine(HSSFWorkbook wb, HSSFPatriarch patriarch, int iRowStart, int iColStart, int iRowStop, int iColStop) {
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 350, 0, (short) (iColStart), iRowStart, (short) (iColStop), iRowStop);
		HSSFSimpleShape lineShape = patriarch.createSimpleShape(anchor);
		lineShape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
	}

	// 计算行高度，实现行自动适应高度 defaultRowHeight = 12.00f; //每一行的高度指定   目前只实现根据回车多行来判断，不能根据单元格宽度自动回行来判断
	public float getCellAutoHeight(String str, float defaultRowHeight) {
		if (str == null) {
			return defaultRowHeight;
		}
		float height = 0.00f;
		int n = 0;
		if (str.endsWith("\n")) {
			n = str.split("\n").length; // 回车个数
		} else {
			n = str.split("\n").length + 1; // 回车个数
		}
		height = defaultRowHeight * n;

		return height; // 计算
	}

	//计算字符串高度
	public float getregex(String charStr) {
		if (charStr.equals(" ")) {
			return 0.5f;
		}
		if (Pattern.compile("^[A-Za-z0-9]+$").matcher(charStr).matches()) {
			return 0.5f;
		}
		// 判断是否为全角
		if (Pattern.compile("^[\u4e00-\u9fa5]+$").matcher(charStr).matches()) {
			return 1.00f;
		}
		if (Pattern.compile("^x00-xff]+$").matcher(charStr).matches()) {
			return 1.00f;
		}
		return 0.5f;
	}

	public HSSFCellStyle titlev12(HSSFWorkbook wb, HSSFFont blackFont) {
		HSSFCellStyle curStyle = wb.createCellStyle();

		curStyle.setFont(blackFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle nobox(HSSFWorkbook wb) {
		HSSFCellStyle curStyle = wb.createCellStyle();

		curStyle.setBorderTop(HSSFCellStyle.BORDER_NONE); // 实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_NONE); // 实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE); // 实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE); // 实线右边框

		curStyle.setTopBorderColor((short) 0);

		return curStyle;
	}

	// 实现打印时为白框，目的就是实现涂去上行的下边框线 by tony 20110709
	public HSSFCellStyle whiteBox(HSSFWorkbook wb) {
		HSSFCellStyle curStyle = wb.createCellStyle();

		curStyle.setTopBorderColor(HSSFColor.WHITE.index);
		curStyle.setRightBorderColor(HSSFColor.WHITE.index);
		curStyle.setBottomBorderColor(HSSFColor.WHITE.index);
		curStyle.setLeftBorderColor(HSSFColor.WHITE.index);

		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 实线右边框

		return curStyle;
	}

	public HSSFCellStyle normalv12(HSSFWorkbook wb, HSSFFont defaultFont12) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setFont(defaultFont12);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle normalv10(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setFont(defaultFont10);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle bnormalv12(HSSFWorkbook wb, HSSFFont defaultFont12) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setFont(defaultFont12);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle moneyrv10_BorderThin(HSSFWorkbook wb, HSSFFont defaultFont10, short rmb4Format) {
		HSSFCellStyle curStyle = wb.createCellStyle();

		curStyle.setFont(defaultFont10);
		curStyle.setDataFormat(rmb4Format);

		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 实线右边框

		return curStyle;
	}

	public HSSFCellStyle numberrv10_BorderThin(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setFont(defaultFont10);

		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 实线右边框

		return curStyle;
	}

	public HSSFCellStyle moneyrv12_BorderThin(HSSFWorkbook wb, HSSFFont defaultFont12, short rmb2Format) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setFont(defaultFont12);
		curStyle.setDataFormat(rmb2Format);

		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 实线右边框

		return curStyle;
	}

	public HSSFCellStyle money1(HSSFWorkbook wb, HSSFFont defaultFont10, short money1Format) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setFont(defaultFont10);
		curStyle.setDataFormat(money1Format);

		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle money2(HSSFWorkbook wb, HSSFFont defaultFont10, short money2Format) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setFont(defaultFont10);
		curStyle.setDataFormat(money2Format);

		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle datevEN(HSSFWorkbook wb, HSSFFont defaultFont10, short datevENFormat) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setFont(defaultFont10);
		curStyle.setDataFormat(datevENFormat);

		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle notet10(HSSFWorkbook wb) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true); // 换行

		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 实线右边框

		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle notevt10(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true); // 换行
		curStyle.setFont(defaultFont10);

		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP); // 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle noterv10(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true); // 换行
		curStyle.setFont(defaultFont10);

		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}
	
	public HSSFCellStyle noterv10NoWrap(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(false);  						//换行   
		curStyle.setFont(defaultFont10);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	} 	

	public HSSFCellStyle notehv10(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true); 									// 换行
		curStyle.setFont(defaultFont10);
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 	// 单元格垂直居中

		return curStyle;
	}

	// 横向居左，垂直居中
	public HSSFCellStyle notehlv10(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true); // 换行
		curStyle.setFont(defaultFont10);
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	// 横向居右，垂直居中
	public HSSFCellStyle notehrv10(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true); 									// 换行
		curStyle.setFont(defaultFont10);
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 	// 单元格垂直居中

		return curStyle;
	}

	public HSSFCellStyle notehv10_BorderThin(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true); // 换行

		curStyle.setFont(defaultFont10);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 实线右边框

		return curStyle;
	}

	public HSSFCellStyle notecv10_BorderThin(HSSFWorkbook wb, HSSFFont defaultFont10) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true); 									// 换行
		curStyle.setFont(defaultFont10);
		curStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 	// 单元格垂直居中

		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 				// 实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); 			// 实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); 			// 实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); 				// 实线右边框

		return curStyle;
	}

}
