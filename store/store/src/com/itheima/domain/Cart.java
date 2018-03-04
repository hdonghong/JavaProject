package com.itheima.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String, CartItem> map = new LinkedHashMap<>();
	private Double total=0.0;
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * 提供一个javabean属性方便cart.jsp中对购物车项的遍历
	 * @return
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	/**
	 * 添加到购物车
	 * @param item
	 */
	public void add2Cart(CartItem item) {
		if (item != null) {
		//1.先判断购物车中有无该商品
			String pid = item.getProduct().getPid();
			if (map.containsKey(pid)) {
				//购物车中已有该商品
				map.get(pid).setCount(map.get(pid).getCount()+item.getCount());
			} else {
				//购物车中没有该商品
				map.put(pid, item);
			}
		//2.添加完成后修改金额
			total += item.getSubtotal();
		}
		
	}
	
	/**
	 * 从购物车中移除购物车项
	 * @param pid
	 */
	public void removeFromCart(String pid) {
		if (pid != null && map.containsKey(pid)) {
			CartItem removedItem =  map.remove(pid);
			
			total -= removedItem.getSubtotal();
		}
	}
	
	/**
	 * 清空购物车
	 */
	public void clearCart() {
		map.clear();
		total = 0.0;
	}
}
