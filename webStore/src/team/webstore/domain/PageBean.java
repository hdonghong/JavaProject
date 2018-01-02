package team.webstore.domain;

import java.util.List;

/**
 * 分页的JavaBean
 * @author Lenovo
 * @param <T>
 *
 */
public class PageBean<T> {

	private int currPage;		// 当前页面
	private int totalCount;		// 总记录数
	private int pageSize;		// 每页显示的记录数
	private List<T> beanList;	// 页面显示的数据
	
	public int getTotalPage() {	// 获取总页面数
		return (int) Math.ceil(totalCount*1.0/pageSize);
	}
	
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public PageBean() {
		super();
	}
	public PageBean(int currPage, int totalCount, int pageSize, List<T> beanList) {
		super();
		this.currPage = currPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.beanList = beanList;
	}
	
}
