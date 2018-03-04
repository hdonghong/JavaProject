package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 查询所有分类
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.调用service的findAll方法查询所有分类
		CategoryService service = (CategoryService) BeanFactory.getBean("CategoryService");
		List<Category> clist = null;
		try {
			clist = service.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//2.将clist转成JSON格式返回到页面上
		String json = JSONArray.fromObject(clist).toString();
		
		//3.返回
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(json);
		
		
		return null;
	}

}
