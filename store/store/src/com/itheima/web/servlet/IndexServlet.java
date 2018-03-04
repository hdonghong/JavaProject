package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;

/**
 * 和首页相关的servlet
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductServiceImpl();
		//1.调用service查询最新商品和热门商品
		List<Product> newList = null;
		List<Product> hotList = null;
		try {
			newList = service.findNew();
			hotList = service.findHot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//2.将获取的商品列表放入request域中
		request.setAttribute("nList", newList);
		request.setAttribute("hList", hotList);
		
		return "/jsp/index.jsp";
	}

}
