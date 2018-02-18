package com.platform.tool.freeMaker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMakerUtil {
	
	/**
	 * 
	 * 获取模板文件
	 * 
	 * @param name
	 * @return
	 */
	public Template getTemplate(String name) {
		try {
			Configuration cfg = new Configuration();
			//当处理某个模版时，FreeMarker 直接从缓存中返回对应的 Template 对象，并有一个默认的机制来保证该模版对象是跟模版文件同步的template_update_delay 用来指定更新模版文件的间隔时间，相当于多长时间检测一下是否有必要重新加载模版文件，0 表示每次都重新加载，否则为多少毫秒钟检测一下模版是否更改。
			//cfg.setTemplateUpdateDelay(0);
			
			cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
			Template template = cfg.getTemplate(name);
			return template;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 控制台输出
	 * 
	 * @param name
	 * @param root
	 */
	public void print(String templateName, Map<String, Object> root) {

		try {
			Template template = this.getTemplate(templateName);
			template.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 生成文件
	 * 
	 * @param name:模板名
	 * @param root：数据原型
	 * @param outFile：输出路径(全路径名)
	 */
	public void generateFile(String templateName, Map<String, Object> root, String outFilePath) {

		FileWriter out = null;
		try {
			// 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
			out = new FileWriter(new File(outFilePath));
			Template temp = this.getTemplate(templateName);
			temp.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
