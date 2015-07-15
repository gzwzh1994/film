package com.bysj.dao;

import java.util.List;

public interface MoiveDao<T> {
	/**
	 * 添加影片
	 */
   public void save(T o);
   
   /**
    * 删除影片
    */
   public void delete(T o);
   
   /**
    * 修改影片
    */
   public void saveOrUpdate(T o);
   
   /**
    * 影片查询
    */
   public List<T> find(String hql);
   public List<T> find(String hql, Object[] param);
   
   /**
    * 单个影片查询
    */
   public T get(String hql, Object[] param);
}
