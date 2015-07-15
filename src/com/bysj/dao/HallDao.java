package com.bysj.dao;

import java.util.List;

public interface HallDao<T> {
	/**
	 * 添加影厅
	 */
   public void save(T o);
   
   /**
    * 删除影厅
    */
   public void delete(T o);
   
   /**
    * 修改影厅
    */
   public void saveOrUpdate(T o);
   
   /**
    * 影厅查询
    */
   public List<T> find(String hql);
   public List<T> find(String hql, Object[] param);
   public List<T> find(String hql, List<Object> param);
   
   /**
    * 单个影厅查询
    */
   public T get(String hql, Object[] param);
}
