package team.webstore.web.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.Cart;
import team.webstore.domain.CartItem;
import team.webstore.domain.Product;
import team.webstore.service.ProductService;

/**
 * 购物车模块
 * @author hdonghong 
 * @version 创建时间：2017年11月26日 下午4:12:52 
 */
public class CartAction extends ActionSupport implements ModelDriven<Product> {

	private static final long serialVersionUID = -3626391035839002127L;

	private Product product = new Product();
	@Override
	public Product getModel() {
		return product;
	}
	
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private Integer count; //商品数量
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/**
	 * 获取当前session中的购物车
	 * @return Cart对象
	 */
	public Cart getCart() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}

	/**
	 * 添加商品到购物车中
	 * @return
	 */
	public String add() {
		// 先查询到该商品
		product = productService.getById(product.getPid());
		if (product != null && count != null) {
			// 生成购物车项
			CartItem item = new CartItem(product, count);
			// 封装到购物车中
			getCart().add2Cart(item);
		}
		
		return "cart";
	}
	
	/**
	 * 移除购物车中某商品项
	 * @return
	 */
	public String remove() {
		Cart cart = getCart();
		if (product.getPid() != null ) {
			cart.removeFromCart(product.getPid());
		}
		
		return "cart";
	}
	
	/**
	 * 清空购物车
	 * @return
	 */
	public String clear() {
		getCart().clearCart();
		return "cart";
	}
	
}
