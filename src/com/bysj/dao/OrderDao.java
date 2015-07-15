package com.bysj.dao;

import java.util.List;

/**
 * 
 * @author gzwzh1994
 * 
 */
public interface OrderDao<T> {
	/**
	 * 添加订单
	 */
	public void save(T o);

	/**
	 * 删除订单
	 */
	public void delete(T o);

	/**
	 * 修改订单
	 */
	public void saveOrUpdate(T o);

	/**
	 * 订单查询
	 */
	public List<T> find(String hql);

	public List<T> find(String hql, Object[] param);

	/**
	 * 订单查询（带分页）
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 单个订单查询
	 */
	public T get(String hql, Object[] param);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Object[] param);
}
