package team.webstore.web.action;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import team.webstore.domain.Category;
import team.webstore.service.CategoryService;

/**
 * 商品分类的后台管理控制器
 * @author hdonghong 
 * @version 创建时间：2017年12月7日 下午2:00:07 
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {

	private static final long serialVersionUID = -3694802208594158405L;

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
		List<Category> categoryList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("list", categoryList);
		return "category_list";
	}
	
	/**
	 * 添加新分类
	 * @return
	 */
	public String add() {
		categoryService.add(category);
		// 添加成功后跳转到查询分类页面
		return "category_findAll";
	}
	
	/**
	 * 通过分类的cid获取此项分类的对象
	 * 放入值栈中后请求转发
	 * @return
	 */
	public String getById() {
		category = categoryService.getById(category.getCid());
		ActionContext.getContext().getValueStack().set("category", category);
		return "category_edit";
	}
	
	/**
	 * 更新分类名称
	 * @return
	 */
	public String update() {
		categoryService.update(category);
		return "category_findAll";
	}
	
	/**
	 * 删除此项分类
	 * @return
	 */
	public String delete() {
		categoryService.delete(category);
		return "category_findAll";
	}
	
	// 排序
	private Integer rank;
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * 获取分类汇总
	 * @return
	 */
	public String getCollection() {
		List<Category> categoryList = categoryService.findAll();
		
		if (rank!=null && rank == 2) { // 按销售额排序
			Collections.sort(categoryList, new Comparator<Category>() {
				@Override
				public int compare(Category o1, Category o2) {
					return o2.getTotal().compareTo(o1.getTotal());
				}
				
			});
		} else { // 按销售量排序
			rank = 1;
			Collections.sort(categoryList, new Comparator<Category>() {
				@Override
				public int compare(Category o1, Category o2) {
					return o2.getCount().compareTo(o1.getCount());
				}
				
			});
		}
		
		ActionContext.getContext().getValueStack().set("rank", rank);
		ActionContext.getContext().getValueStack().set("list", categoryList);
		return "category_coll";
	}
}
