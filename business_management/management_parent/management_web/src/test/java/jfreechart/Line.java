package jfreechart;

import java.awt.Font;
import java.awt.Rectangle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * @Description:  JFreeChart测试
 */
public class Line {

	public static void main(String[] args) {
		//构造图形的数据集合
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(16, "访问数", "00");
		dataset.addValue(12, "访问数", "01");
		dataset.addValue(4, "访问数", "02");
		dataset.addValue(1, "访问数", "03");
		dataset.addValue(null, "访问数", "04");
		dataset.addValue(1, "访问数", "05");
		dataset.addValue(1, "访问数", "06");
		dataset.addValue(1, "访问数", "07");
		dataset.addValue(24, "访问数", "08");
		dataset.addValue(118, "访问数", "09");		
		dataset.addValue(109, "访问数", "10");	
		dataset.addValue(109, "访问数", "11");
		dataset.addValue(33, "访问数", "12");
		dataset.addValue(50, "访问数", "13");
		dataset.addValue(68, "访问数", "14");
		dataset.addValue(69, "访问数", "15");
		dataset.addValue(86, "访问数", "16");
		dataset.addValue(44, "访问数", "17");
		dataset.addValue(22, "访问数", "18");
		dataset.addValue(10, "访问数", "19");		
		dataset.addValue(21, "访问数", "20");			
		dataset.addValue(33, "访问数", "21");			
		dataset.addValue(31, "访问数", "22");			
		dataset.addValue(39, "访问数", "23");			
		
		JFreeChart chart = ChartFactory.createLineChart(
							"系统访问压力统计图",     		//图形的主标题
							"访问数",               		//X轴外的标签名称
							"数量",                      //Y轴外的标签名称 
							dataset,             		//图形的数据集
							PlotOrientation.VERTICAL,   //图表的显示形式（水平/垂直）
							true,                       //是否生成图形的子标题
							true, 						//是否在图形上生成工具提示
							true  						//是否通过图片的点击生成URL地址
							);
		//处理主标题的乱码
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,18));
		//处理子标题的乱码
		chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,15));
		//获取图表区域对象
		CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
		//获取X轴对象
		CategoryAxis categoryAxis = (CategoryAxis) categoryPlot.getDomainAxis();
		//获取Y轴对象
		NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
		//处理X轴上的乱码
		categoryAxis.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		//处理X轴外的乱码
		categoryAxis.setLabelFont(new Font("宋体",Font.BOLD,15));
		//处理Y轴上的乱码
		numberAxis.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		//处理Y轴外的乱码
		numberAxis.setLabelFont(new Font("宋体",Font.BOLD,15));
		
		//处理Y轴的刻度，不让它自动显示刻度，要设置成1为刻度单位
		numberAxis.setAutoTickUnitSelection(false);//不让自动生成刻度
		NumberTickUnit unit = new NumberTickUnit(1d);
		numberAxis.setTickUnit(unit);//设置以1为单位
		
		//获取绘图区域对象
		LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
		
		//在图形上显示数字
		lineAndShapeRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lineAndShapeRenderer.setBaseItemLabelsVisible(true);
		lineAndShapeRenderer.setBaseItemLabelFont(new Font("宋体",Font.BOLD,15));
		//生成图形的转折点（矩形）
		/**
		 * 参数一：0表示第一条线
		 * 参数二：表示要以什么图像在线上显示转折点
		 */
		Rectangle shape = new Rectangle(10,10);
		lineAndShapeRenderer.setSeriesShape(0, shape);
		lineAndShapeRenderer.setSeriesShapesVisible(0, true);
		
		//显示图形
		ChartFrame chartFrame = new ChartFrame("jk",chart);
		chartFrame.setVisible(true);
		chartFrame.pack();
	}

}
