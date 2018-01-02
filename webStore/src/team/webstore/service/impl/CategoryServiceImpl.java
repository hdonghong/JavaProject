package team.webstore.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import team.webstore.domain.Category;
import team.webstore.dao.CategoryDao;
import team.webstore.service.CategoryService;

/**
 * 
 * @author hdonghong 
 * @version 创建时间：2017年11月25日 下午2:37:47 
 */
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public void add(Category category) {
		// 将状态设为可用
		category.setState(1);
		categoryDao.add(category);
	}

	@Override
	public Category getById(Long cid) {
		return categoryDao.getById(cid);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	@Override
	public void delete(Category category) {
		category.setState(0);
		categoryDao.update(category);
	}
	
	
}
