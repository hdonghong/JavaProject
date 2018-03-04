package com.itheima.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	/**
	 * 查询最新商品
	 */
	@Override
	public List<Product> findNew() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

	/**
	 * 查询热门商品
	 */
	@Override
	public List<Product> findHot() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot=1 order by pdate limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

	/**
	 * 通过商品id查询商品
	 */
	@Override
	public Product getById(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=? limit 1";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
	}

	/**
	 * 按类别分页查询数据
	 */
	@Override
	public List<Product> findByPage(String cid, int currPage, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=? limit ?, ?";
		return qr.query(sql, new BeanListHandler<>(Product.class), cid, (currPage-1)*pageSize, pageSize);
	}

	/**
	 * 按类别查询数据总条数
	 */
	@Override
	public int getTotalCount(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		return ((Long)qr.query(sql, new ScalarHandler<>(), cid)).intValue();
	}

	/**
	 * 更新商品所有的分类 cid 为删除分类做准备
	 */
	@Override
	public void updateCid(String cid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update product set cid = null where cid = ?";
		qr.update(DataSourceUtils.getConnection(), sql, cid);
	}

	/**
	 * 查询所有商品
	 */
	@Override
	public List<Product> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

	/**
	 * 添加商品
	 */
	@Override
	public void add(Product product) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql, product.getPid(), product.getPname(), product.getMarket_price(),
				product.getShop_price(), product.getPimage(), product.getPdate(), product.getIs_hot(),
				product.getPdesc(), product.getPflag(), product.getCategory().getCid());
	}

}
