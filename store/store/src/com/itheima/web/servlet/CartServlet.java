package com.itheima.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Cart;
import com.itheima.domain.CartItem;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;

/**
 * 购物车的servlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 从session中获取购物车对象
	 * @param request
	 * @return
	 */
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) { //第一次获取
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		return cart;
	}
	
	/**
	 * 商品添加到购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取商品id和购买数量
		String pid = request.getParameter("pid");
		int count = Integer.parseInt(request.getParameter("count"));
		
		//2.调用productservice查询商品
		ProductService service = (ProductService) BeanFactory.getBean("ProductService");
		Product product = service.getById(pid);
		
		//3.创建购物车项对象和获取购物车对象
		CartItem item = new CartItem(product, count);
		Cart cart = getCart(request);
		
		//4.购物车项添加到购物车
		cart.add2Cart(item);
		
		//5.重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		return null;
	}

	/**
	 * 删除指定购物车项
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.获取商品id
		String pid = request.getParameter("pid");
		
		//2.获取购物车
		Cart cart = getCart(request);
		
		//3.从购物车中移除该商品
		if (pid != null && cart != null) {
			cart.removeFromCart(pid);
		}
		
		//4.重定向回到cart.jsp页面
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		return null;
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.清空
		getCart(request).clearCart();
		
		//4.重定向回到cart.jsp页面
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		return null;
	}
}
