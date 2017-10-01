package pers.hdh.beans;

import java.util.List;

/**
 * 分页的Bean实体
 * @param <E>
 */
public class PageBean<E> {
    private List<E> list;
    private Integer currPage;   // 当前页码
    private Integer pageSize;   // 页面规定记录数
    private Integer totalCount; // 总记录数
    private Integer totalPage;  // 总页面数

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return  (int)(Math.ceil(totalCount*1.0)/totalPage);
    }

    public PageBean() {
        super();
    }

    public PageBean(List<E> list, Integer currPage, Integer pageSize, Integer totalCount, Integer totalPage) {
        this.list = list;
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }
}
