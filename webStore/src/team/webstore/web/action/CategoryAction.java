package team.webstore.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.Category;
import team.webstore.service.CategoryService;
import team.webstore.utils.FastJsonUtils;

/**
 * 商品分类模块
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午2:34:05 
 */
public class CategoryAction extends ActionSupport implements ModelDriven<Category>{

	private static final long serialVersionUID = -7809140707063903323L;

	private Category category = new Category();
	@Override
	public Category getModel() {
		return category;
	}
	
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 查询所有分类
	 * @return
	 */
	public String findAll() {
		List<Category> categoryList =  categoryService.findAll();
		// 把获取到的分类列表转化成json格式字符串
		String jsonString = FastJsonUtils.toJSONString(categoryList);
		// 写到浏览器
		FastJsonUtils.write_json(ServletActionContext.getResponse(), jsonString);
		return NONE;
	}
	
}
