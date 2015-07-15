package com.bysj.dao;

import java.util.List;

public interface AdminDao<T> {
	/**
	 * 添加管理员
	 */
   public void save(T o);
   
   /**
    * 删除管理员
    */
   public void delete(T o);
   
   /**
    * 修改管理员
    */
   public void saveOrUpdate(T o);
   
   /**
    * 用户管理员
    */
   public List<T> find(String hql);
   public List<T> find(String hql, Object[] param);
   
   /**
    * 单个用户管理员
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
