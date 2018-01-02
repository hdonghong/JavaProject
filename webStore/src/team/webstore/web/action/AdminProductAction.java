package team.webstore.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.OrderItem;
import team.webstore.domain.PageBean;
import team.webstore.domain.Product;
import team.webstore.service.ProductService;
import team.webstore.utils.FastJsonUtils;

/**
 * 商品的后台管理模块
 * @author hdonghong 
 * @version 创建时间：2017年12月7日 下午3:49:32 
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	private static final long serialVersionUID = -8363342626893262158L;

	private Product product = new Product();
	@Override
	public Product getModel() {
		return product;
	}
	
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	private Integer currPage=1;
	public void setCurrPage(Integer currPage) {
		this.currPage = 
				currPage > 1 ? currPage : 1;
	}
	private Date begin;
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	private Date end;
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * 分页查询所有商品
	 * @return
	 */
	public String findAll() {
		// 封装查询条件
		int pageSize = 11;	// 当前页展示商品数
		int state = 1;		// 上架的商品
		// QBC
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按照分类展示商品
		if (product.getCategory() != null && product.getCategory().getCid() != null) {
			criteria.add(Restrictions.eq("category.cid", product.getCategory().getCid()));
		} 
		// 按照搜索关键词展示商品
		if (product.getPdesc() != null) {
			String keywords = product.getPdesc().trim();
			criteria.add(Restrictions.like("pdesc", "%"+ keywords +"%"));
		}
		// 按照上架时间展示商品
		if (begin != null) {
			criteria.add(Restrictions.gt("create_at", begin.getTime()));
		}
		if (end != null) {
			criteria.add(Restrictions.lt("create_at", end.getTime()));
		}
		// 上架时间倒序排序
		criteria.addOrder(Order.desc("create_at"));
		// 要求商品为上架状态
		criteria.add(Restrictions.eq("state", state));
		// 保证存在该商品所属分类
		criteria.createCriteria("category").add(Restrictions.like("state", state));
		
		// 获取一个页面bean
		PageBean<Product> page = productService.findByPage(currPage, pageSize, criteria);
		// 压栈
		ActionContext.getContext().getValueStack().set("page", page);			
		ActionContext.getContext().getValueStack().set("begin", begin);
		ActionContext.getContext().getValueStack().set("end", end);
		// 页面跳转
		return "product_list";
	}
	
	/**
	 * 查看商品评价
	 */
	public String showComments() {
		product = productService.getById(product.getPid());
		// 将获取的product.orderItems对象转成json格式数据
		Set<OrderItem> orderItems = product.getOrderItems();
		String jsonString = FastJsonUtils.toJSONString(orderItems);
		// 写回到浏览器
		FastJsonUtils.write_json(ServletActionContext.getResponse(), jsonString);
		return NONE;
	}
	
	/**
	 * 通过商品id查询商品
	 */
	public String getById() {
		product = productService.getById(product.getPid());
		ActionContext.getContext().getValueStack().set("product", product);
		return "product_edit";
	}
	
	/**
	 * 文件的上传，需要再CustomerAction类中定义成员的属性，命名是有规范的!
	 * private File upload; // 表示要上传的文件
	 * private String uploadFileName; // 表示上传文件的名称(不会中文乱码)
	 * private String uploadContentType; // 表示上传文件的MIME类型
	 * 提供set方法，通过拦截器注入值
	 */
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	/**
	 * 保存商品
	 * @return
	 */
	public String save() {
		if (upload != null) {
			// TODO
			// 保存文件的路径
			// String rootPath = "F:/Study/program/JAVAWEB/webStore/WebContent/"; // 根路径
			String rootPath = ServletActionContext.getRequest().getRealPath("")+File.separatorChar; // 根路径
			String subPath = "products/"+product.getCategory().getCid()+"/"+uploadFileName; // 子路径，同时保存到数据库中
			// 创建保存的file对象
			File file = new File(rootPath + subPath);
			// 拷贝
			try {
				FileUtils.copyFile(upload, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 保存路径
			product.setPimage(subPath);
		}
		// 调用service执行业务
		productService.save(product);
		
		return "product_findAll";
	}
	
	/**
	 * 更新商品信息
	 * @return
	 */
	public String update() {
		productService.update(product);
		return "product_findAll";
	}
	
	/**
	 * 下架指定商品
	 * @return
	 */
	public String delete() {
		productService.delete(product);
		return "product_findAll";
	}
	
	// 排序方式
	private Integer rank;
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	// 数据量
	private Integer size=10;
	public void setSize(Integer size) {
		this.size = 
				size > 0 ? size : 10;
	}

	/**
	 * 商品汇总查询
	 * @return
	 */
	public String getCollection() {
		DetachedCriteria criteria = DetachedCriteria.forClass(product.getClass());
		criteria.add(Restrictions.eq("state", 1));
		// 保证存在该商品所属分类
		criteria.createCriteria("category").add(Restrictions.like("state", 1));
		// 按照分类汇总
		if (product.getCategory() != null && product.getCategory().getCid() != null) {
			criteria.add(Restrictions.eq("category.cid", product.getCategory().getCid()));
		}
		PageBean<Product> page = productService.findByPage(currPage, criteria);
		
		if (page != null) {
			if (rank!=null && rank == 2) { // 按销售额排序
				Collections.sort(page.getBeanList(), new Comparator<Product>() {
					@Override
					public int compare(Product o1, Product o2) {
						return o2.getTotal().compareTo(o1.getTotal());
					}
					
				});
			} else { // 按销售量排序
				rank = 1;
				Collections.sort(page.getBeanList(), new Comparator<Product>() {
					@Override
					public int compare(Product o1, Product o2) {
						return o2.getPcount().compareTo(o1.getPcount());
					}
					
				});
			}
			if (size < page.getTotalCount()) {
				page.setBeanList(page.getBeanList().subList(0, size));
				page.setPageSize(size);
			} else {
				page.setPageSize(page.getTotalCount());
			}
			
			ActionContext.getContext().getValueStack().set("rank", rank);
			ActionContext.getContext().getValueStack().set("page", page);
		}
		return "product_coll";
	}
}
