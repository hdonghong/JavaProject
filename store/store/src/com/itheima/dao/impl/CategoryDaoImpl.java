package com.itheima.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;
import com.itheima.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	/**
	 * 查询所有分类
	 */
	@Override
	public List<Category> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

	/**
	 * 添加分类
	 */
	@Override
	public void add(Category category) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?, ?)";
		qr.update(sql, category.getCid(), category.getCname());
	}

	/**
	 * 通过分类id获取一个分类
	 */
	@Override
	public Category getById(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Category.class), cid);
	}

	/**
	 * 编辑分类
	 */
	@Override
	public void update(Category category) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname = ? where cid = ?";
		qr.update(sql, category.getCname(), category.getCid());
	}

	/**
	 * 删除分类
	 */
	@Override
	public void update(String cid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "delete from category where cid = ?";
		qr.update(DataSourceUtils.getConnection(), sql, cid);
	}

}
