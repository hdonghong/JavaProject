package team.webstore.web.action;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.PageBean;
import team.webstore.domain.Product;
import team.webstore.service.ProductService;
import team.webstore.utils.CookieUtils;

/**
 * 商品模块
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午7:01:38 
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private static final long serialVersionUID = -6971628227306265321L;

	private Product product = new Product();
	@Override
	// model 是action的一个属性
	public Product getModel() {
		return product;
	}
	
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * 进入首页时查询数据库展示热门商品(销量最高的商品)和最新商品(最近变动过或增加的商品)
	 * @return
	 */
	public String showIndex() {
		// 限制查询的商品数
		int listSize = 9;
		// 查询热门商品和最新商品
		List<Product> hotProductsList = productService.findHotProducts(listSize);
		List<Product> newProductsList = productService.findNewProducts(listSize);
		// 压栈
		ActionContext.getContext().getValueStack().set("hotProducts", hotProductsList);
		ActionContext.getContext().getValueStack().set("newProducts", newProductsList);
		
		return "index";
	}
	
	/**
	 * 查看商品详情
	 * @return
	 */
	public String getById() {
		product = productService.getById(product.getPid());
		// 压栈
		ActionContext.getContext().getValueStack().set("product",product);
		
		// 实现：浏览记录功能
		String history = product.getPimage(); //浏览记录
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie historiesCookie = CookieUtils.getCookieByName("histories", request.getCookies()); // 获取浏览器中已存在的cookie:histories
		String historiesStr = "";
		if ( historiesCookie == null) { // 第一次查看商品详情，此时没有idsCookie，将此次商品id放入ids中
			historiesStr = history;
		} else { // 不是第一次查看商品详情
			historiesStr = historiesCookie.getValue();
			// 将idsCookie的值切割，存入链表中，看此次查看的商品是否有与浏览记录商品重复
			String[] historiesArr = historiesStr.split("-");
			LinkedList<String> historiesList = new LinkedList<>(Arrays.asList(historiesArr));
			
			if (historiesList.contains(history)) { // 重复
				historiesList.remove(history);
			} else { // 不重复
				if (historiesList.size() >= 7) { // 限制最多有8个记录
					historiesList.removeLast();
				}
			}
			
			// 重写cookie histories 的值
			historiesList.addFirst(history);
			historiesStr = "";
			for (String h : historiesList) {
				historiesStr += (h + "-");
			}
			historiesStr = historiesStr.substring(0, historiesStr.length()-1);
		}
		historiesCookie = new Cookie("histories", historiesStr);
		CookieUtils.addCookie(historiesCookie, request.getContextPath()+"/", 3600, response);
		
		return "product_info";
	}
	
	// 属性驱动的方式
	// 当前页，默认值就是1
	private Integer currPage = 1;
	public void setCurrPage(Integer currPage) {
		this.currPage = 
				currPage > 1 ? currPage : 1;
	}
	
	/**
	 * 分页展示商品
	 * @return
	 */
	public String findByPage() {
		// 封装查询条件
		int pageSize = 12;	// 当前页展示商品数
		int state = 1;		// 上架的商品
		// QBC
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		if (product.getCategory() != null && product.getCategory().getCid() != null) { // 按照分类展示商品
			criteria.add(Restrictions.eq("category.cid", product.getCategory().getCid()));
		} else if (product.getPdesc() != null) {    // 按照搜索关键词展示商品
			String keywords = product.getPdesc().trim();
			if (keywords.equals("")) {
				product.setPdesc("所有商品");
			} else if (keywords.equals("所有商品")) {
				keywords = "";
			}
			criteria.add(Restrictions.like("pdesc", "%"+ keywords +"%"));
		}
		criteria.add(Restrictions.eq("state", state));
		// 保证存在该商品所属分类
		criteria.createCriteria("category").add(Restrictions.like("state", state));
		
		// 获取一个页面bean
		PageBean<Product> page = productService.findByPage(currPage, pageSize, criteria);
		// 压栈
		ActionContext.getContext().getValueStack().set("page", page);
		// 页面跳转
		return "product_list";
	}
	
	/**
	 * 清空历史纪录
	 * @return
	 */
	public String clearHistories() {
		CookieUtils.addCookie(
				new Cookie("histories", null), 
				ServletActionContext.getRequest().getContextPath()+"/", 
				0, 
				ServletActionContext.getResponse());
		
		return NONE;
	}
	
}
