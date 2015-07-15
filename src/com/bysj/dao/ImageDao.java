package com.bysj.dao;

import java.util.List;

/**
 * 
 * @author gzwzh1994
 * 
 */
public interface ImageDao<T> {
	/**
	 * 添加图片
	 */
	public void save(T o);

	/**
	 * 删除图片
	 */
	public void delete(T o);

	/**
	 * 修改图片
	 */
	public void saveOrUpdate(T o);

	/**
	 * 图片查询
	 */
	public List<T> find(String hql);

	public List<T> find(String hql, Object[] param);

	public T get(String hql, Object[] param);
}
