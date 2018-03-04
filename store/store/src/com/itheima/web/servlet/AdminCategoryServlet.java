package com.itheima.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.UUIDUtils;

/**
 * 分类管理的servlet
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 获取所有分类
	 * @throws Exception 
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.调用service获取分类列表
		CategoryService service = (CategoryService) BeanFactory.getBean("CategoryService");
		List<Category> list = service.findAll();
		
		//2.放入request域中后请求转发
		request.setAttribute("list", list);
		return "/admin/category/list.jsp";
	}

	/**
	 * 跳转到添加页面上
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/category/add.jsp";
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取分类名称
		String cname = request.getParameter("cname");
		//2.封装category对象
		Category category = new Category();
		category.setCid(UUIDUtils.getId());
		category.setCname(cname);
		//3.调用service添加分类
		CategoryService service = (CategoryService) BeanFactory.getBean("CategoryService");
		service.add(category);
		//4.重定向
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		return null;
	}
	
	/**
	 * 通过分类id获取一个分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取分类id
		String cid = request.getParameter("cid");
		//2.调用service获取分类
		CategoryService service = (CategoryService) BeanFactory.getBean("CategoryService");
		Category category = service.getById(cid);
		//3.请求转发
		request.setAttribute("category", category);
		return "/admin/category/edit.jsp";
	}
	
	/**
	 * 修改分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取分类id和分类名称
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		//2.封装category对象
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		//3.调用service编辑分类
		CategoryService service = (CategoryService) BeanFactory.getBean("CategoryService");
		service.update(category);
		//4.重定向
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		return null;
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取分类id
		String cid = request.getParameter("cid");
		//2.调用service获取分类
		CategoryService service = (CategoryService) BeanFactory.getBean("CategoryService");
		service.delete(cid);
		//3.重定向
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		return null;
	}
}
