package com.itheima.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.CategoryService;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;

/**
 * 商品管理的servlet
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 查询所有商品
	 * @throws Exception 
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.调用service进行查询
		ProductService service = (ProductService) BeanFactory.getBean("ProductService");
		List<Product> plist = service.findAll();
		
		//2.请求转发
		request.setAttribute("plist", plist);
		return "/admin/product/list.jsp";
	}
	
	/**
	 * 跳转到添加商品的页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//查询分类
		CategoryService service = (CategoryService) BeanFactory.getBean("CategoryService");
		List<Category> clist = service.findAll();
		
		request.setAttribute("clist", clist);
		return "/admin/product/add.jsp";
	}
}
