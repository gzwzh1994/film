package com.bysj.dao;

import java.util.List;

public interface UserDao<T> {
	/**
	 * 添加用户
	 */
   public void save(T o);
   
   /**
    * 删除用户
    */
   public void delete(T o);
   
   /**
    * 修改用户
    */
   public void saveOrUpdate(T o);
   
   /**
    * 用户查询
    */
   public List<T> find(String hql);
   public List<T> find(String hql, Object[] param);
   
   /**
    * 单个用户查询
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
