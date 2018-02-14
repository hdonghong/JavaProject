package jfreechart;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * @Description:  JFreeChart测试
 */
public class Bar {
	public static void main(String[] args) {
		//构造图形的数据集合
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(3300, "货号", "JK041/JK1060339");
		dataset.addValue(3000, "货号", "JK040/JK1060338");
		dataset.addValue(2400, "货号", "JK1060338/JK338");
		dataset.addValue(1304, "货号", "JKF081342/62180");
		dataset.addValue(1200, "货号", "20-37-265/JK1050163");
		dataset.addValue(1000, "货号", "JK5300006/13080");
		dataset.addValue(980, "货号", "J859101549/JK549");
		dataset.addValue(910, "货号", "JK1400002/13077");
		dataset.addValue(300, "货号", "20-37-264/JK1051087");
		dataset.addValue(120, "货号", "JK5100004/13078");
		
		JFreeChart chart = ChartFactory.createBarChart3D(
							"货物销售情况统计图",     		//图形的主标题
							"所属单位名称",                 //X轴外的标签名称
							"数量",                       //Y轴外的标签名称 
							dataset,             	    //图形的数据集
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
		CategoryAxis3D categoryAxis3D = (CategoryAxis3D) categoryPlot.getDomainAxis();
		//获取Y轴对象
		NumberAxis3D numberAxis3D = (NumberAxis3D) categoryPlot.getRangeAxis();
		//处理X轴上的乱码
		categoryAxis3D.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		//处理X轴外的乱码
		categoryAxis3D.setLabelFont(new Font("宋体",Font.BOLD,15));
		//处理Y轴上的乱码
		numberAxis3D.setTickLabelFont(new Font("宋体",Font.BOLD,15));
		//处理Y轴外的乱码
		numberAxis3D.setLabelFont(new Font("宋体",Font.BOLD,15));
		
		//处理Y轴的刻度，不让它自动显示刻度，要设置成1为刻度单位
		numberAxis3D.setAutoTickUnitSelection(false);//不让自动生成刻度
		NumberTickUnit unit = new NumberTickUnit(1d);
		numberAxis3D.setTickUnit(unit);//设置以1为单位
		
		//获取绘图区域对象
		BarRenderer3D barRenderer3D = (BarRenderer3D) categoryPlot.getRenderer();
		//让图形变得苗条点
		barRenderer3D.setMaximumBarWidth(0.08);
		//在图形上显示数字
		barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3D.setBaseItemLabelsVisible(true);
		barRenderer3D.setBaseItemLabelFont(new Font("宋体",Font.BOLD,15));
		
		//显示图形
		ChartFrame chartFrame = new ChartFrame("jk",chart);
		chartFrame.setVisible(true);
		chartFrame.pack();
	}

}
