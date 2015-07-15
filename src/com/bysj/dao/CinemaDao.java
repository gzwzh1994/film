package com.bysj.dao;

import java.util.List;

/**
 * 
 * @author gzwzh1994
 * 
 */
public interface CinemaDao<T> {
	/**
	 * 添加影院
	 */
	public void save(T o);

	/**
	 * 删除影院
	 */
	public void delete(T o);

	/**
	 * 修改影院
	 */
	public void saveOrUpdate(T o);

	/**
	 * 影院查询
	 */
	public List<T> find(String hql);

	public List<T> find(String hql, Object[] param);

	public List<T> find(String hql, List<Object> param);

	/**
	 * 单个影院查询
	 */
	public T get(String hql, Object[] param);
}
