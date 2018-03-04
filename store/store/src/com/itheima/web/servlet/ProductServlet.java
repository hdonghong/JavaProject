package com.itheima.web.servlet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.utils.CookieUtils;

/**
 * 商品的servlet
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @throws Exception 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取商品id
		String pid = request.getParameter("pid");
		
		//2.调用service查询商品
		ProductService service = new ProductServiceImpl();
		Product product = service.getById(pid);
		
	//扩展：商品浏览记录
		//获取指定的cookie
		Cookie c = CookieUtils.getCookieByName("ids", request.getCookies());
		String ids = "";
		if (c == null) { //若cookie为空
			ids = pid;
		} else {
			//将cookie中的值存放到链表
			ids = c.getValue();
			String[] id_arr = ids.split("-");
			List<String> temp_list = Arrays.asList(id_arr);
			LinkedList<String> id_list = new LinkedList<>(temp_list);
			
			if (id_list.contains(pid)) { //cookie中已存在此商品id，将其移至最前
				id_list.remove(pid);
			} else {
				if (id_list.size() >= 3) { //cookie中存放商品id已满，移除末尾
					id_list.removeLast();
				}
			}
			
			//重写cookie的value
			id_list.addFirst(pid);
			ids = "";
			for (String id : id_list) {
				ids += (id + "-");
			}
			ids = ids.substring(0, ids.length()-1);
		}
		c = new Cookie("ids", ids);
		c.setMaxAge(3600);//设置存活时间为：1hour
		c.setPath(request.getContextPath()+"/"); //设置cookie路径为：项目路径
		response.addCookie(c);//写回浏览器
		
		//3.判断product
		if (product == null) {
			return null;
		}
		
		request.setAttribute("product", product);
		return "/jsp/product_info.jsp";
	}
	
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取商品的分类id 当前页 设置页面条数
		String cid = request.getParameter("cid");
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize = 12;
		
		//2.调用service查询商品
		ProductService service = new ProductServiceImpl();
		PageBean<Product> pageBean = service.findByPage(cid, currPage, pageSize);
		
		//3.判断pageBean
		if (pageBean == null) {
			return null;
		}
		
		request.setAttribute("pageBean", pageBean);
		return "/jsp/product_list.jsp";
	}
}
