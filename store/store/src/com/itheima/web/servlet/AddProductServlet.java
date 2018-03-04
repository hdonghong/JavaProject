package com.itheima.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.UUIDUtils;
import com.itheima.utils.UploadUtils;

/**
 * 添加商品的Servlet
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1.封装一个map集合
			Map<String,Object> map = new HashMap<>();
			//使用fileupload
			//创建磁盘文件项工厂
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			//创建核心上传对象
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
			//解析请求
			List<FileItem> list = servletFileUpload.parseRequest(request);
			
			//遍历集合
			for (FileItem item : list) {
				if (item.isFormField()) { //文件项是普通上传组件
					map.put(item.getFieldName(), item.getString("utf-8"));
					
				} else { //文件项是文件上传组件
					String name = item.getName();//获取文件名称
					String realName = UploadUtils.getRealName(name);//获取真实文件名
					String uuidName = UploadUtils.getUUIDName(realName);//获取随机文件名
					String path = this.getServletContext().getRealPath("/products/1");//获取文件存放路径
					
					InputStream input = item.getInputStream();//获取文件读取流
					FileOutputStream output = new FileOutputStream(new File(path, uuidName));
					IOUtils.copy(input, output);
					output.close();
					input.close();
					item.delete();//删除临时文件
					
					map.put(item.getFieldName(), "products/1/"+uuidName);
				}
			}

			//封装参数
			Product product = new Product();
			BeanUtils.populate(product, map);
			product.setPid(UUIDUtils.getId());
			product.setPdate(new Date(new java.util.Date().getTime()));
			
			Category category = new Category();
			category.setCid((String)map.get("cid"));
			product.setCategory(category);
			
		//2.调用service完成添加操作
			ProductService service = (ProductService) BeanFactory.getBean("ProductService");
			service.add(product);
		//3.页面重定向
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
