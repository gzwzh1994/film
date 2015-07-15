package com.bysj.dao;

import java.util.List;

/**
 * 
 * @author gzwzh1994
 * 
 */
public interface CityDao<T> {
	/**
	 * 添加城市
	 */
	public void save(T o);

	/**
	 * 删除城市
	 */
	public void delete(T o);

	/**
	 * 修改城市
	 */
	public void saveOrUpdate(T o);

	/**
	 * 城市查询
	 */
	public List<T> find(String hql);

	public List<T> find(String hql, Object[] param);

	/**
	 * 单个城市查询
	 */
	public T get(String hql, Object[] param);
}
