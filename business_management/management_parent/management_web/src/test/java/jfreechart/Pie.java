package jfreechart;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
public class Pie {
/**
 * @Description:  JFreeChart测试
 */
	public static void main(String[] args) {
		//构造图形的数据集合
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("宏艺", 10062);
		dataset.setValue("平遥鸿艺", 3256);
		dataset.setValue("南皮开发", 5200);
		dataset.setValue("光华", 5352);
		dataset.setValue("会龙", 3702);
		dataset.setValue("精艺", 2152);
		dataset.setValue("民鑫", 5400);
		dataset.setValue("汇越", 300);
		dataset.setValue("天顺", 4900);
		dataset.setValue("华艺", 3302);
		dataset.setValue("文水志远", 3940);
		
		JFreeChart chart = ChartFactory.createPieChart3D(
							"生成厂家销售情况统计图",     	//图形的主标题
							dataset,             		//图形的数据集
							true,                       //是否生成图形的子标题
							true, 						//是否在图形上生成工具提示
							true  						//是否通过图片的点击生成URL地址
							);
		//处理主标题的乱码
		chart.getTitle().setFont(new Font("宋体",Font.BOLD,18));
		//处理子标题的乱码
		chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,15));
		
		//获取图表区域对象
		PiePlot3D piePlot3D = (PiePlot3D) chart.getPlot();
		//处理图表区域的乱码
		piePlot3D.setLabelFont(new Font("宋体",Font.BOLD,15));
		
		//在图形上生成数值，格式为；沟通 (60%)	{0}名称 {1}数值 (2)百分比
		String labelFormat = "{0} ({2})";
		piePlot3D.setLabelGenerator(new StandardPieSectionLabelGenerator(labelFormat));
		
		//显示图形
		ChartFrame chartFrame = new ChartFrame("jk",chart);
		chartFrame.setVisible(true);
		chartFrame.pack();
	}

}
