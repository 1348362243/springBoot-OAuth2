package com.wx.spring.base;

/**
 * 分页类
 * <br>@class: BasePage 
 * <br>@description: 
 * <br>@author: wangxin
 * <br>@date: 2019年5月9日 上午10:12:18
 * <br>@version: 1.0
 * <br>
 * <br>@since: (版本) 作者 时间 注释
 */
public class BasePage {
 
	private Integer page;
	private Integer size;
	
	/**
	 * 排序 正序 order by xx    倒序 order by xx desc
	 */
	private String orderBySome;

	public Integer getCurrentPage() {
		return page;
	}
	public Integer getBegin() {
		getPage();
		return (this.page - 1) * getSize();
	}
	public Integer getRow() {
		Integer size = this.size == null ? 10 : this.size;
		this.size = size > 200 ? 200 : size;
		return this.size;
	}
	public Integer getPage() {
		this.page = (page == null || page < 1) ? 1 : page;
		return  page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size == null ? 10 : size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}	
	 
	public String getOrderBySome() {
		return orderBySome;
	}

	public void setOrderBySome(String orderBySome) {
		this.orderBySome = orderBySome;
	}
	
	@Override
	public String toString() {
		return "BasePage [page=" + page + ", size=" + size + "]";
	}
}
